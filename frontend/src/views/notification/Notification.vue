<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">알림</h4>
      <div class="alarm-wrapper">
        <div v-if="alarmList && alarmList.length > 0" class="alarm-list">
          <div v-for="(alarm, idx) in alarmList" :key="idx">
            <div class="alarm-contents" :class="{ unread: alarm.status == 0 }">
              <div class="alarm-contents-message" v-html="alarm.message"></div>
              <div class="alarm-contents-time">{{ convertTime(alarm.time) }}</div>
            </div>
          </div>
          <div v-if="alarmList.length >= page * 7">
            <button type="button" class="alarm-button" @click="getMoreAlarm">
              더 많은 알림 보기
            </button>
          </div>
          <div v-else>
            <p class="alarm-button-no">더 이상 알림이 존재하지 않습니다</p>
          </div>
        </div>
        <div v-else class="alarm-contents-no">
          <p>받은 알림이 없습니다.</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import TopHeader from "@/views/TopHeader.vue";
import fire from "@/firebase.js";
var moment = require("moment");

export default {
  name: "Notification",
  components: {
    TopHeader,
  },
  computed: {
    ...mapState(["userId"]),
  },
  data() {
    return {
      alarmList: [],
      page: 1,
    };
  },
  created() {
    this.alarmList = [];
    const usersref = fire.database().ref(`users/${this.userId}`).limitToLast(7);
    usersref.on("value", (list) => {
      const data = list.val();
      for (let key in data) {
        this.alarmList.unshift({
          message: data[key].message.replaceAll("\n", "<br/>"),
          time: data[key].createDateTime,
          status: data[key].readStatus,
        });
      }
    });
  },
  methods: {
    updateReadStatus() {
      fire
        .database()
        .ref(`users/${this.userId}`)
        .on("value", (list) => {
          const data = list.val();
          for (let key in data) {
            if (data[key].readStatus === 0) {
              fire.database().ref(`users/${this.userId}/${key}`).update({
                readStatus: 1,
              });
            }
          }
        });
    },
    getMoreAlarm() {
      let prev = this.page;
      this.page += 1;
      const usersref = fire
        .database()
        .ref(`users/${this.userId}`)
        .limitToLast(7 * this.page);

      usersref.on("value", (list) => {
        const data = list.val();
        let arrData = Object.entries(data).reverse();
        var idx = 1;
        for (let i = 1; i <= arrData.length; i++) {
          if (idx++ > prev * 7) {
            this.alarmList.push({
              message: arrData[i - 1][1].message,
              time: arrData[i - 1][1].createDateTime,
              status: arrData[i - 1][1].readStatus,
            });
          }
        }
      });
    },
    convertTime(time) {
      let diffTime = Math.floor(moment().subtract(moment(time)) / 1000 / 60);
      if (diffTime < 1) return "방금 전";
      if (diffTime < 60) return `${diffTime}분 전`;

      let diffTimeHour = Math.floor(diffTime / 60);
      if (diffTimeHour < 24) return `${diffTimeHour}시간 전`;

      let diffTimeDay = Math.floor(diffTimeHour / 24);
      if (diffTimeDay < 365) return `${diffTimeDay}일 전`;

      return `${Math.floor(diffTimeDay / 365)}년 전`;
    },
  },
  destroyed() {
    this.updateReadStatus();
  },
};
</script>
<style scoped>
.alarm-wrapper {
  width: 32rem;
  margin: 2rem auto;
  padding: 0;
  overflow: hidden;
  border-radius: 1rem;
  box-shadow: 0 0.4rem 0.8rem 0.4rem rgb(161, 160, 228, 0.16);
  background-color: var(--white);
}
.alarm-list {
  display: flex;
  flex-direction: column;
}
.alarm-contents {
  padding: 1.5rem 1.8rem 1rem;
  border-bottom: 1px solid var(--very-light-grey);
}
.alarm-contents-no p {
  margin: 2rem auto;
  font-size: 1.2rem;
}
.alarm-contents-message {
  text-align: left;
  font-size: 1.1rem;
}
.alarm-contents-time {
  margin-top: 1rem;
  text-align: right;
  color: var(--grey);
}
.unread {
  background-color: var(--very-light-orange);
}
.alarm-button {
  font-size: 1.1rem;
  letter-spacing: 0.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 1.5rem auto;
  padding: 0.8rem 1.8rem;
  border-radius: 2em;
  border: 0;
  background-color: var(--orange);
  color: var(--white);
}
.alarm-button-no {
  margin: 2.4rem auto;
  font-size: 1.1rem;
  color: var(--orange);
}
</style>
