<template>
  <div class="question-list-wrapper">
    <ClubdetailBoardItem
      v-for="(item, index) in boardList"
      :key="index"
      :boardId="item.boardId"
      :contents="item.contents"
      :date="item.date"
      :fileUrl="item.fileUrl"
      :userId="item.userId"
      :nickname="item.nickname"
      :profileImg="item.profileImg"
    />
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
.question-list-wrapper {
  padding: 1.8rem 1.6rem 1.4rem;
  width: inherit;
}
</style>
