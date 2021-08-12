import SERVER from "@/api/api";
import axios from "axios";

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
  },
};

export default mybookStore;
