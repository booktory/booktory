<template>
  <div class="container bg-image">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 모임 일정</h4>
          <div class="main-head">
            <h5>다음 모임</h5>
            <span
              v-if="clubInfo.isLeader && nowbookclub == null"
              class="font-body-4"
              @click="$router.push({ name: 'ClubdetailMeetingCreate' })"
              >모임 만들기</span
            >
          </div>
          <div class="next-meeting">
            <div v-if="nowbookclub != null">
              <div class="meeting-card m-top-1">
                <div class="icon icon-cancel" @click="clickCancel">
                  <icon-base><icon-x /></icon-base>
                </div>
                <div class="meeting-card-head">
                  <div class="meeting-card-head-date">
                    {{ $moment(nowbookclub.endDateTime).format("YYYY년 M월 D일 ")
                    }}<span>{{
                      $moment(nowbookclub.endDateTime).format(
                        $moment(nowbookclub.endDateTime).format("A") == "AM"
                          ? "오전 h시 mm분"
                          : "오후 h시 mm분"
                      )
                    }}</span>
                  </div>
                </div>
                <div class="meeting-card-body">
                  <h5>{{ nowbookclub.bookTitle }}</h5>
                  <span class="font-body-4"> 책을 함께 읽을 예정이에요</span>
                </div>
                <div class="meeting-card-footer font-body-4">
                  {{ convertRemainTime(nowbookclub.endDateTime) }}
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 meeting-card-no">
              예정된 모임이 없습니다<br />
              새로운 모임을 개설해 주세요 :)
            </div>
          </div>
          <div class="pre-meeting">
            <h5>지난 모임</h5>
            <div v-if="prebookclubList.length > 0">
              <div
                class="meeting-card m-top-1"
                v-for="(preMeeting, idx) in prebookclubList"
                :key="idx"
              >
                <div class="meeting-card-head">
                  <div class="meeting-card-head-date">
                    {{ $moment(preMeeting.endDateTime).format("YYYY년 M월 D일") }}
                    <span
                      >{{
                        $moment(preMeeting.endDateTime).format(
                          $moment(preMeeting.endDateTime).format("A") == "AM"
                            ? "오전 h시 mm분"
                            : "오후 h시 mm분"
                        )
                      }}
                    </span>
                  </div>
                </div>
                <div class="meeting-card-body">
                  <h5>{{ preMeeting.bookTitle }}</h5>
                  <span class="font-body-4"> 책을 함께 읽었어요</span>
                </div>
                <div class="meeting-card-footer font-body-4">
                  <div>{{ preMeeting.userList.length }}명 참석</div>
                  <span
                    class="meeting-card-footer-list"
                    v-for="(user, idx) in preMeeting.userList"
                    :key="idx"
                  >
                    <img
                      :src="
                        user.profileImg
                          ? user.profileImg
                          : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
                      "
                      class="meeting-card-footer-profileImg"
                    />
                  </span>
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 meeting-card-no">아직 진행한 모임이 없습니다</div>
          </div>
        </div>
      </div>
      <Navbar :selected="'meeting'" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
var moment = require("moment");

export default {
  name: "ClubdetailMeeting",
  components: {
    TopHeader,
    Navbar,
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubId"]),
    ...mapState("bookclubStore", ["nowbookclub", "prebookclubList"]),
  },
  created() {
    this.getBookClubList(this.clubId);
  },
  methods: {
    ...mapActions("bookclubStore", ["cancelMeeting", "getBookClubList"]),
    clickCancel() {
      this.cancelMeeting(this.nowbookclub.id);
    },
    convertRemainTime(endDateTime) {
      let diffSecond = Math.floor(moment(endDateTime).subtract(moment()) / 1000);
      let diffTime = Math.floor(diffSecond / 60);
      let diffTimeHour = Math.floor(diffTime / 60);
      let diffTimeDay = Math.floor(diffTimeHour / 24);

      let dateStr = "모임 시작 ";
      if (diffTimeDay > 0) dateStr += diffTimeDay + "일 ";
      if (diffTimeHour > 0) dateStr += (diffTimeHour % 24) + "시간 ";
      if (diffTime >= 10) dateStr += (diffTime % 60) + "분 ";
      dateStr += "전";

      return dateStr;
    },
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
