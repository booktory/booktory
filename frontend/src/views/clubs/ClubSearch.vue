<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">클럽찾기</h4>
    <span class="sub-title">클럽명 또는 도서 분야로 검색하기</span>
    <ClubSearchBar />
    <ClubSearchKeyword />

    <p class="text-link" @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</p>
    <Navbar :selected="'home'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import ClubSearchBar from "@/views/clubs/ClubSearchBar.vue";
import ClubSearchKeyword from "@/views/clubs/ClubSearchKeyword.vue";

export default {
  name: "ClubSearch",
  components: {
    TopHeader,
    Navbar,
    ClubSearchBar,
    ClubSearchKeyword,
  },
};
</script>

<style scoped>
.text-link {
  text-align: right;
  font-size: 1rem;
  color: var(--grey);
  margin-right: 10%;
}
</style>
