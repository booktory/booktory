<template>
  <div class="question-item-wrapper">
    <div class="quetion-frame">
      <img
        class="profileImg"
        :src="
          question.profileImg
            ? question.profileImg
            : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
        "
        alt="프로필 사진"
      />
      <div class="question-info">
        <div class="sub-info">
          <span v-if="isLeader" class="font-body-5 reply" @click="clickReply">답글 달기</span>
          <div v-if="!question.isOpen" class="lock">
            <icon-base :width="'1.4rem'" :height="'1.4rem'" :iconColor="'var(--grey)'"
              ><icon-lock
            /></icon-base>
          </div>
        </div>
        <span class="font-body-3 nickname">{{ question.nickname }}</span>
        <span class="font-body-5">{{ convertTime(question.date) }}</span>
      </div>
    </div>
    <div v-if="question.isOpen || isLeader || question.userId == userId">
      <div v-html="question.questionContents" class="question-content font-body-4"></div>
      <ClubQuestionAnswerList :answerList="question.answers" />
    </div>
    <div v-else>
      <div class="question-content secret font-body-4">비밀글은 작성자만 확인할 수 있습니다</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ClubQuestionAnswerList from "@/views/clubs/ClubQuestionAnswerList.vue";
import IconLock from "@/components/icons/IconLock.vue";
import Swal from "sweetalert2";
var moment = require("moment");
require("moment-timezone");
moment.tz.setDefault("Asia/Seoul");

export default {
  name: "ClubQuestionItem",
  components: {
    ClubQuestionAnswerList,
    IconLock,
  },
  props: {
    question: {
      type: Object,
    },
  },
  computed: {
    ...mapState(["userId"]),
    ...mapState("clubStore", ["isLeader"]),
  },
  data() {
    return {
      answerData: {
        questionId: this.question.questionId,
        isOpen: this.question.isOpen,
        contents: "",
      },
    };
  },
  methods: {
    ...mapActions("clubStore", ["registerAnswer"]),
    clickReply() {
      (async () => {
        const { value: text } = await Swal.fire({
          inputLabel: "답글 달기",
          input: "textarea",
          inputPlaceholder: "답글을 입력해주세요",
          showCancelButton: true,
          confirmButtonText: "등록",
          cancelButtonText: "취소",
        });
        if (text) {
          this.answerData.contents = text;
          this.registerAnswer(this.answerData);
        }
      })();
    },
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
.question-item-wrapper {
  padding: 0;
}
.quetion-frame {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  align-items: center;
  gap: 0.7rem;
  padding: 0;
}
.profileImg {
  width: 4rem;
  height: 4rem;
}
.question-info {
  position: relative;
  display: grid;
  grid-template-rows: 3fr 2fr;
  justify-content: start;
  align-items: end;
  gap: 0.2rem;
  padding: 0;
}
.sub-info {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0.2rem 0.5rem;
}
.lock {
  float: right;
  margin: -0.2rem 0.5rem 0 0;
  padding: 0;
}
.reply {
  float: right;
  color: var(--grey);
}
.nickname {
  font-weight: bold;
  color: var(--orange);
}
.question-content {
  margin: 1rem 0.7rem;
  text-align: justify;
}
.secret {
  color: var(--medium-grey);
}
</style>
