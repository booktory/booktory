import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const boardStore = {
  namespaced: true,
  state: {
    clubId: null,
    boardList: null,
  },
  getters: {
    clubId(state) {
      return state.clubId;
    },
    boardList(state) {
      return state.boardList;
    },
  },
  mutations: {
    SET_CLUBID(state, data) {
      state.clubId = data;
    },
    SET_BOARD_LIST(state, data) {
      state.boardList = data;
    },
  },
  actions: {
    setClubId({ commit }, culbId) {
      commit("SET_CLUBID", culbId);
    },
    // 게시판 목록 확인
    getBoard({ getters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getBoard + getters.clubId)
        .then((res) => {
          commit("SET_BOARD_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 게시판 게시글 등록
    registerBoard({ rootGetters, getters }, boardData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.registerBoard + getters.clubId,
          boardData,
          rootGetters.config
        )
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "게시글 등록 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.go(0);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "게시글 등록 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
    // 게시판 게시글 삭제
    // deleteBoard({ getters, commit }) {
    //   axios
    //     .delete(SERVER.URL + SERVER.ROUTES.deleteBoard + getters.boardId)
    //     .then((res) => {
    //       commit("", res.data);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
  },
};

export default boardStore;
