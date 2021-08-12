<template>
  <div class="container bg-img">
    <TopHeader />
    <div class="card">
      <div class="main">
        <h4 class="title">문의 게시판</h4>
        <p class="sub-title-c">클럽에 대해 궁금한 사항들을 물어보세요</p>
        <div class="question-board">
          <ClubQuestionList v-if="questionList" :questionList="questionList" />
          <div class="empty" v-else>
            <span class="font-body-3">작성된 질문이 없습니다</span>
          </div>
        </div>
        <button class="button-3" @click="clickRegister" :disabled="!questionData.contents">
          등록
        </button>
        <div class="is-open">
          <div>
            <input class="check" type="checkbox" id="checkbox" v-model="isLock" />
            <span class="font-body-4">비밀글</span>
          </div>
        </div>
        <h5>새로운 질문</h5>
        <div class="question-input">
          <textarea
            v-model="questionData.contents"
            style="font-size: 1.2rem; text-align: justify"
            class="input-content"
            type="text"
            id="content"
            rows="5"
            placeholder="질문을 입력해주세요"
          />
        </div>
        <div class="empty-div"></div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import ClubQuestionList from "@/views/clubs/ClubQuestionList.vue";

export default {
  name: "ClubQuestion",
  components: {
    TopHeader,
    Navbar,
    ClubQuestionList,
  },
  computed: {
    ...mapState("clubStore", ["questionList"]),
  },
  data() {
    return {
      isLock: false,
      questionData: {
        isOpen: true,
        contents: "",
      },
    };
  },
  methods: {
    ...mapActions("clubStore", ["registerQuestion", "findQuestionList"]),
    clickRegister() {
      this.questionData.isOpen = !this.isLock;
      this.registerQuestion(this.questionData).then(() => {
        this.isLock = false;
        this.questionData.isOpen = true;
        this.questionData.contents = "";
      });
    },
  },
  created() {
    this.findQuestionList();
  },
};
</script>

<style scoped>
.bg-img {
  height: 100vh;
  background-image: linear-gradient(to bottom, var(--white) 0%, var(--light-grey) 100%),
    url("./images/club-backgroud.png");
  background-blend-mode: multiply;
  background-size: cover;
}
.card {
  width: 100%;
  height: inherit;
  top: 5em;
}
.main {
  height: inherit;
  padding: 3rem 3rem 10rem;
  background-color: var(--beige);
  border-radius: 7em 7em 0 0;
}
.sub-title-c {
  font-size: 1.2rem;
  margin: 0;
}
.question-board {
  width: inherit;
  min-height: 20rem;
  margin-top: 2rem;
  background-color: var(--white);
  border-radius: 1rem;
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}
.empty {
  text-align: left;
  padding: 1.8rem;
  color: var(--grey);
}
h5 {
  display: flex;
  margin: 2rem 0 0 1rem;
}
.question-input {
  width: inherit;
  margin-top: 1rem;
  background-color: var(--white);
  border-radius: 1rem;
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}
.input-content {
  color: var(--dark-grey);
  width: 90%;
  padding: 1.3rem 1rem;
  border: 0;
  background-color: transparent;
  text-align: left;
  font-size: 1.4rem;
}
textarea:focus {
  outline: none;
}
.is-open {
  float: right;
  margin: 2.3rem 1rem 0 0;
}
.is-open > div {
  display: flex;
  align-items: center;
}
.check {
  width: 1.2rem;
  height: 1.2rem;
  margin-right: 0.5rem;
}
</style>
