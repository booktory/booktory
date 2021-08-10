<template>
  <div class="navbar-container">
    <div class="item" @click="$router.push({ name: 'ClubHome' })">
      <p class="sub-item font-body-4">클럽 홈</p>
    </div>
    <div class="item">
      <p class="sub-item font-body-4">모임 일정</p>
    </div>
    <div class="item" @click="$router.push({ name: 'MyBook' })">
      <p class="sub-item font-body-4">담벼락</p>
    </div>
    <div class="item" @click="$router.push({ name: 'MyPage' })">
      <p class="sub-item font-body-4">클럽 관리</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style scoped>
* {
  background-color: var(--white);
}

.navbar-container {
  display: grid;
  padding: 5% 0;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
  align-items: start;
}

.sub-item {
  margin: auto;
  padding: 5px;
}
</style>
