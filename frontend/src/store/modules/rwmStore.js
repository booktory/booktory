import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";


const rwmStore = {
  namespaced: true,
  state: {
    rwmRoomInfo: null,
    rwmList: null,
    rwmParticipant: null,
  },
  getters: {
    rwmRoomInfo(state) {
      return state.rwmRoomInfo;
    },
    rwmList(state) {
      return state.rwmList;
    },
    rwmParticipant(state) {
      return state.rwmParticipant;
    }
  },
  mutations: {
    SET_RWMROOMINFO(state, data) {
      state.rwmRoomInfo = data;
    },
    SET_RWMLIST(state, data) {
      state.rwmList = data;
    },
    SET_PARTICIPANTLIST(state, data) {
      state.rwmParticipant = data;
    },
  },
  actions: {
    findRwmRoomInfo({ commit }, rwmId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmRoomInfo + rwmId)
        .then((res) => {
          commit("SET_RWMROOMINFO", res.data);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        })
    },
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
    findRwmParticipant({ commit }, rwmId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmParticipant + rwmId + "/users")
        .then((res) => {
          console.log(res.data);
          commit("SET_PARTICIPANTLIST", res.data);
          let swalHtml = `<div style="display:flex; flex-direction: column; justify-content:flex-start; align-items:flex-start;">`;
          for (var participant of res.data.userList) {
            swalHtml += `
                        <div style="margin: 0.3rem; display:flex; justify-content:flex-start; align-items:center;">
                          <img src="` + participant.profileImg + `" style="display: inline-block; width: 4em; height: 4em; border-radius: 10em;"/>
                          <div style="margin-left: 1rem; display:flex; flex-direction: column; justify-content:flex-start; align-items:flex-start;">
                            <div><b>` + participant.userNickName + `</b></div>
                            <div> ` + participant.bookName + ` 읽는중 </div>
                          </div>
                        </div>
                        `
          }
          swalHtml += `</div>`;
          Swal.fire({
            showCancelButton: false,
            showCloseButton: true,
            showConfirmButton: false,
            title: "참가자("+res.data.userList.length+")",
            html: swalHtml,
          });
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
          router.push({ name: "RwmRoom" , query: {id: rwmEnterData.id} });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    exitRwmRoom({ rootGetters }, rwmId) {

      axios
        .delete(SERVER.URL + SERVER.ROUTES.exitRwmRoom + rwmId, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          router.push({ name: 'RwmMain' });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
};

export default rwmStore;