<template>
  <div class="container">
    <div>
      <h3 class="title">Page Not Found</h3>
      <p class="sub-title">요청하신 페이지를 찾을 수 없어요</p>
      <img class="logo" src="@/assets/images/find_page.png" alt="Page Not Found" />
      <button type="button" class="button-2 m-top-10" @click="$router.push({ name: 'Home' })">
        홈으로 가기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFound",
};
</script>

<style scoped>
.title {
  margin-top: 10rem;
}
.logo {
  width: 50%;
  margin: 2rem 0 -2rem;
  object-fit: contain;
}
</style>
