<template>
  <div class="input-div m-top-5">
    <div>
      <input
        type="text"
        placeholder="검색할 클럽명을 입력해주세요"
        @click="$router.push({ name: 'ClubSearchBarPage' })"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubSearchBar.vue",
};
</script>

<style></style>
