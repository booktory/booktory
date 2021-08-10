<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">비밀번호 찾기</h4>
    <p class="sub-title">가입하신 이메일을 입력해주세요</p>
    <div class="input-div m-top-5">
      <label for="user_email">이메일</label>
      <div>
        <input
          v-model="email"
          v-bind:class="{
            error: error.email,
            complete: !error.email && email.length !== 0,
          }"
          type="email"
          id="email"
          placeholder="이메일을 입력해주세요"
          autocapitalize="none"
          autocorrect="none"
          required
          @keyup.enter="clickFindPassword"
        />
      </div>
      <p v-if="error.email" class="message">{{ error.email }}</p>
    </div>
    <button
      type="button"
      class="button-2 m-top-10"
      :disabled="!isSubmit"
      @click="clickFindPassword"
    >
      비밀번호 찾기
    </button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "FindPassword",
  data() {
    return {
      email: "",
      error: {
        email: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    email: {
      handler() {
        this.checkEmailForm();
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["findPassword"]),
    // 비밀번호 찾기 버튼 클릭
    clickFindPassword() {
      if (this.isSubmit) {
        this.findPassword(this.email);
      }
    },
    // 이메일 유효성 검사
    checkEmailForm() {
      if (this.email.length > 0 && !this.validEmail(this.email)) {
        this.error.email = "이메일 형식을 올바르게 입력해주세요";
      } else this.error.email = false;

      // 유효성 검사 통과 시 로그인 버튼 활성화
      if (this.email.length > 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
    validEmail(email) {
      // eslint-disable-next-line
      var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      return reg.test(email);
    },
  },
};
</script>

<style scoped></style>
