import SERVER from "@/api/api";
import axios from "axios";

const searchStore = {
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
    // 키워드로 책 검색
    searchBookByKeyword({ commit }, keyword) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.searchBookByKeyword + "?keyword=" + keyword)
        .then((res) => {
          commit("SET_BOOKLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default searchStore;
