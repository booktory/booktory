import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";
var moment = require("moment");

const bookclubStore = {
  namespaced: true,
  state: {
    bookclubList: null,
    nowbookclub: null,
    nextbookclubList: null,
    prebookclubList: null,
    clubId: null,
  },
  getters: {
    bookclubList(state) {
      return state.bookclubList;
    },
    nowbookclub(state) {
      return state.nowbookclub;
    },
    newxtbookclubList(state) {
      return state.nextbookclubList;
    },
    prebookclubList(state) {
      return state.prebookclubList;
    },
    clubId(state) {
      return state.clubId;
    },
  },
  mutations: {
    SET_BOOKCLUB_LIST(state, data) {
      state.bookclubList = data;
    },
    SET_NOWBOOKCLUB(state, data) {
      state.nowbookclub = data;
    },
    SET_NEXTBOOKCLUB_LIST(state, data) {
      state.nextbookclubList = data;
    },
    SET_PREBOOKCLUB_LIST(state, data) {
      state.prebookclubList = data;
    },
    SET_CLUB_ID(state, data) {
      state.clubId = data;
    },
  },
  actions: {
    // 모임/읽을 책 목록 확인
    getBookClubList({ commit }, clubId) {
      let nextbooks = [];
      let prebooks = [];
      axios
        .get(SERVER.URL + SERVER.ROUTES.getBookClubList + clubId)
        .then((res) => {
          commit("SET_BOOKCLUB_LIST", res.data);
          commit("SET_CLUB_ID", clubId);
          let flag = true;
          for (let bookclub of res.data) {
            if (bookclub.endDateTime == null) {
              nextbooks.push(bookclub);
            } else if (moment().subtract(moment(bookclub.endDateTime)) / 1000 / 60 <= 60) {
              flag = false;
              commit("SET_NOWBOOKCLUB", bookclub);
            } else {
              prebooks.push(bookclub);
            }
          }
          if (flag) {
            commit("SET_NOWBOOKCLUB", null);
          }
          commit("SET_NEXTBOOKCLUB_LIST", nextbooks);
          commit("SET_PREBOOKCLUB_LIST", prebooks);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 읽을 책 등록
    createBook({ dispatch }, bookclubData) {
      console.log(dispatch);
      axios
        .post(SERVER.URL + SERVER.ROUTES.createBook, bookclubData)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "읽을 책 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.go(-1);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "읽을 책 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 읽을 책 삭제
    deleteBook({ dispatch }, data) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteBook + data.bookclubId)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "책 삭제 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("getBookClubList", data.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "책 삭제 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },

    // 모임 등록
    createMeeting({ dispatch }, bookclubData) {
      console.log(dispatch);
      axios
        .put(SERVER.URL + SERVER.ROUTES.createMeeting, bookclubData)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "모임 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.go(-1);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "모임 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 모임 취소
    cancelMeeting({ dispatch, getters, commit }, bookclubId) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.cancelMeeting + bookclubId)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "모임 취소 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          commit("SET_NOWBOOKCLUB", null);
          dispatch("getBookClubList", getters.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "모임 취소 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 모임 입장
    attendMeeting({ rootGetters }, bookclubId) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.attendMeeting + bookclubId + "/user",
          null,
          rootGetters.config
        )
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "모임 입장 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "Meeting" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "모임 입장 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 모임 퇴장
    leaveMeeting({ rootGetters }, bookclubId) {
      axios
        .put(
          SERVER.URL + SERVER.ROUTES.leaveMeeting + bookclubId + "/user",
          null,
          rootGetters.config
        )
        .then(() => {
          router.go(-1);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "모임 퇴장 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default bookclubStore;
