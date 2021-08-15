<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon icon-back" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <div class="main-head">
            <h4 class="title">모임 생성</h4>
          </div>
          <div class="create-meeting-card m-top-1">
            <div class="create-meeting-schedule">
              <div class="create-meeting-schedule-head">
                <div class="font-body-3 font-bold">모임 날짜</div>
              </div>
              <div class="create-meeting-schedule-body m-top-1">
                <date-picker
                  v-model="endDate"
                  type="datetime"
                  placeholder="날짜를 선택하세요"
                  class="date-picker"
                  required
                ></date-picker>
              </div>
              <!-- <div class="create-meeting-schedule-head m-top-2">
                <div class="font-body-3 font-bold">모임 시간</div>
              </div>
              <div class="create-meeting-schedule-body m-top-1">
                <date-picker
                  v-model="value2"
                  type="time"
                  placeholder="시간을 선택하세요"
                  class="date-picker"
                  required
                ></date-picker>
              </div> -->
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
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubdetailMeetingCreate",
  components: {
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
      // console.log(this.selected);
      const createParam = {
        id: this.selected,
        endDateTime: this.endDate,
      };
      // console.log(typeof new Date(this.endDate));
      // console.log(this.endDate.format("yyyy-MM-dd HH:mm:ss"));
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
