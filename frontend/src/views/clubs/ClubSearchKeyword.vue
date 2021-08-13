<template>
  <div>
    <div class="input-div m-top-5">
      <label>검색할 도서 분야 선택</label>
      <span class="genre-keyword" v-for="(genre, idx) in genresList" :key="idx" :value="genre">
        <button class="tag" @click="clickGenre">{{ genre }}</button>
      </span>
    </div>
    <div v-if="!clubList" class="input-div">
      <label>검색된 클럽 목록</label>
      <p class="message">검색된 클럽이 없습니다.</p>
    </div>
    <ClubSearchResult
      v-for="(club, idx) in clubList"
      :key="idx"
      :club="club"
      @select-club="onSelectClub"
      class="page-item"
    />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ClubSearchResult from "@/views/clubs/ClubSearchResult.vue";
import router from "@/router";

export default {
  name: "ClubSearchKeyword",
  components: {
    ClubSearchResult,
  },
  data: function () {
    return {
      genre: "",
    };
  },
  methods: {
    ...mapActions("searchStore", ["searchClubByGenre"]),
    clickGenre(event) {
      this.genre = event.target.textContent;
      // console.log(this.genre);
      this.searchClubByGenre(this.genre);
    },

    onSelectClub: function () {
      // console.log(club);
      router.push({ name: "ClubSearchBarPageListItem" });
    },
  },
  computed: {
    genresList: function () {
      return this.$store.state.examples.genresList;
    },
    ...mapState("searchStore", ["clubList"]),
  },
};
</script>

<style scoped>
.tag {
  display: inline-block;
  margin: 0 auto;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 1em;
  color: white;
  background-color: var(--light-orange);
  margin: 0.5% 0.4%;
}
</style>
