<template>
  <div class="container bg-img">
    <TopHeader />
    <ClubList />
    <div class="text-right">
      <span @click="$router.push({ name: 'ClubSearch' })">클럽 찾기</span>&nbsp;|&nbsp;<span
        @click="$router.push({ name: 'ClubCreate' })"
        >클럽 만들기</span
      >
    </div>
    <Navbar :selected="'home'" />
  </div>
</template>

<script>
import ClubList from "@/views/clubs/ClubList.vue";
import Navbar from "@/views/Navbar.vue";
import TopHeader from "@/views/TopHeader.vue";

export default {
  name: "ClubHome",
  components: {
    ClubList,
    Navbar,
    TopHeader,
  },
};
</script>

<style scoped>
.bg-img {
  position: relative;
}
.bg-img:after {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-image: url("./images/club-backgroud.png");
  background-size: cover;
  opacity: 30%;
  z-index: -1;
}
.text-right {
  text-align: right;
  margin: 0 3.5rem;
}
.text-right,
.text-right span {
  font-size: 1.4rem;
  font-weight: bold;
  color: var(--dark-grey);
}
</style>
