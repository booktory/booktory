<template>
  <div class="container bg-img">
    <div v-if="clubInfo">
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 모임 일정</h4>
          <div class="main-head">
            <h5>다음 모임</h5>
            <span
              v-if="clubInfo.isLeader && nowbookclub == null"
              class="font-body-4"
              @click="clickMeetingCreate"
              >모임 만들기</span
            >
          </div>
          <div class="next-meeting">
            <div v-if="nowbookclub != null">
              <div class="meeting-card m-top-1">
                <div v-if="clubInfo.isLeader" class="icon icon-cancel" @click="clickCancel">
                  <icon-base><icon-x /></icon-base>
                </div>
                <div class="meeting-card-head">
                  <div class="meeting-card-head-date">
                    {{ $moment(nowbookclub.endDateTime).format("YYYY년 M월 D일 ")
                    }}{{
                      $moment(nowbookclub.endDateTime).format(
                        $moment(nowbookclub.endDateTime).format("A") == "AM"
                          ? "오전 h시 mm분"
                          : "오후 h시 mm분"
                      )
                    }}
                  </div>
                </div>
                <div class="meeting-card-body">
                  <h5>{{ nowbookclub.bookTitle }}</h5>
                </div>
                <div class="meeting-card-footer font-body-4">
                  {{ convertRemainTime(nowbookclub.endDateTime) }}
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 meeting-card-no">예정된 모임이 없습니다</div>
          </div>
          <div class="pre-meeting">
            <h5>지난 모임</h5>
            <div v-if="prebookclubList && prebookclubList.length > 0">
              <div
                class="meeting-card m-top-1"
                v-for="(preMeeting, idx) in prebookclubList"
                :key="idx"
              >
                <div class="meeting-card-head">
                  <div class="meeting-card-head-date">
                    {{ $moment(preMeeting.endDateTime).format("YYYY년 M월 D일") }}
                    {{
                      $moment(preMeeting.endDateTime).format(
                        $moment(preMeeting.endDateTime).format("A") == "AM"
                          ? "오전 h시 mm분"
                          : "오후 h시 mm분"
                      )
                    }}
                  </div>
                </div>
                <div class="meeting-card-body">
                  <h5>{{ preMeeting.bookTitle }}</h5>
                </div>
                <div
                  v-if="preMeeting && preMeeting.userList.length > 0"
                  class="meeting-card-footer font-body-4"
                >
                  <div>{{ preMeeting.userList.length }}명이 책을 함께 읽었어요</div>
                  <div class="meeting-card-footer-list">
                    <div
                      class="meeting-card-footer-profileImg"
                      v-for="(user, idx) in preMeeting.userList"
                      :key="idx"
                    >
                      <img
                        :src="
                          user.profileImg
                            ? user.profileImg
                            : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
                        "
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 meeting-card-no">아직 진행한 모임이 없습니다</div>
          </div>
        </div>
      </div>
      <Navbar :selected="'meeting'" :clubId="this.clubId" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import Swal from "sweetalert2";
import router from "@/router";
var moment = require("moment");

export default {
  name: "ClubdetailMeeting",
  components: {
    TopHeader,
    Navbar,
  },
  data() {
    return {
      clubId: this.$route.query.clubId,
    };
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubImage"]),
    ...mapState("bookclubStore", ["nowbookclub", "prebookclubList"]),
  },
  methods: {
    ...mapActions("bookclubStore", ["cancelMeeting", "getBookClubList"]),
    ...mapActions("clubStore", ["findClubInfo"]),
    // 모임 개설하기
    clickMeetingCreate() {
      router.push({ name: "ClubdetailMeetingCreate", query: { clubId: this.clubId } });
    },
    // 모임 취소하기
    clickCancel() {
      Swal.fire({
        showCancelButton: true,
        title: "모임을 취소 하시겠습니까?",
        confirmButtonText: "네, 취소할래요",
        cancelButtonText: "아니요",
      }).then((result) => {
        if (result.isConfirmed) {
          this.cancelMeeting(this.nowbookclub.id);
        }
      });
    },
    convertRemainTime(endDateTime) {
      let diffSecond = Math.floor(moment(endDateTime).subtract(moment()) / 1000);
      let diffTime = Math.floor(diffSecond / 60);
      let diffTimeHour = Math.floor(diffTime / 60);
      let diffTimeDay = Math.floor(diffTimeHour / 24);

      let dateStr = "";
      if (diffTimeDay > 0) dateStr += diffTimeDay + "일 ";
      if (diffTimeHour > 0) dateStr += (diffTimeHour % 24) + "시간 ";
      if (diffTime >= 10) dateStr += (diffTime % 60) + "분 후 읽을 예정이에요";
      else dateStr = "지금 함께 읽고 있어요";

      return dateStr;
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  async created() {
    this.findClubInfo(this.clubId);
    this.getBookClubList(this.clubId);
    await new Promise((resolve) => setTimeout(resolve, 100));
    this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailMeeting.scss";
.m-top-1 {
  margin-top: 1rem;
}

.m-top-2 {
  margin-top: 2rem;
}
</style>
