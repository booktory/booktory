import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import examples from "./modules/examples.js";
import accountStore from "@/store/modules/accountStore";
import mypageStore from "@/store/modules/mypageStore";

import cookies from "vue-cookies";

export default new Vuex.Store({
  state: {
    authToken: cookies.get("auth-token"),
    user: localStorage.getItem("user"),
  },
  getters: {
    config: (state) => ({ headers: { jwt: state.authToken } }),
  },
  mutations: {
    SET_TOKEN(state, data) {
      state.authToken = data;
      cookies.set("auth-token", data);
    },
    SET_USER(state, data) {
      state.user = data;
      localStorage.setItem("user", data);
    },
  },
  actions: {},
  modules: {
    accountStore,
    mypageStore,
    examples,
  },
});
