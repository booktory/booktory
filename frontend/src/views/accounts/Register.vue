<template>
  <div class="register">
    <div class="container">
      <div class="navbar">
        <img
          class="icon"
          alt="arrow-left"
          src="@/assets/icons/arrow-left.svg"
          @click="$router.go(-1)"
        />
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
      <button type="button" class="button-2 m-top-10" @click="clickRegister">
        다음
      </button>
      <router-link to="password">
        <p class="text-link">이용약관 보기</p>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";

export default {
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
</style>
