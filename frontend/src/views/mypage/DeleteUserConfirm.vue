<template>
  <div class="container">
    <TopHeader :nickname="nickname" />
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
        />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickDelete">
      탈퇴하기
    </button>
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import router from "@/router";
import axios from "axios";
import Swal from "sweetalert2";

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
    clickDelete() {
      axios
        .delete("/users", {
          jwt: this.jwt,
          password: this.password,
        })
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "회원 탈퇴 완료",
            html: "회원 탈퇴가 완료 되었습니다.",
          });
          console.log(res.data);
          router.push({ name: "Login" });
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "회원 탈퇴 실패",
            text: "회원 탈퇴에 실패했습니다.",
          });
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
