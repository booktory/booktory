import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const mypageStore = {
  namespaced: true,
  state: {
    userInfo: null,
    mainBadge: null,
    badgeList: [
      { state: false, name: "책이 궁금해", content: "하나 이상의 클럽에 가입해요.", isMain: false },
      {
        state: false,
        name: "카리스마 리더",
        content: "하나 이상의 클럽을 만들어요.",
        isMain: false,
      },
      { state: false, name: "책토리 회장님", content: "셋 이상의 클럽을 만들어요.", isMain: false },
      { state: false, name: "모임의 시작", content: "모임에 한 번 이상 참여해요.", isMain: false },
      { state: false, name: "책을 좋아해", content: "모임에 10번 이상 참여해요.", isMain: false },
      {
        state: false,
        name: "나는야 책 인싸",
        content: "모임에 30번 이상 참여해요.",
        isMain: false,
      },
      { state: false, name: "책토리 홀릭", content: "모임에 50번 이상 참여해요.", isMain: false },
      {
        state: false,
        name: "책토리 토박이",
        content: "모임에 100번 이상 참여해요.",
        isMain: false,
      },
      {
        state: false,
        name: "독서에 미친 자",
        content: "모임에 100번 이상 참여해요.",
        isMain: false,
      },
      {
        state: false,
        name: "독서는 습관",
        content: "일주일 내에 모임에 세 번 이상 참여해요.",
        isMain: false,
      },
      {
        state: false,
        name: "자타공인 책덕후",
        content: "일주일 내에 모임에 일곱 번 이상 참여해요.",
        isMain: false,
      },
      {
        state: false,
        name: "책토리 중독",
        content: "하루에 모임을 두 번 이상 참여해요.",
        isMain: false,
      },
      {
        state: false,
        name: "첫 글의 설렘",
        content: "게시글을 한 개 이상 작성해요.",
        isMain: false,
      },
      { state: false, name: "소통의 달인", content: "게시글을 10개 이상 작성해요.", isMain: false },
      {
        state: false,
        name: "독서 나눔",
        content: "Read With Me에 두 시간 이상 참여해요.",
        isMain: false,
      },
    ],
    alarmRead: true,
  },
  getters: {
    userInfo(state) {
      return state.userInfo;
    },
    mainBadge(state) {
      return state.mainBadge;
    },
    badgeList(state) {
      return state.badgeList;
    },
    alarmRead(state) {
      return state.alarmRead;
    },
  },
  mutations: {
    SET_USERINFO(state, data) {
      state.userInfo = data;
    },
    SET_BADGELIST(state, data) {
      state.badgeList = data;
    },
    SET_MAINBADGE(state, data) {
      state.mainBadge = data;
    },
    SET_ALARM_READ(state, data) {
      state.alarmRead = data;
    },
  },
  actions: {
    // 회원 정보 확인
    findUserInfo({ rootGetters, getters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getUserInfo, rootGetters.config)
        .then((res) => {
          // 회원 정보 설정
          commit("SET_USERINFO", res.data);
          // 배지 목록 가져오기
          let badgeList = getters.badgeList;
          // 나의 배지 목록 설정
          for (var i = 0; i < res.data.badgeList.length; i++) {
            badgeList[res.data.badgeList[i]].state = true;
          }
          commit("SET_BADGELIST", badgeList);
          // 대표 배지 설정
          let mainBadgeName = null;
          if (res.data.mainBadge >= 0) {
            mainBadgeName = badgeList[res.data.mainBadge].name;
            badgeList[res.data.mainBadge].isMain = true;
          }
          commit("SET_MAINBADGE", mainBadgeName);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 회원 정보 수정
    updateUserInfo({ rootGetters, commit }, userData) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.updateUserInfo, userData, rootGetters.config)
        .then((res) => {
          commit("userNickname", userData.nickname, { root: true });
          localStorage.setItem("userNickname", userData.nickname);
          Swal.fire({
            icon: "success",
            title: "프로필 수정 완료",
            html: "프로필이 수정 되었습니다.<br>지금부터 바뀐 정보를 확인하실 수 있어요!",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
          router.push({ name: "MyPage" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "프로필 수정 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 비밀번호 변경
    changePassword({ dispatch, rootGetters }, passwordData) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.changePassword, passwordData, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          Swal.fire({
            icon: "success",
            title: "비밀번호 변경 완료",
            html: "비밀번호가 변경 되었습니다.<br>바뀐 비밀번호로 다시 로그인 해주세요!",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("logout", null, { root: true });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "비밀번호 변경 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 대표배지 설정
    changeMainBadge({ getters }, badgeId) {
      axios
        .patch(SERVER.URL + "/users/" + getters.userInfo.id + "/main-badge/" + badgeId)
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "대표배지 설정 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
          router.go(0);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "대표배지 설정 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 알람 읽음 여부 체크
    fetchAlarmRead({ commit }, chk) {
      commit("SET_ALARM_READ", chk);
    },
  },
};

export default mypageStore;
