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
    },
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
        });
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
    findRwmParticipant({ rootGetters, commit }, rwmId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmParticipant + rwmId + "/users")
        .then((res) => {
          console.log(res.data);
          let flag = false;
          for (var i = 0; i < res.data.userList.length; i++) {
            if (rootGetters.userId == res.data.userList[i].userId) {
              flag = true;
              break;
            }
          }
          if (!flag) {
            Swal.fire({
              icon: "warning",
              title: "정상적인 접근이 아닙니다",
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: false,
            });
            router.push({ name: "RwmMain" });
          } else {
            commit("SET_PARTICIPANTLIST", res.data);
            let swalHtml = `<div style="margin-bottom: 0.5rem; display:flex; gap: 0.6rem; flex-direction: column; justify-content:flex-start; align-items:flex-start;">`;
            for (var participant of res.data.userList) {
              swalHtml += `
                        <div style="display: flex; gap: 1.2rem; justify-content: flex-start; align-items: center;">
                          <img class="profileImg" src=
                        ${
                          participant.profileImg && participant.profileImg != ""
                            ? participant.profileImg
                            : "https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png"
                        } style="display: inline-block; width: 4rem; height: 4rem; border-radius: 50%;"/>
                          <div style="display:flex; gap: 0.3rem; flex-direction: column; align-items:flex-start;">
                            <div style="font-size: 1.2rem; font-weight: bold;">${
                              participant.userNickName
                            }</div>
                            <div>${participant.bookName} 읽는 중 </div>
                          </div>
                        </div>
                        `;
            }
            swalHtml += `</div>`;
            Swal.fire({
              showCancelButton: false,
              showConfirmButton: false,
              title: "참가자 (" + res.data.userList.length + ")",
              html: swalHtml,
            });
          }
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
        .post(
          SERVER.URL + SERVER.ROUTES.enterRwmRoom + rwmEnterData.id,
          rwmBookTitle,
          rootGetters.config
        )
        .then((res) => {
          console.log(res.data);
          router.push({ name: "RwmRoom", query: { id: rwmEnterData.id } });
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
          router.push({ name: "RwmMain" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};

export default rwmStore;
