<template>
  <div class="answer-item-wrapper">
    <div class="answer-frame">
      <img
        class="profile-image"
        :src="profileImg ? profileImg : 'https://via.placeholder.com/50'"
        alt="프로필 사진"
      />
      <div class="answer-info">
        <span class="font-body-4 nickname">{{ nickname }}</span>
        <span class="font-body-5">2021-08-11 17:16:15</span>
      </div>
    </div>
    <p class="answer-content font-body-4">{{ answerContents }}</p>
  </div>
</template>

<script>
export default {
  name: "ClubQuestionAnswerItem",
  components: {},
  props: {
    answerContents: {
      type: String,
    },
    isOpen: {
      type: Boolean,
    },
    userId: {
      type: Number,
    },
    nickname: {
      type: String,
    },
    profileImg: {
      type: String,
    },
  },
  methods: {},
};
</script>

<style scoped>
* {
  text-align: left;
}
.answer-item-wrapper {
  padding: 0;
}
.answer-frame {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  padding: 0;
}
.profile-image {
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 50%;
}
.answer-info {
  position: relative;
  display: grid;
  grid-template-rows: 3fr 2fr;
  justify-content: start;
  align-items: end;
  gap: 0.2rem;
  padding: 0;
}
.nickname {
  font-weight: bold;
  color: var(--orange);
}
.answer-content {
  margin: 1rem 0.7rem;
  text-align: justify;
}
</style>
