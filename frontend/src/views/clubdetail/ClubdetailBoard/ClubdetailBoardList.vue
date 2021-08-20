<template>
  <div class="board-list-wrapper">
    <ClubdetailBoardItem v-for="(board, index) in boardList" :key="index" :board="board" />
  </div>
</template>

<script>
import ClubdetailBoardItem from "@/views/clubdetail/ClubdetailBoard/ClubdetailBoardItem.vue";

export default {
  name: "ClubdetailBoardList",
  components: {
    ClubdetailBoardItem,
  },
  computed: {},
  props: {
    boardList: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.board-list-wrapper {
  padding: 1.8rem 1.6rem 1.4rem;
  width: inherit;
}
</style>
