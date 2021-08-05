<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">로그인</h4>
    <p class="sub-title">이메일과 비밀번호를 입력해주세요</p>
    <div class="input-div m-top-10">
      <label for="email">이메일</label>
      <div>
        <input
          v-model="loginData.email"
          type="text"
          id="email"
          placeholder="booktory@example.com"
        />
      </div>
    </div>
    <div class="input-div">
      <label for="password">비밀번호</label>
      <div>
        <input
          v-model="loginData.password"
          type="password"
          id="password"
          placeholder="비밀번호를 입력해주세요"
        />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickLogin">확인</button>
    <router-link to="password">
      <p class="text-link">비밀번호 찾기</p>
    </router-link>
    <div class="social-login">
      <img src="@/assets/icons/naver.svg" />
      <img src="@/assets/icons/kakao.svg" />
    </div>
    <div class="text-div">
      <p class="font-body-3">아직 회원이 아니신가요?</p>
      <h5 @click="$router.push({ name: 'Register' })">회원가입하기</h5>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import Swal from "sweetalert2";

import IconBase from "../../components/icons/IconBase.vue";
import ArrowLeft from "../../components/icons/ArrowLeft.vue";

export default {
  components: { IconBase, ArrowLeft },
  name: "Login",
  data() {
    return {
      loginData: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    clickLogin() {
      axios
        .post("/users/login", {
          email: this.loginData.email,
          password: this.loginData.password,
        })
        .then((res) => {
          console.log(res.data);
          const Toast = Swal.mixin({
            toast: true,
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            onOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });
          Toast.fire({
            icon: "success",
            title: "로그인에 성공했습니다.",
          });
          // 홈 화면(내 클럽 보기)으로 이동
          router.push({ name: "MyClub" });
        })
        .catch((err) => {
          const Toast = Swal.mixin({
            toast: true,
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: false,
            onOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });
          Toast.fire({
            icon: "error",
            title: err.response.data.message,
          });
        });
    },
  },
};
</script>

<style scoped>
.social-login img {
  width: 4.8em;
  height: 4.8em;
  margin: 5% 1.5%;
}

.text-div * {
  color: var(--grey);
  margin: 0.6em auto;
}
</style>
