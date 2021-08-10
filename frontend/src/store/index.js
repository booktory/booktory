import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import examples from "./modules/examples.js";
import accountStore from "@/store/modules/accountStore";
import mypageStore from "@/store/modules/mypageStore";
import clubStore from "@/store/modules/clubStore";

import VueCookies from "vue-cookies";
Vue.use(VueCookies);
Vue.$cookies.config("1d");

export default new Vuex.Store({
  state: {
    authToken: Vue.$cookies.get("auth-token"),
    userEmail: null,
    userNickname: null,
  },
  getters: {
    config: (state) => ({ headers: { jwt: state.authToken } }),
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
  },
  actions: {
    fetchUser({ commit }) {
      commit("SET_USER_EMAIL", localStorage.getItem("userEmail"));
      commit("SET_USER_NICKNAME", localStorage.getItem("userNickname"));
    },
  },
  modules: {
    accountStore,
    mypageStore,
    clubStore,
    examples,
  },
});
