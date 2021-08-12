import SERVER from "@/api/api";
import axios from "axios";

const searchStore = {
  namespaced: true,
  state: {
    bookList: null,
    clubList: null,
  },
  getters: {
    bookList(state) {
      return state.bookList;
    },
    clubList(state) {
      return state.clubList;
    },
  },
  mutations: {
    SET_BOOKLIST(state, data) {
      state.bookList = data;
    },
    SET_CLUBLIST(state, data) {
      state.clubList = data;
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
    // 클럽명(키워드)로 클럽 검색
    searchClubByNameAndGenre({ commit }, keyword) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.searchClubByNameAndGenre + "?keyword=" + keyword)
        .then((res) => {
          // console.log(res.data);
          commit("SET_CLUBLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default searchStore;
