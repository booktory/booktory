<template>
  <div class="input-div m-top-10">
    <div>
      <input
        type="text"
        placeholder="검색할 클럽명을 입력해주세요"
        @click="$router.push({ name: 'ClubSearchBarPage' })"
      />
    </div>
    <icon-base style="width: 5%"><HomeIcon /></icon-base>
  </div>
</template>

<script>
import IconBase from "../../components/icons/IconBase.vue";
import HomeIcon from "../../components/icons/HomeIcon.vue";

export default {
  name: "ClubSearchBar.vue",
  components: { IconBase, HomeIcon },
};
</script>

<style></style>
