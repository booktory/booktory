<template>
  <div class="container bg-img">
    <TopHeader />
    <ClubList />
    <div class="text-right">
      <span class="text-link" @click="$router.push({ name: 'ClubSearch' })">클럽 찾기 | </span>
      <span class="text-link" @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</span>
    </div>
    <Navbar :selected="'home'" class="footer" />
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
  height: 100vh;
  background-image: linear-gradient(to bottom, var(--white) 0%, var(--light-grey) 100%),
    url("./images/club-backgroud.png");
  background-blend-mode: multiply;
  background-size: cover;
}
.text-right {
  text-align: right;
  margin-right: 10%;
}
.text-link {
  font-size: 1.5rem;
  color: var(--white);
}

.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
