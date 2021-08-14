<template>
  <div v-if="clubInfo">
    <!-- 헤드 => 좌우 넘기기 있음 -->
    <div class="header">
      <span v-if="index !== 0">
        <div class="icon" @click="clickLeft">
          <icon-base><icon-chevron-left /></icon-base>
        </div>
      </span>
      <span v-else>
        <div class="icon">
          <icon-base :iconColor="'none'"><chevron-left /></icon-base>
        </div>
      </span>
      <h5>
        {{ clubInfo.name }}
      </h5>
      <span v-if="index !== maxLength - 1">
        <div class="icon" @click="clickRight">
          <icon-base><icon-chevron-right /></icon-base>
        </div>
      </span>
      <span v-else>
        <div class="icon">
          <icon-base :iconColor="'none'"><chevron-right /></icon-base>
        </div>
      </span>
    </div>

    <!-- 바디1 => 클럽 정보 -->
    <div class="card-background club-info" @click="clickCard">
      <h4 class="club-info-title">{{ clubInfo.name }}</h4>
      <div class="font-body-4 club-info-user">
        <b>클럽장</b> {{ clubInfo.leaderName }}&nbsp;|&nbsp;<b>참가자</b> {{ clubInfo.nowMember }}명
      </div>
      <div>
        <div class="font-body-3 club-info-text">
          {{ clubInfo.info }}
        </div>
        <span
          v-for="(genre, idx) in clubInfo.genres"
          :key="idx"
          :id="genre"
          class="font-body-4 club-info-genre"
        >
          {{ genreList[genre - 1] }}
        </span>
      </div>

      <div v-if="clubInfo.endDateTime">
        <!-- 바디2 => 책 정보 -->
        <div class="bookcard-background">
          <div class="bookcard-box">
            <img :src="clubInfo.thumbnail" alt="bookThumbnail" class="bookcard-image" />
            <div class="bookcard-info">
              <div class="bookcard-info-more">
                <span class="font-body-4">책 목록 더보기</span>
              </div>
              <span class="bookcard-info-now font-body-5">읽고 있는 책</span>
              <h5 class="bookcard-info-title">
                {{
                  clubInfo.title.length > 30
                    ? clubInfo.title.substr(0, 30) + "・・・"
                    : clubInfo.title
                }}
              </h5>
              <div class="bookcard-info-subtitle font-body-4">
                {{
                  clubInfo.author.length > 8
                    ? clubInfo.author.substr(0, 8) + "・・・"
                    : clubInfo.author
                }}&nbsp;|&nbsp;{{
                  clubInfo.publisher.length > 8
                    ? clubInfo.publisher.substr(0, 8) + "・・・"
                    : clubInfo.publisher
                }}
              </div>
            </div>
          </div>
        </div>
        <!-- 모임 정보 -->
        <div class="meeting">
          <h5>{{ convertTime(clubInfo.endDateTime) }}</h5>
          <span class="font-body-4">{{ remainTimeStr }}</span>
        </div>
        <button :disabled="!isOpen" class="button-2 m-top-5">모임 입장하기</button>
      </div>
      <div v-else>
        <span class="empty-meeting">예정된 모임이 없습니다.</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import router from "@/router";

