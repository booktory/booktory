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
                  <div class="font-body-3">
                    {{
                      nowbookclub.endDateTime.substr(0, 4) +
                      "년 " +
                      (new Date(nowbookclub.endDateTime).getMonth() + 1) +
                      "월 " +
                      new Date(nowbookclub.endDateTime).getDate() +
                      "일"
                    }}
                    <span
                      >{{
                        (new Date(nowbookclub.endDateTime).getHours() / 12 >= 1 ? "오후" : "오전") +
                        " " +
                        (new Date(nowbookclub.endDateTime).getHours() % 12) +
                        "시 " +
                        new Date(nowbookclub.endDateTime).getMinutes() +
                        "분"
                      }}
                    </span>
                  </div>
                </div>
                <div class="meeting-card-body font-body-3">
                  <div style="font-weight: bold" class="font-body-3">
                    {{
                      "'" +
                      (nowbookclub.bookTitle.length > 24
                        ? nowbookclub.bookTitle.substr(0, 24) + "..."
                        : nowbookclub.bookTitle) +
                      "'"
                    }}
                  </div>
                  <div class="font-body-4">책을 읽을 예정이에요</div>
                </div>
                <div class="meeting-card-footer font-body-4">
                  {{ convertRemainTime(nowbookclub.endDateTime) }}
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 meeting-card-no">
              예정된 모임이 없어요.<br />
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
                  <div class="font-body-3">
                    {{
                      preMeeting.endDateTime.substr(0, 4) +
                      "년 " +
                      (new Date(preMeeting.endDateTime).getMonth() + 1) +
                      "월 " +
                      new Date(preMeeting.endDateTime).getDate() +
                      "일"
                    }}
                    <span
                      >{{
                        (new Date(preMeeting.endDateTime).getHours() / 12 >= 1 ? "오후" : "오전") +
                        " " +
                        (new Date(preMeeting.endDateTime).getHours() % 12) +
                        "시 " +
                        new Date(preMeeting.endDateTime).getMinutes() +
                        "분"
                      }}
                    </span>
                  </div>
                </div>
                <div class="meeting-card-body font-body-3">
                  <div style="font-weight: bold" class="font-body-3">
                    {{
                      "'" +
                      (preMeeting.bookTitle.length > 24
                        ? preMeeting.bookTitle.substr(0, 24) + "..."
                        : preMeeting.bookTitle) +
                      "'"
                    }}
                  </div>
                  <div class="font-body-4">책을 읽었어요</div>
                </div>
                <div class="meeting-card-footer font-body-4">
                  <p class="m-bottom-0-5">{{ preMeeting.userList.length }}명 참석</p>
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
            <div v-else class="font-body-4 meeting-card-no">아직 진행한 모임이 없어요 :)</div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailMeeting",
  components: {
    Navbar,
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubId"]),
    ...mapState("bookclubStore", ["nowbookclub", "prebookclubList"]),
  },
  created() {
    this.getBookclubList(this.clubId);
  },
  methods: {
    ...mapActions("bookclubStore", ["cancelMeeting", "getBookclubList"]),
    clickCancel() {
      this.cancelMeeting(this.nowbookclub.id);
    },
    convertRemainTime(endDateTime) {
      let target = new Date(endDateTime);
      let curr = new Date();
      let diffSecond = Math.floor((target.getTime() - curr.getTime()) / 1000);
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

.m-bottom-0-5 {
  margin-bottom: 0.5rem;
}
</style>
