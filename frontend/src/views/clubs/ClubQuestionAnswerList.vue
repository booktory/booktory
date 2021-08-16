<template>
  <div class="answer-list-wrapper">
    <ClubQuestionAnswerItem v-for="(answer, index) in answerList" :key="index" :answer="answer" />
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
  margin: 0.2rem 0 0.3rem 1rem;
  width: inherit;
}
</style>
