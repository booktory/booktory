<template>
  <div class="input-div">
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
  margin: 0 0.2%;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 10px;
  color: white;
  background-color: #a4c0f3;
}
</style>
