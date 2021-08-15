import SERVER from "@/api/api";
import axios from "axios";

const rwmStore = {
    namespaced: true,
    state: {
        rwmList: null,
    },
    getters: {
        rwmList(state) {
            return state.rwmList;
        },
    },
    mutations: {
        SET_RWMLIST(state, data) {
            state.rwmList = data;
        },
    },
    actions: {

        findRwmList({ rootGetters, commit }) {
            axios
                .get(SERVER.URL + SERVER.ROUTES.getRwmList, rootGetters.config)
                .then((res) => {
                    commit("SET_RWMLIST", res.data);
                })
                .catch((err) => {
                    console.log(err);
                });
        },
    }
};

export default rwmStore;