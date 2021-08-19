<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">모임 생성</h4>
          <div class="create-meeting-schedule">
            <div class="input-div">
              <p class="label font-body-4">모임 일정 선택</p>
              <date-picker
                v-model="meetingData.endDateTime"
                v-bind:class="{
                  error: error.endDateTime,
                  complete: !error.endDateTime,
                }"
                id="endDate"
                class="date-picker"
                placeholder="날짜와 시간을 선택하세요"
                type="datetime"
                value-type="format"
                format="YYYY-MM-DD HH:mm:ss"
                :minute-step="10"
                :second-step="60"
                :default-value="new Date().setHours(new Date().getHours(), 0, 0, 0)"
                :disabled-date="notBeforeToday"
                :disabled-time="notBeforeTodayCurrTime"
                :editable="false"
                required
              ></date-picker>
              <p v-if="error.endDateTime" class="message">{{ error.endDateTime }}</p>
            </div>
          </div>
          <div class="create-meeting-book">
            <div class="input-div">
              <p class="label font-body-4">읽을 책 선택</p>
              <select
                v-model="meetingData.id"
                v-bind:class="{
                  error: error.id,
                  complete: !error.id,
                }"
              >
                <option disabled selected value="0">읽을 책을 선택하세요</option>
                <option v-for="(book, idx) in nextbookclubList" :value="book.id" :key="idx">
                  {{ book.bookTitle }}
                </option>
              </select>
              <p v-if="error.id" class="message">{{ error.id }}</p>
            </div>
          </div>
          <button
            type="button"
            class="button-2 m-top-10"
            :disabled="!isSubmit"
            @click="clickCreate"
          >
            생성하기
          </button>
        </div>
      </div>
      <Navbar :selected="'meeting'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubdetailMeetingCreate",
  components: {
    TopHeader,
    Navbar,
  },
  data() {
    return {
      meetingData: {
        id: 0,
        endDateTime: null,
      },
      error: {
        id: false,
        endDateTime: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    meetingData: {
      deep: true,
      handler() {
        this.checkEndDateTimeForm();
        this.checkIdForm();
      },
    },
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubId", "clubImage"]),
    ...mapState("bookclubStore", ["nextbookclubList"]),
  },
  methods: {
    ...mapActions("bookclubStore", ["createMeeting"]),
    // 생성하기 버튼 클릭
    clickCreate() {
      if (this.isSubmit) {
        this.createMeeting(this.meetingData);
      }
    },
    // 모임 일정 선택 제한
    notBeforeToday(date) {
      return date < new Date(new Date().setHours(0, 0, 0, 0));
    },
    notBeforeTodayCurrTime(date) {
      return date < new Date(new Date().setHours(new Date().getHours(), 0, 0, 0));
    },
    // 모임 일정 유효성 검사
    checkEndDateTimeForm() {
      if (!this.meetingData.endDateTime) {
        this.error.endDateTime = "모임 일정을 선택해주세요";
      } else if (new Date(this.meetingData.endDateTime) < new Date()) {
        this.error.endDateTime = "모임 일정을 현재 시간 이후로 선택해주세요";
      } else this.error.endDateTime = false;
    },
    // 읽을 책 유효성 검사
    checkIdForm() {
      if (this.meetingData.id == 0) {
        this.error.id = "모임에서 읽을 책을 선택해주세요";
      } else this.error.id = false;

      // 유효성 검사 통과 시 버튼 활성화
      if (this.meetingData.endDateTime && this.meetingData.id != 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailMeetingCreate.scss";
.m-top-1 {
  margin-top: 1rem;
}
.m-top-2 {
  margin-top: 2rem;
}
.adj-center {
  margin: 0 auto;
}
.font-bold {
  font-weight: bold;
}
.mx-datepicker {
  width: 250px;
}
</style>
