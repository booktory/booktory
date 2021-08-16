<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">클럽 찾기</h4>
      <ClubSearchBar />
      <ClubSearchGenre class="search-list" />
      <p class="text-right font-body-4">원하는 클럽이 없다면?</p>
      <h5 class="text-right" @click="$router.push({ name: 'ClubCreate' })">새 클럽 만들기</h5>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import ClubSearchBar from "@/views/clubs/ClubSearchBar.vue";
import ClubSearchGenre from "@/views/clubs/ClubSearchGenre.vue";

export default {
  name: "ClubSearch",
  components: {
    TopHeader,
    Navbar,
    ClubSearchBar,
    ClubSearchGenre,
  },
};
</script>

<style scoped>
.search-list {
  margin-bottom: 2rem;
}
.text-right {
  text-align: right;
  margin: 0 4rem 0.3rem 0;
  color: var(--dark-grey);
}
</style>
