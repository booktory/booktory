<template>
  <div class="input-div">
    <p class="label font-body-4">읽을 책 찾기</p>
    <div class="search-div">
      <div class="icon search-icon" @click="clickSearch">
        <icon-base :iconColor="'var(--grey)'"><icon-search /></icon-base>
      </div>
      <input
        v-model="keyword"
        type="text"
        placeholder="검색어를 입력해주세요"
        @keyup.enter="clickSearch"
      />
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import IconSearch from "@/components/icons/IconSearch.vue";

export default {
  name: "ClubCreateBookBar",
  components: {
    IconSearch,
  },
  data() {
    return {
      keyword: "",
    };
  },
  watch: {},
  methods: {
    ...mapActions("searchStore", ["searchBookByKeyword"]),
    clickSearch() {
      this.searchBookByKeyword(this.keyword);
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  margin-left: 1rem;
}
.search-div {
  position: relative;
}
.search-icon {
  position: absolute;
  top: 0.6rem;
  right: 1rem;
}
</style>
