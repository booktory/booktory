<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">비밀번호 찾기</h4>
    <p class="sub-title">가입하신 이메일을 입력해주세요</p>
    <div class="input-div m-top-10">
      <label for="user_email">이메일</label>
      <div>
        <input type="text" id="user_email" placeholder="booktory@example.com" />
      </div>
      <p class="message">이메일 형식으로 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10">확인</button>
  </div>
</template>

<script>
export default {
  components: {},
  name: "FindPassword",
};
</script>

<style scoped></style>
