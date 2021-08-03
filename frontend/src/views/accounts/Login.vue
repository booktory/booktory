<template>
  <div class="login">
    <div class="container">
      <div class="navbar">
        <img
          class="icon"
          alt="arrow-left"
          src="@/assets/icons/arrow-left.svg"
          @click="$router.go(-1)"
        />
      </div>
      <h4 class="title">로그인</h4>
      <p class="sub-title">이메일과 비밀번호를 입력해주세요</p>
      <div class="input-div m-top-10">
        <label for="email">이메일</label>
        <div>
          <input v-model="email" type="text" id="email" placeholder="booktory@example.com" />
        </div>
      </div>
      <div class="input-div">
        <label for="password">비밀번호</label>
        <div>
          <input
            v-model="password"
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
        <h5 @click="$router.push({ name: 'Signup' })">회원가입하기</h5>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    clickLogin() {
      axios
        .post("/users/login", {
          email: this.email,
          password: this.password,
        })
        .then((res) => {
          alert("로그인 성공");
          console.log(res.data);
        })
        .catch((error) => {
          alert("로그인 실패");
          console.log(error);
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
