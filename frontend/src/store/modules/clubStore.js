import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const clubStore = {
  namespaced: true,
  state: {
    clubId: null,
    isLeader: null,
    applyList: null,
    joinedList: null,
    questionList: null,
  },
  getters: {
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
  },
  mutations: {
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
  },
  actions: {
    // 클럽 가입신청 수락
    acceptToClub({ rootGetters, getters, commit }, userClubId) {
      axios
        .put(SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId, rootGetters.config)
        .then((res) => {
          console.log(res);
          this.findApplyList();
          this.findJoinedList();
        })
        .catch((err) => {
          console.log(err);
          var apply = getters.applyList;
          var joined = getters.joinedList;
          for (var i = 0; i < apply.length; i++) {
            if (apply[i].id == userClubId) {
              joined.push(apply[i]);
              apply.splice(i, 1);
              break;
            }
          }
          commit("SET_APPLY_LIST", apply);
          commit("SET_JOINED_LIST", joined);
        });
    },
    // 클럽 가입신청 거절
    rejectJoin({ rootGetters, getters, commit }, userClubId) {
      axios
        .delete(SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId, rootGetters.config)
        .then((res) => {
          console.log(res);
          this.findApplyList();
        })
        .catch((err) => {
          console.log(err);
          var apply = getters.applyList;
          for (var i = 0; i < apply.length; i++) {
            if (apply[i].id == userClubId) {
              apply.splice(i, 1);
              break;
            }
          }
          commit("SET_APPLY_LIST", apply);
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
          let list = [
            {
              id: 1,
              userProfileImg: "",
              userNickname: "소프트콘",
            },
            {
              id: 2,
              userProfileImg: "",
              userNickname: "수염맨의여행을떠나요",
            },
            {
              id: 3,
              userProfileImg: "",
              userNickname: "책토리",
            },
          ];
          commit("SET_APPLY_LIST", list);
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
          let list = [
            {
              Id: 4,
              userProfileImg: "",
              userNickname: "가입1",
            },
            {
              Id: 5,
              userProfileImg: "",
              userNickname: "가입2",
            },
            {
              Id: 6,
              userProfileImg: "",
              userNickname: "가입3",
            },
          ];
          commit("SET_JOINED_LIST", list);
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
    findQuestionList({ getters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.questions + getters.clubId)
        .then((res) => {
          // 문의게시판 목록 설정
          commit("SET_QUESTION_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 문의게시판 질문 등록
    registerQuestion({ rootGetters, getters }, questionData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.questions + getters.clubId,
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
          router.go(0);
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
    registerAnswer({ rootGetters }, questionData) {
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
          router.go(0);
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
