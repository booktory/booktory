<template>
  <div class="container m-top">
    <div>
      <h4 class="title">비밀번호 변경</h4>
      <div class="m-top-10">
        <div class="input-div">
          <label for="password">신규 비밀번호</label>
          <div>
            <input
              v-model.trim="passwordData.password"
              v-bind:class="{
                error: error.password,
                complete: !error.password && passwordData.password.length !== 0,
              }"
              type="password"
              id="password"
              maxlength="16"
              placeholder="새로운 비밀번호를 입력해주세요"
              required
            />
          </div>
          <p v-if="error.password" class="message">{{ error.password }}</p>
        </div>
        <div class="input-div">
          <label for="passwordConfirm">신규 비밀번호 확인</label>
          <div>
            <input
              v-model.trim="passwordData.passwordConfirm"
              v-bind:class="{
                error: error.passwordConfirm,
                complete: !error.passwordConfirm && passwordData.passwordConfirm.length !== 0,
              }"
              type="password"
              id="passwordConfirm"
              placeholder="새로운 비밀번호를 한 번 더 입력해주세요"
              @keyup.enter="clickUpdatePassword"
            />
          </div>
          <p v-if="error.passwordConfirm" class="message">{{ error.passwordConfirm }}</p>
        </div>
      </div>
      <button
        type="button"
        class="button-2 m-top-10"
        :disabled="!isSubmit"
        @click="clickUpdatePassword"
      >
        변경하기
      </button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "FindPasswordUpdate",
  data() {
    return {
      passwordData: {
        email: this.$route.query.email,
        password: "",
        passwordConfirm: "",
      },
      error: {
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    passwordData: {
      deep: true,
      handler() {
        this.checkPasswordForm();
        this.checkPasswordConfirmForm();
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["resetPassword"]),
    // 비밀번호 변경하기 버튼 클릭
    clickUpdatePassword() {
      if (this.isSubmit) {
        this.resetPassword(this.passwordData);
      }
    },
    // 새로운 비밀번호 유효성 검사
    checkPasswordForm() {
      if (this.passwordData.password.length > 0 && this.passwordData.password.length < 8) {
        this.error.password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.passwordData.password.length > 16) {
        this.error.password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (
        this.passwordData.password.length >= 8 &&
        !this.validPassword(this.passwordData.password)
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
        this.passwordData.passwordConfirm.length > 0 &&
        this.passwordData.password != this.passwordData.passwordConfirm
      ) {
        this.error.passwordConfirm = "비밀번호와 일치하지 않아요! 다시 확인해주세요";
      } else this.error.passwordConfirm = false;

      // 유효성 검사 통과 시 로그인 버튼 활성화
      if (this.passwordData.password.length > 0 && this.passwordData.passwordConfirm.length > 0) {
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
