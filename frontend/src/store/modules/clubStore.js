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
    isRegisterBook: null,
    clubImage: null,
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
    isRegisterBook(state) {
      return state.isRegisterBook;
    },
    clubImage(state) {
      return state.clubImage;
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
    SET_IS_REGISTER_BOOK(state, data) {
      state.isRegisterBook = data;
    },
    SET_CLUB_IMAGE(state, data) {
      state.clubImage = data;
    },
  },
  actions: {
    // ?????? ????????? ?????? ??????
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
    // ?????? ?????? ?????? ????????? ??????
    setClubIndex({ commit }, index) {
      commit("SET_CLUB_INDEX", index);
    },

    // ?????? ?????? ?????? ?????? - ??????
    findClubInfo({ rootGetters, commit, dispatch }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getClubInfo + clubId, rootGetters.config)
        .then((res) => {
          // ?????? ????????? ????????? ???????????????
          if (!res.data.isMember) {
            Swal.fire({
              icon: "warning",
              title: "????????? ?????? ??? ?????? ???????????????",
              showConfirmButton: false,
              timer: 1500,
              timerProgressBar: false,
            });
            router.push({ name: "ClubSearchBarPageListItem", query: { clubId: res.data.id } });
          } else {
            commit("SET_CLUBID", clubId);
            commit("SET_CLUB_INFO", res.data);
            commit("SET_IS_LEADER", res.data.isLeader);
            commit("SET_CLUB_IMAGE", res.data.img);
            // ?????? ?????? ?????? ??????
            let meetingInfo = null;
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
          }
        })
        .catch((err) => {
          console.log(err);
          Swal.fire({
            icon: "warning",
            title: "???????????? ?????? ???????????????",
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
          router.push({ name: "ClubHome" });
        });
    },
    // ?????? ?????? ?????? ?????? - ?????????
    findClubOpenInfo({ rootGetters, commit, dispatch }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getClubInfo + clubId, rootGetters.config)
        .then((res) => {
          commit("SET_CLUBID", clubId);
          commit("SET_CLUB_INFO", res.data);
          commit("SET_IS_LEADER", res.data.isLeader);
          commit("SET_CLUB_IMAGE", res.data.img);
          // ?????? ?????? ?????? ??????
          let meetingInfo = null;
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
          Swal.fire({
            icon: "warning",
            title: "???????????? ?????? ???????????????",
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
          router.push({ name: "ClubHome" });
        });
    },

    // ????????? ?????? ????????? ????????? ??????
    async checkRegisterBook({ getters, commit }, bookId) {
      await axios
        .get(SERVER.URL + "/clubs/" + getters.clubId + "/books/" + bookId)
        .then((res) => {
          commit("SET_IS_REGISTER_BOOK", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // ?????? ???????????? ?????? ?????? ??????
    calcRemainTime({ getters, commit }) {
      let meetingInfo = getters.meetingInfo;
      // ?????? ????????? ????????? ?????? ?????? ??????
      if (meetingInfo && meetingInfo.isCalc) {
        let diffSecond = Math.floor(
          moment(meetingInfo.startTime).add(9, "h").subtract(moment()) / 1000
        );
        let diffTime = Math.floor(diffSecond / 60);
        let diffTimeHour = Math.floor(diffTime / 60);
        let diffTimeDay = Math.floor(diffTimeHour / 24);
        // ???????????? 00??? 00??? 00??? 00??? ???????????????.
        let dateStr = "???????????? ";
        if (diffTimeDay > 0) dateStr += diffTimeDay + "??? ";
        if (diffTimeHour > 0) dateStr += (diffTimeHour % 24) + "?????? ";
        if (diffTime >= 10) dateStr += (diffTime % 60) + "??? ";
        if (diffSecond > 0) dateStr += (diffSecond % 60) + "??? ";
        meetingInfo.remainTime = dateStr + "???????????????.";
        if (diffTime < 10) {
          meetingInfo.isOpen = true;
          meetingInfo.remainTime = "??? ????????? ???????????????.";
        }
        if (diffSecond <= 0) {
          meetingInfo.remainTime = "????????? ?????????????????????.";
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

    // ??? ?????? ?????? ?????? ??????
    saveClubData({ commit }, clubData) {
      commit("SET_NEW_CLUBDATA", clubData);
      router.push({ name: "ClubCreateBook" });
    },
    // ??? ?????? ?????????
    createClub({ rootGetters, getters }, books) {
      let clubData = getters.newClubData;
      clubData.books = books;
      axios
        .post(SERVER.URL + SERVER.ROUTES.createClub, clubData, rootGetters.config)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ??????",
            html: "?????? ????????? ?????? ???????????????.<br>???????????? ????????? ???????????? ???????????? ??? ?????????!",
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ??????",
            text: err.response.data.message,
          });
        });
    },
    // ?????? ?????? ??????
    updateClub({ rootGetters, getters, commit }, clubData) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.updateClub + getters.clubId, clubData, rootGetters.config)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          commit("SET_CLUB_IMAGE", clubData.img);
          router.push({ name: "ClubdetailManage", query: { clubId: getters.clubId } });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // ?????? ??????
    deleteClub({ rootGetters, dispatch }, clubId) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteClub + clubId, rootGetters.config)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findClubInfo", clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },

    // ?????? ????????????
    applyToClub({ rootGetters, getters }) {
      axios
        .post(SERVER.URL + "/clubs/" + getters.clubId + "/join", null, rootGetters.config)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ?????? ??????",
            text: getters.clubInfo.name + "??? ?????? ????????? ?????????????????????!",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // ?????? ???????????? ??????
    acceptToClub({ rootGetters, getters, dispatch }, userClubId) {
      axios
        .put(
          SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId,
          null,
          rootGetters.config
        )
        .then(() => {
          dispatch("findApplyList", getters.clubId);
          dispatch("findJoinedList", getters.clubId);
          Swal.fire({
            icon: "success",
            title: "???????????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "???????????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // ?????? ???????????? ??????
    rejectJoin({ rootGetters, getters, dispatch }, userClubId) {
      axios
        .delete(SERVER.URL + "/clubs/" + getters.clubId + "/join/" + userClubId, rootGetters.config)
        .then(() => {
          dispatch("findApplyList", getters.clubId);
          Swal.fire({
            icon: "success",
            title: "???????????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "???????????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // ?????? ???????????? ?????? ??????
    findApplyList({ rootGetters, commit }, clubId) {
      axios
        .get(SERVER.URL + "/clubs/" + clubId + "/users/apply", rootGetters.config)
        .then((res) => {
          commit("SET_CLUBID", clubId);
          commit("SET_APPLY_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // ?????? ????????? ?????? ??????
    findJoinedList({ commit }, clubId) {
      axios
        .get(SERVER.URL + "/clubs/" + clubId + "/users")
        .then((res) => {
          commit("SET_CLUBID", clubId);
          commit("SET_JOINED_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // ?????? ??????
    deleteClubUser({ rootGetters, getters }) {
      axios
        .delete(
          SERVER.URL + SERVER.ROUTES.deleteClub + getters.clubId + "/user",
          rootGetters.config
        )
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubSearchBarPageListItem", query: { clubId: getters.clubId } });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },

    // ??????????????? ?????? ??????
    findQuestionList({ commit }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.questions + clubId)
        .then((res) => {
          // ??????????????? ?????? ??????
          commit("SET_QUESTION_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // ??????????????? ?????? ??????
    registerQuestion({ rootGetters, dispatch }, questionData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.questions + questionData.clubId,
          questionData,
          rootGetters.config
        )
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findQuestionList", questionData.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ??????",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // ??????????????? ?????? ??????
    registerAnswer({ rootGetters, getters, dispatch }, questionData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.questions + questionData.questionId + "/answer",
          questionData,
          rootGetters.config
        )
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "?????? ?????? ??????",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findQuestionList", getters.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "?????? ?????? ??????",
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
