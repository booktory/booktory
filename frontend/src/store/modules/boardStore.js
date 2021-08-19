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
    // 담벼락 게시글 목록 확인
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
    // 담벼락 게시글 등록
    registerBoard({ rootGetters, getters, dispatch }, boardData) {
      axios
        .post(
          SERVER.URL + SERVER.ROUTES.registerBoard + getters.clubId,
          boardData,
          rootGetters.config
        )
        .then(() => {
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
    // 담벼락 게시글 삭제
    deleteBoard({ rootGetters, getters, dispatch }, boardId) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteBoard + boardId, rootGetters.config)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "게시글 삭제 완료",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          dispatch("findBoardList", getters.clubId);
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "게시글 삭제 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default boardStore;
