<template>
  <div class="question-item-wrapper">
    <div class="quetion-frame">
      <img
        class="profile-image"
        :src="profileImg ? profileImg : 'https://via.placeholder.com/50'"
        alt="프로필 사진"
      />
      <div class="question-info">
        <div class="sub-info">
          <span class="font-body-5 reply" @click="clickReply">답글 달기</span>
          <div v-if="!isOpen" class="lock">
            <icon-base :width="'1.4rem'" :height="'1.4rem'" :iconColor="'var(--grey)'"
              ><icon-lock
            /></icon-base>
          </div>
        </div>
        <span class="font-body-3 nickname">{{ nickname }}</span>
        <span class="font-body-5">2021-08-11 17:16:15</span>
      </div>
    </div>
    <div v-if="true">
      <div class="question-content font-body-4">{{ questionContents }}</div>
      <ClubQuestionAnswerList :answerList="answers" />
    </div>
    <div v-else>
      <span>비밀글입니다</span>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import ClubQuestionAnswerList from "@/views/clubs/ClubQuestionAnswerList.vue";
import IconLock from "@/components/icons/IconLock.vue";
import Swal from "sweetalert2";

export default {
  name: "ClubQuestionItem",
  components: {
    ClubQuestionAnswerList,
    IconLock,
  },
  props: {
    questionId: {
      type: Number,
    },
    questionContents: {
      type: String,
    },
    answers: {
      type: Array,
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
  data() {
    return {
      answerData: {
        questionId: this.questionId,
        isOpen: this.isOpen,
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
          console.log(this.answerData);
          this.registerAnswer(this.answerData);
        }
      })();
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
.profile-image {
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
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
</style>
