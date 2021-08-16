<template>
  <div class="container bg-image">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <div class="main-head">
            <h4 class="title">모임 생성</h4>
          </div>
          <div class="create-meeting-card m-top-1">
            <div class="create-meeting-schedule">
              <div class="create-meeting-schedule-head">
                <div class="font-body-3 font-bold">모임 일정</div>
              </div>
              <div class="create-meeting-schedule-body m-top-1">
                <div class="input-div">
                  <date-picker
                    v-model="endDate"
                    valueType="format"
                    type="datetime"
                    placeholder="날짜를 선택하세요"
                    class="date-picker"
                    :disabled-date="disabledAfterTodayAndBefore100Year"
                    :clearable="false"
                    required
                  ></date-picker>
                </div>
              </div>
            </div>
            <div class="create-meeting-book">
              <div class="create-meeting-book-head m-top-2">
                <div class="font-body-3 font-bold">읽을 책 선택하기</div>
              </div>
              <div class="create-meeting-book-body m-top-1">
                <select v-model="selected" name="" id="">
                  <option disabled selected value="1">책을 선택하세요</option>
                  <option v-for="(book, idx) in nextbookclubList" :value="book.id" :key="idx">
                    {{ book.bookTitle }}
                  </option>
                </select>
              </div>
            </div>
            <button class="button-2 adj-center" @click="clickCreate">만들기</button>
          </div>
        </div>
      </div>
      <Navbar selected="'meeting'" />
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
      selected: 1,
      endDate: null,
      value2: null,
    };
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubId"]),
    ...mapState("bookclubStore", ["nextbookclubList"]),
  },
  methods: {
    ...mapActions("bookclubStore", ["createMeeting"]),
    clickCreate() {
      const createParam = {
        id: this.selected,
        endDateTime: this.endDate,
      };
      this.createMeeting(createParam);
    },
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
