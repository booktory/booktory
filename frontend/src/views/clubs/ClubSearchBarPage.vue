<template>
  <div class="container">
    <div>
      <div class="navbar">
        <div class="icon left" @click="$router.go(-1)">
          <icon-base><icon-arrow-left /></icon-base>
        </div>
        <div class="center">
          <input
            v-model="keyword"
            type="text"
            placeholder="검색할 클럽명을 입력해주세요"
            maxlength="10"
            class="font-body-3"
            @keyup.enter="clickSearch"
          />
        </div>
        <div class="icon right" @click="clickSearch">
          <icon-base :iconColor="'var(--light-brown)'"><icon-search /></icon-base>
        </div>
      </div>
      <div v-if="clubList && clubList.length > 0" class="club-search-result">
        <ClubSearchBarPageList
          v-for="(club, idx) in clubList"
          :key="idx"
          :club="club"
          :keyword="keyword"
          class="page-item"
        />
      </div>
      <div v-else class="empty club-search-result">
        <span class="font-body-4">검색된 클럽이 없습니다</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ClubSearchBarPageList from "@/views/clubs/ClubSearchBarPageList.vue";
import IconSearch from "@/components/icons/IconSearch.vue";

export default {
  name: "ClubSearchBarPage",
  components: {
    ClubSearchBarPageList,
    IconSearch,
  },
  computed: {
    ...mapState("searchStore", ["clubList"]),
  },
  data: function () {
    return {
      keyword: "",
    };
  },
  methods: {
    ...mapActions("searchStore", ["searchClubByName", "initClubList"]),
    ...mapActions("clubStore", ["findClubInfo"]),
    clickSearch() {
      this.searchClubByName(this.keyword);
    },
  },
  created() {
    this.initClubList();
  },
};
</script>

<style lang="scss" scoped>
.navbar {
  background-color: var(--white);

  .center {
    justify-self: left;
    width: 100%;
  }
  input {
    width: inherit;
    padding: 1rem 1.7rem;
    text-align: left;
    border: 0;
    background-color: transparent;
  }
  .right {
    justify-self: left;
    align-items: center;
    display: flex;
    position: relative;
  }
}
.club-search-result {
  padding-top: 2rem;
}

.empty {
  text-align: left;
  margin: 0 auto;
  width: 30rem;
  color: var(--grey);
}
</style>
