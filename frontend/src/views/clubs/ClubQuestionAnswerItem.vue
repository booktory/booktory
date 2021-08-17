<template>
  <div class="answer-item-wrapper">
    <div class="answer-frame">
      <div class="icon left" @click="$router.go(-1)">
        <icon-base :iconColor="'var(--grey)'" :width="'1.6rem'"><icon-answer /></icon-base>
      </div>
      <div class="profileImg">
        <img
          :src="
            answer.profileImg
              ? answer.profileImg
              : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
          "
          alt="프로필 사진"
        />
      </div>
      <div class="answer-info">
        <span class="font-body-4 nickname">{{ answer.nickname }}</span>
        <span class="font-body-5">{{ convertTime(answer.date) }}</span>
      </div>
    </div>
    <p class="answer-content font-body-4">{{ answer.answerContents }}</p>
  </div>
</template>

<script>
import IconAnswer from "@/components/icons/IconAnswer.vue";
var moment = require("moment");
require("moment-timezone");
moment.tz.setDefault("Asia/Seoul");

export default {
  name: "ClubQuestionAnswerItem",
  components: {
    IconAnswer,
  },
  props: {
    answer: {
      type: Object,
    },
  },
  methods: {
    // 등록 시간 년월일 변환
    convertTime(data) {
      let ampm = moment(data).format("A") == "AM" ? "오전" : "오후";
      let dateStr = moment(data).format("YYYY년 M월 D일 " + ampm + " h시 mm분");
      return dateStr;
    },
  },
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
  grid-template-columns: 1fr 2fr 9fr;
  justify-content: center;
  align-items: center;
  gap: 0.3rem;
  padding: 0;
}
.profileImg {
  width: 3.5rem;
  height: 3.5rem;
}
.profileImg img {
  height: 3.5rem;
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
  margin: 1rem 0 1rem 3rem;
  text-align: justify;
}
</style>
