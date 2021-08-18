import SERVER from "@/api/api";
import axios from "axios";
import Swal from "sweetalert2";

const mybookStore = {
  namespaced: true,
  state: {
    bookList: null,
  },
  getters: {
    bookList(state) {
      return state.bookList;
    },
  },
  mutations: {
    SET_BOOKLIST(state, data) {
      state.bookList = data;
    },
  },
  actions: {
    // 내가 읽은 책 확인
    findBookList({ rootGetters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getReadBooks, rootGetters.config)
        .then((res) => {
          commit("SET_BOOKLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 읽은 책에 코멘트 달기
    registerBookMemo({ rootGetters }, memoData) {
      axios
        .put(SERVER.URL + SERVER.ROUTES.registerBookMemo, memoData, rootGetters.config)
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "메모 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "메모 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 읽은 책에 작성한 코멘트 삭제
    cancelBookMemo({ rootGetters }, bookId) {
      axios
        .patch(SERVER.URL + SERVER.ROUTES.cancelBookMemo + bookId, null, rootGetters.config)
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "메모 삭제 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          console.log(res.data);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "메모 삭제 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default mybookStore;
