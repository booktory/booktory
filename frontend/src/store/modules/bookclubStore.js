import SERVER from "@/api/api";
import axios from "axios";
// import router from "@/router";
// import Swal from "sweetalert2";

const bookclubStore = {
  namespaced: true,
  state: {
    bookclubList: null,
  },
  getters: {
    bookclubList(state) {
      return state.bookclubList;
    },
  },
  mutations: {
    SET_BOOKCLUB_LIST(state, data) {
      state.bookclubList = data;
    },
  },
  actions: {
    getBookclubList({ commit }, clubId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getBookClubList + clubId)
        .then((res) => {
          commit("SET_BOOKCLUB_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default bookclubStore;
