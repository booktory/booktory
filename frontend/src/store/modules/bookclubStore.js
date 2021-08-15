import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
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
          let flag = true;
          for (let bookclub of res.data) {
            let endDate = new Date(bookclub.endDateTime);
            if (bookclub.endDateTime == null) {
              nextbooks.push(bookclub);
            } else if (curDate.getTime() <= endDate.getTime()) {
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
    cancelMeeting({ commit }, bookclubId) {
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
          commit("SET_NOWBOOKCLUB", null);
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
    deleteBook({ dispatch }, data) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteBook + data.bookclubId)
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "책 삭제 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("getBookclubList", data.clubId);
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
    attendMeeting({ rootGetters }, bookclubId) {
      // console.log(dispatch);
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.attendMeeting + bookclubId + "/user",
          null,
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
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
  },
};

export default bookclubStore;
