<template>
  <div class="container">
    <h4 class="title">책토리와 함께하는 <mark class="title-orange">독서모임</mark></h4>
    <p class="sub-title">책을 읽고 사람들과 함께 생각을 공유해요</p>
    <img class="logo" src="@/assets/images/booktory.svg" alt="Booktory Logo" />
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
  font-size: 2.4rem;
  background-color: transparent;
}

.logo {
  width: 89.3%;
  height: 41.9%;
  margin: 10% 0;
  object-fit: contain;
}
</style>
