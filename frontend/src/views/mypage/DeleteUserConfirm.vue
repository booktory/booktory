<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">비밀번호 입력</h4>
    <p class="sub-title">회원 탈퇴를 위해 비밀번호를 입력해주세요</p>
    <div class="input-div m-top-10">
      <label for="password">비밀번호</label>
      <div>
        <input
          v-model="password"
          type="password"
          id="password"
          placeholder="비밀번호를 입력해주세요"
          @keyup.enter="clickDelete"
        />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickDelete">탈퇴하기</button>
    <Navbar :selected="'mypage'" class="footer" />
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
      nickname: "책토리",
      jwt: "",
      password: "",
    };
  },
  methods: {
    ...mapActions("mypageStore", ["deleteUser"]),
    clickDelete() {
      this.deleteUser(this.password);
    },
  },
};
</script>

<style scoped></style>