export default {
  name: "ClubListItem",
  props: {
    clubId: {
      type: Number,
    },
    maxLength: {
      type: Number,
    },
    index: {
      type: Number,
    },
  },
  computed: {
    ...mapState("clubStore", ["clubInfo"]),
    ...mapState("searchStore", ["genreList"]),
  },
  data() {
    return {
      isOpen: false,
      isStart: false,
      remainTimeStr: "",
    };
  },
  watch: {
    clubId: {
      handler() {
        this.findClubInfo(this.clubId);
      },
    },
  },
  methods: {
    ...mapActions("clubStore", ["findClubInfo"]),
    clickLeft: function () {
      this.$emit("click-left");
    },
    clickRight: function () {
      this.$emit("click-right");
    },
    // 클럽 카드 클릭
    clickCard() {
      router.push({ name: "ClubdetailHome" });
    },
    // 모임 시간 년월일 변환
    convertTime(data) {
      let date = new Date(data);
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let ampm = hour / 12 >= 1 ? "오후 " : "오전 ";
      let minute = date.getMinutes();
      let dateStr =
        year + "년 " + month + "월 " + day + "일 " + ampm + (hour % 12) + "시 " + minute + "분";
      return dateStr;
    },
    // 모임까지 남은 시간 계산
    convertRemainTime() {
      let target = new Date(this.clubInfo.endDateTime);
      let curr = new Date();
      let diffSecond = Math.floor((target.getTime() - curr.getTime()) / 1000);
      let diffTime = Math.floor(diffSecond / 60);
      let diffTimeHour = Math.floor(diffTime / 60);
      let diffTimeDay = Math.floor(diffTimeHour / 24);
      // 모임까지 00일 00시 00분 00초 남았습니다.
      let dateStr = "모임까지 ";
      if (diffTimeDay > 0) dateStr += diffTimeDay + "일 ";
      if (diffTimeHour > 0) dateStr += (diffTimeHour % 24) + "시간 ";
      if (diffTime >= 10) dateStr += (diffTime % 60) + "분 ";
      else if (diffTime >= 0) {
        this.isOpen = true;
        return "곧 모임이 시작됩니다.";
      } else {
        this.isOpen = true;
        this.isStart = true;
        return "모임이 시작되었습니다.";
      }
      if (diffSecond > 0) dateStr += (diffSecond % 60) + "초 ";
      return dateStr + "남았습니다.";
    },
    init() {
      this.initPolling = setInterval(() => {
        this.remainTimeStr = this.convertRemainTime();
        if (this.clubInfo) clearInterval(this.initPolling);
      }, 100);
    },
    start() {
      this.polling = setInterval(() => {
        this.remainTimeStr = this.convertRemainTime();
        if (this.isStart) clearInterval(this.polling);
      }, 1000);
    },
  },
  created() {
    this.findClubInfo(this.clubId);
    this.init();
    this.start();
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header .icon {
  margin: 0 2em;
}

.card-background {
  width: 30rem;
  min-height: 50vh;
  margin: 5% auto;
  padding: 4rem 1rem 4.5rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px 4px rgba(161, 160, 228, 0.16);
  background-color: var(--white);
}

.bookcard-background {
  width: 100%;
  margin: 1.5rem auto;
  padding: 1.5rem 0 1.5rem;
  background-color: var(--very-light-grey);
  border-radius: 1em;
}

.club-info-title {
  margin-top: 0;
  margin-bottom: 1rem;
}
.club-info-user {
  margin-bottom: 1rem;
}
.club-info-text {
  margin: 0 2rem 1rem;
}
.club-info-genre {
  display: inline-block;
  padding: 0.3rem 0.7rem;
  margin: 0 0.2rem;
  border: 0;
  border-radius: 1em;
  color: var(--white);
  background-color: var(--light-orange);
}

.bookcard-box {
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  gap: 1.6rem;
  padding: 0 1.5rem;
}
.bookcard-image {
  width: 7rem;
  border-radius: 1rem;
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
.bookcard-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.bookcard-info-more {
  position: absolute;
  top: 0;
  right: 0;
  margin-right: 1.5rem;
  color: var(--grey);
}
.bookcard-info-title {
  text-align: left;
  margin: 0.5rem 0;
}
.bookcard-info-subtitle {
  text-align: left;
}

.meeting {
  margin-top: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
.meeting h4,
h5 {
  margin: 0.7rem;
}
.empty-meeting {
  display: inline-block;
  margin-top: 4rem;
  font-size: 1.4rem;
  color: var(--grey);
}
</style>
