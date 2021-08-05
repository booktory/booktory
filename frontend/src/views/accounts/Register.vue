<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">회원가입</h4>
    <p class="sub-title">회원정보를 입력해주세요</p>
    <div class="input-div m-top-10">
      <label for="nickname">닉네임</label>
      <div>
        <input
          v-model="registerData.nickname"
          type="text"
          id="nickname"
          placeholder="닉네임을 입력해주세요"
        />
      </div>
    </div>
    <div class="input-div">
      <label for="email">이메일</label>
      <div>
        <input
          v-model="registerData.email"
          type="text"
          id="email"
          placeholder="booktory@example.com"
        />
      </div>
      <p class="message">이메일 형식으로 입력해주세요</p>
    </div>
    <div class="input-div">
      <label for="password">비밀번호</label>
      <div>
        <input
          v-model="registerData.password"
          type="password"
          id="password"
          placeholder="비밀번호를 입력해주세요"
        />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
    </div>
    <div class="input-div">
      <label for="passwordConfirm">비밀번호 확인</label>
      <div>
        <input
          v-model="registerData.passwordConfirm"
          type="password"
          id="passwordConfirm"
          placeholder="비밀번호를 한 번 더 입력해주세요"
        />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickRegister">다음</button>
    <p class="text-link">이용약관 보기</p>
  </div>
</template>

<script>
import router from "@/router";
// import axios from "axios";
// import Swal from "sweetalert2";

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
    };
  },
  methods: {
    clickRegister() {
      router.push({ name: "ExtraInfo" });
      /*
      let timerInterval;
      Swal.fire({
        title: "인증 메일 발송 중",
        html: "조금만 기다려주세요.",
        timer: 4000,
        timerProgressBar: true,
        onBeforeOpen: () => {
          Swal.showLoading();
          timerInterval = setInterval(() => {
            const content = Swal.getContent();
            if (content) {
              const b = content.querySelector("b");
              if (b) {
                b.textContent = Swal.getTimerLeft();
              }
            }
          }, 100);
        },
        onClose: () => {
          clearInterval(timerInterval);
        },
      });
      axios
        .post("/users", {
          nickname: this.registerData.nickname,
          email: this.registerData.email,
          password: this.registerData.password,
        })
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "이메일 발송 완료",
            html: "회원가입 인증 메일을 보내드렸어요.<br>이메일을 확인해주세요!",
          });
          console.log(res.data);
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "회원가입 실패",
            text: "회원가입에 실패했습니다!",
          });
          console.log(error);
        });
        */
    },
  },
};
</script>

<style scoped></style>
