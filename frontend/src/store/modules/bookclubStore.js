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
  },
  actions: {
    getBookclubList({ commit }, clubId) {
      let nextbooks = [];
      let prebooks = [];
      axios
        .get(SERVER.URL + SERVER.ROUTES.getBookClubList + clubId)
        .then((res) => {
          commit("SET_BOOKCLUB_LIST", res.data);
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
    createMeeting({ dispatch }, bookclubData) {
      console.log(dispatch);
      axios
        .put(SERVER.URL + SERVER.ROUTES.createMeeting, bookclubData)
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "모임 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
          // router.push({ name: "ClubdetailMeeting" });
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
    cancelMeeting(bookclubId) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.cancelMeeting + bookclubId)
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "모임 취소 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubdetailMeeting" });
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
  },
};

export default bookclubStore;
