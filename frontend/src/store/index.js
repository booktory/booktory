import Vue from "vue";
import Vuex from "vuex";

import examples from "./modules/examples.js";
import accountStore from "@/store/modules/accountStore";

import cookies from "vue-cookies";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authToken: cookies.get("auth-token"),
  },
  getters: {},
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token;
      cookies.set("auth-token", token);
    },
  },
  actions: {},
  modules: {
    accountStore,
    examples,
  },
});
