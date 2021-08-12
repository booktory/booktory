import SERVER from "@/api/api";
import axios from "axios";

const searchStore = {
  namespaced: true,
  state: {
    genreList: [
      "소설",
      "장르소설",
      "희곡",
      "시",
      "고전",
      "만화",
      "에세이",
      "경제경영",
      "자기계발",
      "외국어",
      "IT",
      "인문학",
      "사회과학",
      "역사",
      "자연과학",
      "예술",
      "대중문화",
      "종교/역학",
      "기타",
    ],
    bookList: null,
  },
  getters: {
    genreList(state) {
      return state.genreList;
    },
    bookList(state) {
      return state.bookList;
    },
  },
  mutations: {
    SET_GENRELIST(state, data) {
      state.genreList = data;
    },
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
