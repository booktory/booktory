<template>
  <div class="container m-top">
    <div>
      <h4 class="title">책토리와 함께하는 <span class="title-orange">독서모임</span></h4>
      <p class="sub-title">책을 읽고 사람들과 함께 생각을 공유해요</p>
      <img class="logo" src="@/assets/images/landing_page.png" alt="Booktory" />
      <button type="button" class="button-2" @click="$router.push({ name: 'Login' })">
        시작하기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
.title-orange {
  color: var(--orange);
  font-size: 2rem;
  font-family: "CookieRun-Regular";
}

.logo {
  width: 80%;
  margin: 3rem 0 1rem;
  object-fit: contain;
}
</style>
