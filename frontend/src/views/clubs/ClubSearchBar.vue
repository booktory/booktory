<template>
  <div>
    <div class="input-div">
      <p class="label font-body-4">클럽명으로 찾기</p>
      <div>
        <input
          type="text"
          placeholder="검색할 클럽명을 입력해주세요"
          @click="$router.push({ name: 'ClubSearchBarPage' })"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubSearchBar",
};
</script>

<style scoped>
.label {
  font-weight: bold;
  margin-left: 1rem;
}
</style>
