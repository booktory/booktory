import SERVER from "@/api/api";
import axios from "axios";
// import router from "@/router";
import Swal from "sweetalert2";

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
          let curDate = new Date();
          for (let bookclub of res.data) {
            let endDate = new Date(bookclub.endDateTime);
            if (bookclub.endDateTime == null) {
              nextbooks.push(bookclub);
            } else if (curDate.getTime() <= endDate.getTime()) {
              commit("SET_NOWBOOKCLUB", bookclub);
            } else {
              prebooks.push(bookclub);
            }
          }
          commit("SET_NEXTBOOKCLUB_LIST", nextbooks);
          commit("SET_PREBOOKCLUB_LIST", prebooks);
        })
        .catch((err) => {
          console.log(err);
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
