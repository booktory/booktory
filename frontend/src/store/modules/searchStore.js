import SERVER from "@/api/api";
import axios from "axios";

const searchStore = {
  namespaced: true,
  state: {
    genreList: [
      { id: 1, name: "소설", isSelect: false },
      { id: 2, name: "장르소설", isSelect: false },
      { id: 3, name: "희곡", isSelect: false },
      { id: 4, name: "시", isSelect: false },
      { id: 5, name: "고전", isSelect: false },
      { id: 6, name: "만화", isSelect: false },
      { id: 7, name: "에세이", isSelect: false },
      { id: 8, name: "경제경영", isSelect: false },
      { id: 9, name: "자기계발", isSelect: false },
      { id: 10, name: "외국어", isSelect: false },
      { id: 11, name: "IT", isSelect: false },
      { id: 12, name: "인문학", isSelect: false },
      { id: 13, name: "사회과학", isSelect: false },
      { id: 14, name: "역사", isSelect: false },
      { id: 15, name: "자연과학", isSelect: false },
      { id: 16, name: "예술", isSelect: false },
      { id: 17, name: "대중문화", isSelect: false },
      { id: 18, name: "종교/역학", isSelect: false },
      { id: 19, name: "기타", isSelect: false },
    ],
    bookList: null,
    clubList: null,
  },
  getters: {
    genreList(state) {
      return state.genreList;
    },
    bookList(state) {
      return state.bookList;
    },
    clubList(state) {
      return state.clubList;
    },
  },
  mutations: {
    SET_GENRE_LIST(state, data) {
      state.genreList = data;
    },
    SET_BOOK_LIST(state, data) {
      state.bookList = data;
    },
    SET_CLUB_LIST(state, data) {
      state.clubList = data;
    },
  },
  actions: {
    // 검색된 책 목록 초기화
    initBookList({ commit }) {
      commit("SET_BOOK_LIST", null);
    },
    // 키워드로 책 검색
    searchBookByKeyword({ commit }, keyword) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.searchBookByKeyword + "?keyword=" + keyword)
        .then((res) => {
          commit("SET_BOOK_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 클럽명으로 클럽 검색
    searchClubByName({ commit }, keyword) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.searchClubByNameAndGenre + "?keyword=" + keyword)
        .then((res) => {
          commit("SET_CLUB_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 도서 분야로 클럽 검색
    searchClubByGenre({ commit }, genreList) {
      let genre = "";
      for (var i = 0; i < genreList.length; i++) {
        if (genreList[i].isSelect) {
          genre += genreList[i].name + "%2B";
        }
      }
      axios
        .get(SERVER.URL + SERVER.ROUTES.searchClubByNameAndGenre + "?genre=" + genre)
        .then((res) => {
          commit("SET_CLUB_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default searchStore;
