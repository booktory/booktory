import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import examples from "./modules/examples.js";
import accountStore from "@/store/modules/accountStore";
import mypageStore from "@/store/modules/mypageStore";
import clubStore from "@/store/modules/clubStore";

import cookies from "vue-cookies";

export default new Vuex.Store({
  state: {
    authToken: cookies.get("auth-token"),
    userEmail: localStorage.getItem("userEmail"),
    userNickname: localStorage.getItem("userNickname"),
  },
  getters: {
    config: (state) => ({ headers: { jwt: state.authToken } }),
  },
  mutations: {
    SET_TOKEN(state, data) {
      state.authToken = data;
      cookies.set("auth-token", data);
    },
    SET_USER_EMAIL(state, data) {
      state.userEmail = data;
      localStorage.setItem("userEmail", data);
    },
    SET_USER_NICKNAME(state, data) {
      state.userNickname = data;
      localStorage.setItem("userNickname", data);
    },
  },
  actions: {},
  modules: {
    accountStore,
    mypageStore,
    clubStore,
    examples,
  },
});
