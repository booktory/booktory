<template>
  <div>
    <div class="input-div m-top-5">
      <p class="label font-body-4">도서 분야로 찾기</p>
      <div class="genre-keyword">
        <span v-for="(genre, idx) in genreList" :key="idx">
          <span
            @click="clickGenre"
            :id="genre.id"
            class="tag"
            :class="{ selected: genre.isSelect }"
            >{{ genre.name }}</span
          >
        </span>
      </div>
    </div>
    <div v-if="clubList && clubList.length > 0">
      <ClubSearchResult v-for="(club, idx) in clubList" :key="idx" :club="club" class="page-item" />
    </div>
    <div v-else class="empty">
      <span class="font-body-4">검색된 클럽이 없습니다</span>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ClubSearchResult from "@/views/clubs/ClubSearchResult.vue";

export default {
  name: "ClubSearchGenre",
  components: {
    ClubSearchResult,
  },
  data: function () {
    return {};
  },
  methods: {
    ...mapActions("searchStore", ["searchClubByGenre"]),
    // 도서 분야 클릭
    clickGenre(event) {
      this.genreList[event.target.id - 1].isSelect = !this.genreList[event.target.id - 1].isSelect;
      this.searchClubByGenre(this.genreList);
    },
  },
  computed: {
    ...mapState("searchStore", ["genreList", "clubList"]),
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  margin-left: 1rem;
}
.empty {
  text-align: left;
  margin-left: 5rem;
  color: var(--grey);
}
.genre-keyword {
  background: transparent;
  box-shadow: none;
  margin: 0 auto;
  text-align: center;
}
.genre-keyword span {
  margin: 0 0.2rem 0.3rem;
}
.tag {
  display: inline-block;
  padding: 0.2rem 0.6rem;
  border: 0;
  border-radius: 1em;
  color: var(--white);
  background-color: var(--light-orange);
}
.selected {
  background-color: var(--orange);
}
</style>
