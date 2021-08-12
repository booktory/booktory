<template>
  <div class="question-list-wrapper">
    <ClubQuestionItem
      v-for="(item, index) in questionList"
      :key="index"
      :questionId="item.questionId"
      :questionContents="item.questionContents"
      :answers="item.answers"
      :isOpen="item.isOpen"
      :userId="item.userId"
      :nickname="item.nickname"
      :profileImg="item.profileImg"
    />
  </div>
</template>

<script>
import ClubQuestionItem from "@/views/clubs/ClubQuestionItem.vue";

export default {
  name: "ClubQuestionList",
  components: {
    ClubQuestionItem,
  },
  computed: {},
  props: {
    questionList: {
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
