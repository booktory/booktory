<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">로그인</h4>
    <div class="input-div m-top-5">
      <label for="email">이메일</label>
      <div>
        <input
          v-model="loginData.email"
          v-bind:class="{
            error: error.email,
            complete: !error.email && loginData.email.length !== 0,
          }"
          type="email"
          id="email"
          placeholder="이메일을 입력해주세요"
          autocapitalize="none"
          autocorrect="none"
          required
        />
      </div>
      <p v-if="error.email" class="message">{{ error.email }}</p>
    </div>
    <div class="input-div">
      <label for="password">비밀번호</label>
      <div>
        <input
          v-model="loginData.password"
          v-bind:class="{
            error: error.password,
            complete: !error.password && loginData.password.length !== 0,
          }"
          type="password"
          id="password"
          maxlength="16"
          placeholder="비밀번호를 입력해주세요"
          required
          @keyup.enter="clickLogin"
        />
      </div>
      <p v-if="error.password" class="message">{{ error.password }}</p>
    </div>
    <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickLogin">
      로그인
    </button>
    <router-link to="password">
      <p class="text-link">비밀번호 찾기</p>
    </router-link>
    <div class="social-login">
      <img src="@/assets/icons/naver.svg" />
      <img src="@/assets/icons/kakao.svg" />
    </div>
    <div class="text-div">
      <p class="font-body-3">아직 회원이 아니신가요?</p>
      <h5 class="register-btn" @click="$router.push({ name: 'Register' })">회원가입하기</h5>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Login",
  data() {
    return {
      loginData: {
        email: "",
        password: "",
      },
      error: {
        email: false,
        password: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    loginData: {
      deep: true,
      handler() {
        this.checkEmailForm();
        this.checkPasswordForm();
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["login"]),
    // 로그인 버튼 클릭
    clickLogin() {
      if (this.isSubmit) {
        this.login(this.loginData);
      }
    },
    // 이메일 유효성 검사
    checkEmailForm() {
      if (this.loginData.email.length > 0 && !this.validEmail(this.loginData.email)) {
        this.error.email = "이메일을 올바른 형식으로 입력해주세요";
      } else this.error.email = false;
    },
    validEmail(email) {
      var reg =
        // eslint-disable-next-line
        /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      return reg.test(email);
    },
    // 비밀번호 유효성 검사
    checkPasswordForm() {
      if (this.loginData.password.length > 0 && this.loginData.password.length < 8) {
        this.error.password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.loginData.password.length > 16) {
        this.error.password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (
        this.loginData.password.length >= 8 &&
        !this.validPassword(this.loginData.password)
      ) {
        this.error.password = "영문, 숫자, 특수문자를 모두 포함해야 해요";
      } else this.error.password = false;

      // 유효성 검사 통과 시 로그인 버튼 활성화
      if (this.loginData.email.length > 0 && this.loginData.password.length > 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
    validPassword(password) {
      // eslint-disable-next-line
      var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{0,}$/;
      return reg.test(password);
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
.register-btn {
  display: inline-block;
  margin: 0.2rem;
}
</style>
