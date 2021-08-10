<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">클럽찾기</h4>
    <span class="font-body-2 sub-title">클럽명 또는 도서 분야로 검색하기</span>
    <ClubSearchBar />
    <ClubSearchKeyword />
    <ClubSearchResult />
    <p class="text-link" @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</p>
  </div>
</template>

<script>
import ClubSearchBar from "@/views/clubs/ClubSearchBar.vue";
import ClubSearchKeyword from "@/views/clubs/ClubSearchKeyword.vue";
import ClubSearchResult from "@/views/clubs/ClubSearchResult.vue";

export default {
  name: "ClubSearch",
  components: {
    ClubSearchBar,
    ClubSearchKeyword,
    ClubSearchResult,
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
