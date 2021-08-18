import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import accountStore from "@/store/modules/accountStore";
import mypageStore from "@/store/modules/mypageStore";
import mybookStore from "@/store/modules/mybookStore";
import clubStore from "@/store/modules/clubStore";
import searchStore from "@/store/modules/searchStore";
import boardStore from "@/store/modules/boardStore";
import bookclubStore from "@/store/modules/bookclubStore";
import rwmStore from "@/store/modules/rwmStore.js";

import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.$cookies.config("1d");
import router from "@/router";
import Swal from "sweetalert2";
import SERVER from "@/api/api";
import axios from "axios";

export default new Vuex.Store({
  state: {
    authToken: Vue.$cookies.get("auth-token"),
    userEmail: null,
    userNickname: null,
    userId: null,
  },
  getters: {
    config: (state) => ({ headers: { jwt: state.authToken } }),
    userId(state) {
      return state.userId;
    },
  },
  mutations: {
    SET_TOKEN(state, data) {
      state.authToken = data;
      Vue.$cookies.set("auth-token", data);
    },
    SET_USER_EMAIL(state, data) {
      state.userEmail = data;
      localStorage.setItem("userEmail", data);
    },
    SET_USER_NICKNAME(state, data) {
      state.userNickname = data;
      localStorage.setItem("userNickname", data);
    },
    SET_USER_ID(state, data) {
      state.userId = data;
      localStorage.setItem("userId", data);
    },
  },
  actions: {
    fetchUser({ commit }) {
      commit("SET_TOKEN", Vue.$cookies.get("auth-token"));
      commit("SET_USER_EMAIL", localStorage.getItem("userEmail"));
      commit("SET_USER_NICKNAME", localStorage.getItem("userNickname"));
      commit("SET_USER_ID", localStorage.getItem("userId"));
    },
    // 로그아웃
    logout({ getters, commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.logout, getters.config)
        .then(console.log("redis 삭제"))
        .catch((err) => {
          console.log(err);
        });
      commit("SET_TOKEN", null);
      Vue.$cookies.remove("auth-token");
      commit("SET_USER_EMAIL", null);
      localStorage.removeItem("userEmail");
      commit("SET_USER_NICKNAME", null);
      localStorage.removeItem("userNickname");
      commit("SET_USER_ID", null);
      localStorage.removeItem("userId");
      Swal.fire({
        icon: "success",
        title: "로그아웃 완료",
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true,
      });
      router.push({ name: "Login" });
    },
  },
  modules: {
    accountStore,
    mypageStore,
    mybookStore,
    clubStore,
    searchStore,
    boardStore,
    bookclubStore,
    rwmStore,
  },
});
