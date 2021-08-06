<template>
  <div>
    <ClubList />
    <span @click="$router.push({ name: 'ClubSearch' })">클럽 찾기</span> |
    <span @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</span>
    <Navbar :selected="'home'" class="footer" />
  </div>
</template>

<script>
import ClubList from "@/views/clubs/ClubList.vue";
import Navbar from "@/views/Navbar.vue";

export default {
  name: "ClubHome",
  components: {
    ClubList,
    Navbar,
  },
};
</script>

<style scoped>
.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
