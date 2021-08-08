import SERVER from "@/api/api";
import axios from "axios";
// import router from "@/router";
import Swal from "sweetalert2";

const accountStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    postAuthData({ commit }, info) {
      let timerInterval;
      Swal.fire({
        title: "인증 메일 발송 중",
        html: "조금만 기다려주세요.",
        timer: 4000,
        timerProgressBar: true,
        onBeforeOpen: () => {
          Swal.showLoading();
          timerInterval = setInterval(() => {
            const content = Swal.getContent();
            if (content) {
              const b = content.querySelector("b");
              if (b) {
                b.textContent = Swal.getTimerLeft();
              }
            }
          }, 100);
        },
        onClose: () => {
          clearInterval(timerInterval);
        },
      });
      axios
        .post(SERVER.URL + info.location, info.data)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "이메일 발송 완료",
            html: "회원가입 인증 메일을 보내드렸어요.<br>이메일을 확인해주세요!",
          });
          console.log(commit);
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "회원가입 실패",
            text: error.response.data.message,
          });
        });
    },
    // 회원가입
    register({ dispatch }, registerData) {
      const info = {
        data: registerData,
        location: SERVER.ROUTES.register,
      };
      dispatch("postAuthData", info);
    },
  },
};

export default accountStore;
