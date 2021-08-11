<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon-head" @click="$router.push({ name: 'ClubHome' })">
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
                <h5>모임 날짜</h5>
              </div>
              <div class="create-meeting-schedule-body m-top-1">
                <date-picker
                  v-model="value1"
                  type="date"
                  placeholder="날짜를 선택하세요"
                  class="date-picker"
                  required
                ></date-picker>
              </div>
              <div class="create-meeting-schedule-head m-top-2">
                <h5>모임 시간</h5>
              </div>
              <div class="create-meeting-schedule-body m-top-1">
                <date-picker
                  v-model="value2"
                  type="time"
                  placeholder="시간을 선택하세요"
                  class="date-picker"
                  required
                ></date-picker>
              </div>
            </div>
            <div class="create-meeting-book">
              <div class="create-meeting-book-head m-top-2">
                <h5>읽을 책 선택하기</h5>
              </div>
              <div class="create-meeting-book-body m-top-1">
                <select name="" id="">
                  <option>1</option>
                  <option>2</option>
                </select>
              </div>
            </div>
            <button class="button-2 adj-center">만들기</button>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailMeetingCreate",
  components: {
    Navbar,
  },
  data() {
    return {
      value1: null,
      value2: null,
    };
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
</style>
