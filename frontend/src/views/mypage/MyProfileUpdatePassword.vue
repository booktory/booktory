<template>
  <div class="container">
    <div>
      <TopHeader :nickname="'책토리'" />
      <h4 class="title">비밀번호 변경</h4>
      <div class="m-top-10">
        <div class="input-div">
          <label for="current_password">기존 비밀번호</label>
          <div>
            <input
              v-model="passwordData.current_password"
              v-bind:class="{
                error: error.current_password,
                complete: !error.current_password && passwordData.current_password.length !== 0,
              }"
              type="password"
              id="current_password"
              maxlength="16"
              placeholder="기존 비밀번호를 입력해주세요"
              required
            />
          </div>
          <p v-if="error.current_password" class="message">{{ error.current_password }}</p>
        </div>
        <div class="input-div">
          <label for="new_password">신규 비밀번호</label>
          <div>
            <input
              v-model="passwordData.new_password"
              v-bind:class="{
                error: error.new_password,
                complete: !error.new_password && passwordData.new_password.length !== 0,
              }"
              type="password"
              id="new_password"
              maxlength="16"
              placeholder="새로운 비밀번호를 입력해주세요"
              required
            />
          </div>
          <p v-if="error.new_password" class="message">{{ error.new_password }}</p>
        </div>
        <div class="input-div">
          <label for="new_passwordConfirm">신규 비밀번호 확인</label>
          <div>
            <input
              v-model="passwordData.new_passwordConfirm"
              v-bind:class="{
                error: error.new_passwordConfirm,
                complete:
                  !error.new_passwordConfirm && passwordData.new_passwordConfirm.length !== 0,
              }"
              type="password"
              id="new_passwordConfirm"
              placeholder="새로운 비밀번호를 한 번 더 입력해주세요"
              @keyup.enter="clickUpdatePassword"
            />
          </div>
          <p v-if="error.new_passwordConfirm" class="message">{{ error.new_passwordConfirm }}</p>
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
      <Navbar :selected="'mypage'" class="footer" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions } from "vuex";

export default {
  name: "MyProfileUpdatePassword",
  components: {
    TopHeader,
    Navbar,
  },
  data() {
    return {
      passwordData: {
        current_password: "",
        new_password: "",
        new_passwordConfirm: "",
      },
      error: {
        current_password: false,
        new_password: false,
        new_passwordConfirm: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    passwordData: {
      deep: true,
      handler() {
        this.checkCurrentPasswordForm();
        this.checkPasswordForm();
        this.checkPasswordConfirmForm();
      },
    },
  },
  methods: {
    ...mapActions("mypageStore", ["changePassword"]),
    // 비밀번호 변경하기 버튼 클릭
    clickUpdatePassword() {
      if (this.isSubmit) {
        this.changePassword(this.passwordData);
      }
    },
    // 기존 비밀번호 유효성 검사
    checkCurrentPasswordForm() {
      if (
        this.passwordData.current_password.length > 0 &&
        this.passwordData.current_password.length < 8
      ) {
        this.error.current_password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.passwordData.current_password.length > 16) {
        this.error.current_password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (
        this.passwordData.current_password.length >= 8 &&
        !this.validPassword(this.passwordData.current_password)
      ) {
        this.error.current_password = "영문, 숫자, 특수문자를 모두 포함해야 해요";
      } else this.error.current_password = false;
    },
    // 새로운 비밀번호 유효성 검사
    checkPasswordForm() {
      if (this.passwordData.new_password.length > 0 && this.passwordData.new_password.length < 8) {
        this.error.new_password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.passwordData.new_password.length > 16) {
        this.error.new_password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (
        this.passwordData.new_password.length >= 8 &&
        !this.validPassword(this.passwordData.new_password)
      ) {
        this.error.new_password = "영문, 숫자, 특수문자를 모두 포함해야 해요";
      } else this.error.new_password = false;
    },
    validPassword(password) {
      // eslint-disable-next-line
      var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{0,}$/;
      return reg.test(password);
    },
    // 비밀번호 확인 유효성 검사
    checkPasswordConfirmForm() {
      if (
        this.passwordData.new_passwordConfirm.length > 0 &&
        this.passwordData.new_password != this.passwordData.new_passwordConfirm
      ) {
        this.error.new_passwordConfirm = "비밀번호와 일치하지 않아요! 다시 확인해주세요";
      } else this.error.new_passwordConfirm = false;

      // 유효성 검사 통과 시 로그인 버튼 활성화
      if (
        this.passwordData.current_password.length > 0 &&
        this.passwordData.new_password.length > 0 &&
        this.passwordData.new_passwordConfirm.length > 0
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
