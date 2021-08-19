import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";
import fire from "@/firebase.js";

const accountStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    // 회원가입 처리
    postAuthDataRegister({ commit }, info) {
      let timerInterval;
      Swal.fire({
        title: "인증 메일 발송 중",
        html: "잠시만 기다려주세요.",
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
          commit("");
          router.push({ name: "RegisterEmail", params: { email: info.data.email } });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "회원가입 실패",
            text: err.response.data.message,
          });
        });
    },
    // 회원가입
    register({ dispatch }, registerData) {
      const info = {
        data: registerData,
        location: SERVER.ROUTES.register,
      };
      dispatch("postAuthDataRegister", info);
    },
    // 회원가입 - 추가정보 입력
    registerExtraInfo({ dispatch }, extraData) {
      dispatch("");
      axios
        .patch(SERVER.URL + SERVER.ROUTES.registerExtraInfo, extraData)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "회원가입 완료",
            html: "회원가입이 완료 되었습니다.<br>지금부터 책토리를 이용하실 수 있어요!",
          });
          if (extraData.isSocialUser === "true") {
            router.push({ name: "ClubHome" });
          } else {
            router.push({ name: "Login" });
          }
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "추가정보 입력 실패",
            text: err.response.data.message,
          });
        });
    },
    saveFcmToken({ dispatch }, email) {
      dispatch("");
      const agt = navigator.userAgent.toLowerCase();
      if (agt.indexOf("safari") != -1 || agt.indexOf("iphone") != -1) {
        return;
      }

      let message = fire.messaging();
      message.getToken().then((fcmtoken) => {
        axios
          .post(SERVER.URL + SERVER.ROUTES.registerFCMToken, {
            token: fcmtoken,
            email: email,
          })
          .then()
          .catch((err) => {
            console.log(err);
          });
      });
    },
    // 로그인 처리
    postAuthDataLogin({ commit, dispatch }, info) {
      axios
        .post(SERVER.URL + info.location, info.data)
        .then((res) => {
          commit("SET_TOKEN", res.data.jwt, { root: true });
          commit("SET_USER_EMAIL", res.data.email, { root: true });
          commit("SET_USER_NICKNAME", res.data.nickname, { root: true });
          commit("SET_USER_ID", res.data.id, { root: true });

          // FCM Token 저장
          dispatch("saveFcmToken", res.data.email);

          Swal.fire({
            icon: "success",
            title: "로그인 성공",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "로그인 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: false,
          });
        });
    },
    // 로그인
    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
      };
      dispatch("postAuthDataLogin", info);
    },
    // 소셜 로그인
    socialLogin({ commit, dispatch }, socialDataLogin) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.socialLogin, socialDataLogin)
        .then((res) => {
          commit("SET_TOKEN", res.data.jwt, { root: true });
          commit("SET_USER_EMAIL", res.data.email, { root: true });
          commit("SET_USER_NICKNAME", res.data.nickname, { root: true });
          commit("SET_USER_ID", res.data.id, { root: true });

          let isJoin = res.data.isJoinUser;
          // 회원가입 후 로그인 진행
          if (isJoin) {
            router.push({
              name: "ExtraInfo",
              query: { email: res.data.email, isSocialUser: "true" },
            });
          } else {
            // FCM 토큰 저장
            dispatch("saveFcmToken", res.data.email);
            Swal.fire({
              icon: "success",
              title: "로그인 성공",
              showConfirmButton: false,
              timer: 1000,
              timerProgressBar: true,
            });
            router.push({ name: "ClubHome" });
          }
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "소셜 로그인 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: false,
          });
        });
    },
    // 비밀번호 찾기
    findPassword({ dispatch }, email) {
      dispatch("");
      let timerInterval;
      Swal.fire({
        title: "이메일 발송 중",
        html: "잠시만 기다려주세요.",
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
        .post(SERVER.URL + SERVER.ROUTES.findPassword + email)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "이메일 발송 완료",
            html: "비밀번호 찾기 메일을 보내드렸어요.<br>이메일을 확인해주세요!",
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "이메일 발송 실패",
            text: err.response.data.message,
          });
        });
    },
    // 비밀번호 찾기 - 새로운 비밀번호로 변경
    resetPassword({ dispatch }, passwordData) {
      dispatch("");
      axios
        .patch(SERVER.URL + SERVER.ROUTES.resetPassword, passwordData)
        .then(() => {
          Swal.fire({
            icon: "success",
            title: "비밀번호 변경 완료",
            html: "비밀번호가 변경 되었습니다.<br>바뀐 비밀번호로 다시 로그인 해주세요!",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "Login" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "비밀번호 변경 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default accountStore;
