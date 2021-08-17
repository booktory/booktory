import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";
var moment = require("moment");

const clubStore = {
  namespaced: true,
  state: {
    myClubList: null,
    clubInfo: null,
    isPolling: false,
    meetingInfo: {
      bookclubId: null,
      startTime: "",
      remainTime: "",
      isOpen: false,
      isCalc: true,
    },
    clubId: null,
    isLeader: null,
    applyList: null,
    joinedList: null,
    questionList: null,
    newClubData: null,
    clubIndex: 0,
  },
  getters: {
    myClubList(state) {
      return state.myClubList;
    },
    clubInfo(state) {
      return state.clubInfo;
    },
    isPolling(state) {
      return state.isPolling;
    },
    meetingInfo(state) {
      return state.meetingInfo;
    },
    clubId(state) {
      return state.clubId;
    },
    isLeader(state) {
      return state.isLeader;
    },
    applyList(state) {
      return state.applyList;
    },
    joinedList(state) {
      return state.joinedList;
    },
    questionList(state) {
      return state.questionList;
    },
    newClubData(state) {
      return state.newClubData;
    },
    clubIndex(state) {
      return state.clubIndex;
    },
  },
  mutations: {
    SET_MYCLUB_LIST(state, data) {
      state.myClubList = data;
    },
    SET_CLUB_INFO(state, data) {
      state.clubInfo = data;
    },
    SET_IS_POLLING(state, data) {
      state.isPolling = data;
    },
    SET_MEETING_INFO(state, data) {
      state.meetingInfo = data;
    },
    SET_CLUBID(state, data) {
      state.clubId = data;
    },
    SET_IS_LEADER(state, data) {
      state.isLeader = data;
    },
    SET_APPLY_LIST(state, data) {
      state.applyList = data;
    },
    SET_JOINED_LIST(state, data) {
      state.joinedList = data;
    },
    SET_QUESTION_LIST(state, data) {
      state.questionList = data;
    },
    SET_NEW_CLUBDATA(state, data) {
      state.newClubData = data;
    },
    SET_CLUB_INDEX(state, data) {
      state.clubIndex = data;
    },
  },
  actions: {
    // 내 클럽 정보 확인
    findClubList({ rootGetters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.findClubList, rootGetters.config)
        .then((res) => {
          commit("SET_MYCLUB_LIST", res.data);
          if (res.data.length <= 0) {
            router.push({ name: "ClubSearch" });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 보고 있는 클럽 인덱스 저장
    setClubIndex({ commit }, index) {
      commit("SET_CLUB_INDEX", index);
    },
    // 해당 클럽 정보 확인
    findClubInfo({ rootGetters, commit, dispatch }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getClubInfo + clubId, rootGetters.config)
        .then((res) => {
          commit("SET_CLUBID", clubId);
          commit("SET_CLUB_INFO", res.data);
          commit("SET_IS_LEADER", res.data.isLeader);
          // 다음 모임 정보 설정
          let meetingInfo = null;
          // console.log(res.data.endDateTime);
          if (res.data.endDateTime) {
            meetingInfo = {
              bookclubId: res.data.bookClubId,
              startTime: res.data.endDateTime,
              remainTime: "",
              isOpen: false,
              isCalc: true,
            };
          }

          commit("SET_MEETING_INFO", meetingInfo);
          dispatch("calcRemainTime");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 다음 모임까지 남은 시간 계산
    calcRemainTime({ getters, commit }) {
      let meetingInfo = getters.meetingInfo;
      // 다음 모임이 있으면 남은 시간 계산
      if (meetingInfo && meetingInfo.isCalc) {
        // console.log(meetingInfo.isOpen);
        let diffSecond = Math.floor(
          moment(meetingInfo.startTime).add(9, "h").subtract(moment()) / 1000
        );
        let diffTime = Math.floor(diffSecond / 60);
        let diffTimeHour = Math.floor(diffTime / 60);
        let diffTimeDay = Math.floor(diffTimeHour / 24);
        // 모임까지 00일 00시 00분 00초 남았습니다.
        let dateStr = "모임까지 ";
        if (diffTimeDay > 0) dateStr += diffTimeDay + "일 ";
        if (diffTimeHour > 0) dateStr += (diffTimeHour % 24) + "시간 ";
        if (diffTime >= 10) dateStr += (diffTime % 60) + "분 ";
        if (diffSecond > 0) dateStr += (diffSecond % 60) + "초 ";
        meetingInfo.remainTime = dateStr + "남았습니다.";
        if (diffTime < 10) {
          meetingInfo.isOpen = true;
          meetingInfo.remainTime = "곧 모임이 시작됩니다.";
        }
        if (diffSecond <= 0) {
          meetingInfo.remainTime = "모임이 시작되었습니다.";
          meetingInfo.isCalc = false;
        }
        commit("SET_MEETING_INFO", meetingInfo);
      }
    },
    pollingStart({ getters, commit, dispatch }) {
      commit("SET_IS_POLLING", true);
      this.polling = setInterval(() => {
        dispatch("calcRemainTime");
        if (!getters.isPolling) clearInterval(this.polling);
      }, 1000);
    },
    pollingEnd({ commit }) {
      commit("SET_IS_POLLING", false);
    },
    // 새 클럽 만들 정보 저장
    saveClubData({ commit }, clubData) {
      commit("SET_NEW_CLUBDATA", clubData);
      router.push({ name: "ClubCreateBook" });
    },
    // 새 클럽 만들기
    createClub({ rootGetters, getters }, books) {
      let clubData = getters.newClubData;
      clubData.books = books;
      axios
        .post(SERVER.URL + SERVER.ROUTES.createClub, clubData, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          Swal.fire({
            icon: "success",
            title: "클럽 생성 완료",
            html: "클럽 생성이 완료 되었습니다.<br>지금부터 새로운 클럽에서 활동하실 수 있어요!",
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 생성 실패",
            text: err.response.data.message,
          });
        });
    },
    // 클럽 정보 수정
    updateClub({ rootGetters, getters }, clubData) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.updateClub + getters.clubId, clubData, rootGetters.config)
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "클럽 정보 수정 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
          router.push({ name: "ClubdetailManage" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 정보 수정 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 클럽 가입신청
    applyToClub({ rootGetters, getters }) {
      axios
        .post(SERVER.URL + "/clubs/" + getters.clubId + "/join", null, rootGetters.config)
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "클럽 가입 신청 완료",
            text: getters.clubInfo.name + "에 가입 신청이 완료되었습니다!",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 가입 신청 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 클럽 가입신청 수락
    acceptToClub({ rootGetters, getters, dispatch }, userClubId) {
      axios
        .put(
          SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId,
          null,
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
          dispatch("findApplyList");
          dispatch("findJoinedList");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 클럽 가입신청 거절
    rejectJoin({ rootGetters, getters, dispatch }, userClubId) {
      axios
        .delete(SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId, rootGetters.config)
        .then((res) => {
          console.log(res);
          dispatch("findApplyList");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 클럽 가입신청 회원 목록
    findApplyList({ rootGetters, getters, commit }) {
      axios
        .get(SERVER.URL + "/clubs/" + getters.clubId + "/users/apply", rootGetters.config)
        .then((res) => {
          commit("SET_APPLY_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 클럽 가입된 회원 목록
    findJoinedList({ getters, commit }) {
      axios
        .get(SERVER.URL + "/clubs/" + getters.clubId + "/users")
        .then((res) => {
          commit("SET_JOINED_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 클럽 탈퇴
    deleteClubUser({ rootGetters, getters }) {
      axios
        .delete(
          SERVER.URL + SERVER.ROUTES.deleteClub + getters.clubId + "/user",
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "클럽 탈퇴 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 탈퇴 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 클럽 삭제
    deleteClub({ rootGetters, getters }) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteClub + getters.clubId, rootGetters.config)
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "클럽 삭제 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 삭제 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 문의게시판 목록 확인
    findQuestionList({ commit }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.questions + clubId)
        .then((res) => {
          // 문의게시판 목록 설정
          commit("SET_QUESTION_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 문의게시판 질문 등록
    registerQuestion({ rootGetters, dispatch }, questionData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.questions + questionData.clubId,
          questionData,
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "질문 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findQuestionList", questionData.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "질문 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 문의게시판 답글 등록
    registerAnswer({ rootGetters, getters, dispatch }, questionData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.questions + questionData.questionId + "/answer",
          questionData,
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "답글 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findQuestionList", getters.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "답글 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default clubStore;
