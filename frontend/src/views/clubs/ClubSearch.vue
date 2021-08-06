<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><arrow-left /></icon-base>
      </div>
    </div>
    <h4>클럽찾기</h4>
    <span class="font-body-2">클럽명 또는 도서 분야로 검색하기</span>
    <ClubSearchBar />
    <ClubSearchKeyword />
    <ClubSearchResult />
    <p class="club-create" @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</p>
  </div>
</template>

<script>
import ClubSearchBar from "@/views/clubs/ClubSearchBar.vue";
import ClubSearchKeyword from "@/views/clubs/ClubSearchKeyword.vue";
import ClubSearchResult from "@/views/clubs/ClubSearchResult.vue";
import IconBase from "../../components/icons/IconBase.vue";
import ArrowLeft from "../../components/icons/ArrowLeft.vue";

export default {
  name: "ClubSearch",
  components: {
    ClubSearchBar,
    ClubSearchKeyword,
    ClubSearchResult,
    IconBase,
    ArrowLeft,
  },
};
</script>

<style scoped>
.club-create {
  text-align: right;
  margin-right: 10%;
}
</style>
