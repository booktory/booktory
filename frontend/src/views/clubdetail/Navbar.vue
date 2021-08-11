<template>
  <div class="navbar-container">
    <div class="item" @click="$router.push({ name: 'ClubdetailHome' })">
      <p :class="{ orange: selected == 'home' }" class="sub-item font-body-4">클럽 홈</p>
    </div>
    <div class="item" @click="$router.push({ name: 'ClubdetailMetting' })">
      <p :class="{ orange: selected == 'meeting' }" class="sub-item font-body-4">모임 일정</p>
    </div>
    <div class="item" @click="$router.push({ name: 'ClubdetailTalk' })">
      <p :class="{ orange: selected == 'talk' }" class="sub-item font-body-4">담벼락</p>
    </div>
    <div class="item" @click="$router.push({ name: 'ClubdetailManage' })">
      <p :class="{ orange: selected == 'manage' }" class="sub-item font-body-4">클럽 관리</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  props: {
    selected: {
      type: String,
    },
  },
};
</script>

<style scoped>
* {
  background-color: var(--white);
}

.navbar-container {
  display: grid;
  padding: 1em 0;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
  align-items: center;
}

.sub-item {
  margin: auto;
  padding: 5px;
}

.orange {
  color: var(--orange);
  font-weight: bold;
}
</style>
