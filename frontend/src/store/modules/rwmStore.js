import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";

const rwmStore = {
  namespaced: true,
  state: {
    rwmList: null,
    rwmId: null,
  },
  getters: {
    rwmList(state) {
      return state.rwmList;
    },
    rwmId(state) {
      return state.rwmId;
    }
  },
  mutations: {
    SET_RWMLIST(state, data) {
      state.rwmList = data;
    },
  },
  actions: {
    findRwmList({ commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmList)
        .then((res) => {
          commit("SET_RWMLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    enterRwmRoom({ rootGetters }, rwmEnterData) {
      let rwmBookTitle = {
        title: rwmEnterData.bookTitle,
      };
      axios
        .post(SERVER.URL + SERVER.ROUTES.enterRwmRoom + rwmEnterData.id, rwmBookTitle, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          console.log(rwmEnterData);
          router.push({ name: "RwmRoom" , query: {id: rwmEnterData.id} });
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }
};

export default rwmStore;