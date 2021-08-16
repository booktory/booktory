<template>
  <div>
    <div class="input-div">
      <p class="label font-body-4">클럽명으로 찾기</p>
      <div>
        <input type="text" placeholder="검색할 클럽명을 입력해주세요" @click="clickSearchBar" />
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { mapActions } from "vuex";

export default {
  name: "ClubSearchBar",
  methods: {
    ...mapActions("searchStore", ["initClubList"]),
    clickSearchBar() {
      this.initClubList();
      router.push({ name: "ClubSearchBarPage" });
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  margin-left: 1rem;
}
</style>
