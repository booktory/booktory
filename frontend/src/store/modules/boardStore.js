import SERVER from "@/api/api";
import axios from "axios";
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
    // 게시판 목록 확인
    findBoardList({ commit }, clubId) {
      commit("SET_CLUBID", clubId);
      axios
        .get(SERVER.URL + SERVER.ROUTES.findBoardList + clubId)
        .then((res) => {
          commit("SET_BOARD_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 게시판 게시글 등록
    registerBoard({ rootGetters, getters, dispatch }, boardData) {
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
          dispatch("findBoardList", getters.clubId);
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
