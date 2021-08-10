<template>
  <div class="input-div m-top-5">
    <label>검색할 도서 분야 선택</label>
    <span class="genre-keyword" v-for="(genre, idx) in genresList" :key="idx" :value="genre">
      <button class="tag">{{ genre }}</button>
    </span>
    <div>
      {{ selectedGenre }}
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubSearchKeyword",
  data: function () {
    return {
      selectedGenre: "",
    };
  },
  computed: {
    genresList: function () {
      return this.$store.state.examples.genresList;
    },
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
