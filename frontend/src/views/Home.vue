<template>
  <div class="container m-top">
    <h4 class="title">책토리와 함께하는 <span class="title-orange">독서모임</span></h4>
    <p class="sub-title">책을 읽고 사람들과 함께 생각을 공유해요</p>
    <img class="logo" src="@/assets/images/landing_page.png" alt="Booktory" />
    <button type="button" class="button-2" @click="$router.push({ name: 'Login' })">
      시작하기
    </button>
    <button type="button" class="button-2" @click="$router.push({ name: 'ClubHome' })">
      클럽홈
    </button>
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
  margin: 4rem 0 0;
  object-fit: contain;
}
</style>
