<template>
  <div class="container">
    <TopHeader :nickname="'책토리'" />
    <h4 class="title">비밀번호 변경</h4>
    <div class="m-top-10">
      <div class="input-div">
        <label for="current_password">기존 비밀번호</label>
        <div>
          <input
            v-model="passwordData.current_password"
            type="password"
            id="current_password"
            placeholder="비밀번호를 입력해주세요"
          />
        </div>
        <p class="message">8자 이상 입력해주세요</p>
      </div>
      <div class="input-div">
        <label for="new_password">신규 비밀번호</label>
        <div>
          <input
            v-model="passwordData.new_password"
            type="password"
            id="new_password"
            placeholder="비밀번호를 한 번 더 입력해주세요"
          />
        </div>
        <p class="message">8자 이상 입력해주세요</p>
      </div>
      <div class="input-div">
        <label for="new_passwordConfirm">신규 비밀번호 확인</label>
        <div>
          <input
            v-model="passwordData.new_passwordConfirm"
            type="password"
            id="new_passwordConfirm"
            placeholder="비밀번호를 한 번 더 입력해주세요"
          />
        </div>
        <p class="message">8자 이상 입력해주세요</p>
      </div>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickUpdatePassword">변경하기</button>
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

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
    };
  },
  methods: {
    clickUpdatePassword() {
      axios
        .post("/users/password", {
          current_password: this.passwordData.current_password,
          new_password: this.passwordData.new_password,
        })
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "비밀번호 변경 완료",
            html: "비밀번호가 변경 되었습니다.<br>바뀐 비밀번호로 다시 로그인 해주세요!",
          });
          console.log(res.data);
          // 로그인 페이지로 이동
          router.push({ name: "Login" });
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "비밀번호 변경 실패",
            text: "비밀번호 변경에 실패했습니다.",
          });
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
