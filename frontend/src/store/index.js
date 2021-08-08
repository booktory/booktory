import Vue from "vue";
import Vuex from "vuex";

import examples from "./modules/examples.js";
import accountStore from "@/store/modules/accountStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    accountStore,
    examples,
  },
});
