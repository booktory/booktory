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
    // 클럽명(키워드)로 클럽 검색
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
    // 장르로 클럽 검색
    searchClubByGenre({ commit }, genre) {
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
