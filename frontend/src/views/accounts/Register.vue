<template>
  <div class="container">
    <div>
      <div class="navbar">
        <div class="icon" @click="$router.go(-1)">
          <icon-base><icon-arrow-left /></icon-base>
        </div>
      </div>
      <h4 class="title">회원가입</h4>
      <div class="input-div m-top-5">
        <label for="nickname">닉네임</label>
        <div>
          <input
            v-model.trim="registerData.nickname"
            v-bind:class="{
              error: error.nickname,
              complete: !error.nickname && registerData.nickname.length !== 0,
            }"
            type="text"
            id="nickname"
            maxlength="10"
            placeholder="닉네임을 입력해주세요"
            autocapitalize="none"
            autocorrect="none"
            required
          />
        </div>
        <p v-if="error.nickname" class="message">{{ error.nickname }}</p>
      </div>
      <div class="input-div">
        <label for="email">이메일</label>
        <div>
          <input
            v-model="registerData.email"
            v-bind:class="{
              error: error.email,
              complete: !error.email && registerData.email.length !== 0,
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
            v-model.trim="registerData.password"
            v-bind:class="{
              error: error.password,
              complete: !error.password && registerData.password.length !== 0,
            }"
            type="password"
            id="password"
            maxlength="16"
            placeholder="비밀번호를 입력해주세요"
            required
          />
        </div>
        <p v-if="error.password" class="message">{{ error.password }}</p>
      </div>
      <div class="input-div">
        <label for="passwordConfirm">비밀번호 확인</label>
        <div>
          <input
            v-model.trim="registerData.passwordConfirm"
            v-bind:class="{
              error: error.passwordConfirm,
              complete: !error.passwordConfirm && registerData.passwordConfirm.length !== 0,
            }"
            type="password"
            id="passwordConfirm"
            maxlength="16"
            placeholder="비밀번호를 한 번 더 입력해주세요"
            required
            @keyup.enter="clickRegister"
          />
        </div>
        <p v-if="error.passwordConfirm" class="message">{{ error.passwordConfirm }}</p>
      </div>
      <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickRegister">
        회원가입
      </button>
      <p class="text-link" @click="$router.push({ name: 'BooktoryTos' })">이용약관 보기</p>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  components: {},
  name: "Register",
  data() {
    return {
      registerData: {
        nickname: "",
        email: "",
        password: "",
        passwordConfirm: "",
      },
      error: {
        nickname: false,
        email: false,
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    registerData: {
      deep: true,
      handler() {
        this.checkNicknameForm();
        this.checkEmailForm();
        this.checkPasswordForm();
        this.checkPasswordConfirmForm();
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["register"]),
    // 회원가입 버튼 클릭
    clickRegister() {
      if (this.isSubmit) {
        this.register(this.registerData);
      }
    },
    // 닉네임 유효성 검사
    checkNicknameForm() {
      if (this.registerData.nickname.length > 0 && this.registerData.nickname.length < 2) {
        this.error.nickname = "닉네임이 너무 짧아요! 2자 이상 입력해주세요";
      } else if (this.registerData.nickname.length > 10) {
        this.error.nickname = "닉네임이 너무 길어요! 10자 이하로 입력해주세요";
      } else this.error.nickname = false;
    },
    // 이메일 유효성 검사
    checkEmailForm() {
      if (this.registerData.email.length > 0 && !this.validEmail(this.registerData.email)) {
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
      if (this.registerData.password.length > 0 && this.registerData.password.length < 8) {
        this.error.password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.registerData.password.length > 16) {
        this.error.password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (
        this.registerData.password.length >= 8 &&
        !this.validPassword(this.registerData.password)
      ) {
        this.error.password = "영문, 숫자, 특수문자를 모두 포함해야 해요";
      } else this.error.password = false;
    },
    validPassword(password) {
      // eslint-disable-next-line
      var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{0,}$/;
      return reg.test(password);
    },
    // 비밀번호 확인 유효성 검사
    checkPasswordConfirmForm() {
      if (
        this.registerData.passwordConfirm.length > 0 &&
        this.registerData.password != this.registerData.passwordConfirm
      ) {
        this.error.passwordConfirm = "비밀번호와 일치하지 않아요! 다시 확인해주세요";
      } else this.error.passwordConfirm = false;

      // 유효성 검사 통과 시 로그인 버튼 활성화
      if (
        this.registerData.nickname.length > 0 &&
        this.registerData.email.length > 0 &&
        this.registerData.password.length > 0 &&
        this.registerData.passwordConfirm.length > 0
      ) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
  },
};
</script>

<style scoped></style>
