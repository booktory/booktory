<template>
  <div class="container m-top">
    <div>
      <h4 class="title">회원가입 <span class="title-orange">인증 메일</span> 발송 완료</h4>
      <p class="sub-title">도착한 이메일을 확인해주세요</p>
      <img class="logo" src="@/assets/images/email_page.png" alt="인증메일 발송" />
      <button type="button" class="button-2" @click="clickMail">메일함 가기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterEmail",
  data() {
    return {
      email: this.$route.params.email,
    };
  },
  methods: {
    clickMail() {
      let idx = this.email.indexOf("@");
      let site = this.email.slice(idx);
      location.href = "https://" + site;
    },
  },
};
</script>

<style scoped>
.title-orange {
  color: var(--orange);
  font-size: 2rem;
  font-family: "CookieRun-Regular";
}

.logo {
  width: 60%;
  margin: 3rem 0 1rem;
  object-fit: contain;
}
</style>
