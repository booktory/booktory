<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">비밀번호 입력</h4>
      <p class="sub-title">회원 탈퇴를 위해 비밀번호를 입력해주세요</p>
      <div class="input-div m-top-10">
        <label for="password">비밀번호</label>
        <div>
          <input
            v-model.trim="password"
            v-bind:class="{
              error: error.password,
              complete: !error.password && password.length !== 0,
            }"
            type="password"
            id="password"
            maxlength="16"
            placeholder="비밀번호를 입력해주세요"
            required
            @keyup.enter="clickDelete"
          />
        </div>
        <p v-if="error.password" class="message">{{ error.password }}</p>
      </div>
      <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickDelete">
        탈퇴하기
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
  components: {
    TopHeader,
    Navbar,
  },
  name: "DeleteUserConfirm",
  data() {
    return {
      password: "",
      error: {
        password: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    password: {
      handler() {
        this.checkPasswordForm();
      },
    },
  },
  methods: {
    ...mapActions("mypageStore", ["deleteUser"]),
    // 탈퇴하기 버튼 클릭
    clickDelete() {
      if (this.isSubmit) {
        this.deleteUser(this.password);
      }
    },
    // 비밀번호 유효성 검사
    checkPasswordForm() {
      if (this.password.length > 0 && this.password.length < 8) {
        this.error.password = "비밀번호가 너무 짧아요! 8자 이상 입력해주세요";
      } else if (this.password.length > 16) {
        this.error.password = "비밀번호가 너무 길어요! 16자 이하로 입력해주세요";
      } else if (this.password.length >= 8 && !this.validPassword(this.password)) {
        this.error.password = "영문, 숫자, 특수문자를 모두 포함해야 해요";
      } else this.error.password = false;
    },
    validPassword(password) {
      // eslint-disable-next-line
      var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{0,}$/;
      return reg.test(password);
    },
  },
};
</script>

<style scoped></style>
