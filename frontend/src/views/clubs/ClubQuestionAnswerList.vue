<template>
  <div class="answer-list-wrapper">
    <ClubQuestionAnswerItem
      v-for="(item, index) in answerList"
      :key="index"
      :answerContents="item.answerContents"
      :isOpen="item.isOpen"
      :userId="item.userId"
      :nickname="item.nickname"
      :profileImg="item.profileImg"
    />
  </div>
</template>

<script>
import ClubQuestionAnswerItem from "@/views/clubs/ClubQuestionAnswerItem.vue";

export default {
  name: "ClubQuestionAnswerList",
  components: {
    ClubQuestionAnswerItem,
  },
  computed: {},
  props: {
    answerList: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.answer-list-wrapper {
  margin: 0.2rem 0 0.3rem 3rem;
  width: inherit;
}
</style>
