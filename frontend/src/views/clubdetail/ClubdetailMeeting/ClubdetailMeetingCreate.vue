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
          <div class="create-meeting-card">
            <div class="create-meeting-schedule">
              <div class="create-meeting-schedule-head">
                <h5>모임 일정</h5>
              </div>
              <div class="create-meeting-schedule-body">
                <date-picker></date-picker>
              </div>
            </div>
            <div class="create-meeting-book">
              <div class="create-meeting-book-head">
                <h5>읽을 책 선택하기</h5>
              </div>
              <div class="create-meeting-book-body">
                <select name="" id=""></select>
              </div>
            </div>
            <button class="button-2">만들기</button>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import "./ClubdetailMeetingCreate.scss";
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailMeetingCreate",
  components: {
    Navbar,
  },
};
</script>

<style></style>
