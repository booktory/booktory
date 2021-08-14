<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="updateReadStatus">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <h4 class="title">알림</h4>
    <div class="alarm-wrapper">
      <div class="alarm-list">
        <div v-if="alarmList.length != 0">
          <div class="" v-for="(alarm, idx) in alarmList" :key="idx">
            <div class="alarm-contents unread" v-if="alarm.status == 0">
              <div class="alarm-contents-message">{{ alarm.message }}</div>
              <div class="alarm-contents-time">{{ convertTime(alarm.time) }}</div>
            </div>
            <div class="alarm-contents" v-else>
              <div class="alarm-contents-message">{{ alarm.message }}</div>
              <div class="alarm-contents-time">{{ convertTime(alarm.time) }}</div>
            </div>
          </div>
        </div>
        <div v-else>
          <div class="alarm-contents-no">
            <p>받은 알림이 없습니다.</p>
          </div>
        </div>
        <div v-if="alarmList.length < page * 5 && alarmList.length > 0">
          <p class="alarm-button-no">더 이상 알림이 존재하지 않습니다.</p>
        </div>
        <div v-else-if="alarmList.length >= page * 5">
          <button type="button" class="alarm-button" @click="getMoreAlarm">
            더 많은 알림 보기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import fire from "@/firebase.js";
import router from "@/router";

export default {
  name: "Notification",
  components: {},
  computed: {
    ...mapState(["userNickname"]),
  },
  data() {
    return {
      alarmList: [],
      page: 1,
    };
  },
  created() {
    this.alarmList = [];
    const usersref = fire.database().ref(`users/${this.userNickname}`).limitToLast(5);
    usersref.on("value", (list) => {
      const data = list.val();
      for (let key in data) {
        this.alarmList.unshift({
          message: data[key].message,
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
        .ref(`users/${this.userNickname}`)
        .on("value", (list) => {
          const data = list.val();
          for (let key in data) {
            if (data[key].readStatus === 0) {
              fire.database().ref(`users/${this.userNickname}/${key}`).update({
                readStatus: 1,
              });
            }
          }
        });
      router.go(-1);
    },
    getMoreAlarm() {
      let prev = this.page;
      this.page += 1;
      const usersref = fire
        .database()
        .ref(`users/${this.userNickname}`)
        .limitToLast(5 * this.page);

      usersref.on("value", (list) => {
        const data = list.val();
        let arrData = Object.entries(data).reverse();
        var idx = 1;
        for (let i = 1; i <= arrData.length; i++) {
          if (idx++ > prev * 5) {
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
      let curTime = new Date();
      let alarmTime = new Date(time);
      let diffTime = Math.floor((curTime.getTime() - alarmTime.getTime()) / 1000 / 60);
      if (diffTime < 1) return "방금 전";
      if (diffTime < 60) return `${diffTime}분 전`;

      let diffTimeHour = Math.floor(diffTime / 60);
      if (diffTimeHour < 24) return `${diffTimeHour}시간 전`;

      let diffTimeDay = Math.floor(diffTimeHour / 24);
      if (diffTimeDay < 365) return `${diffTimeDay}일 전`;

      return `${Math.floor(diffTimeDay / 365)}년 전`;
    },
  },
};
</script>
<style scoped>
.alarm-wrapper {
  margin: 5% 7%;
  width: 86%;
  padding: 1%;
  border-radius: 1rem;
  box-shadow: 0 0.4em 0.8em 0 rgb(142 141 208 / 16%);
  background-color: var(--white);
}

.alarm-list {
  padding: 0.5rem 0.3rem 0;
}

.alarm-contents {
  padding: 4% 3%;
  border-bottom: 1px solid var(--very-light-grey);
}

.alarm-contents-no {
  line-height: 1.2;
}

.alarm-contents-message {
  text-align: left;
}

.alarm-contents-time {
  font-size: 0.7rem;
  margin-top: 1rem;
  text-align: right;
}

.unread {
  background-color: var(--very-light-orange);
}

.alarm-button {
  font-size: 1rem;
  letter-spacing: 0.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 5% auto;
  padding: 2.5% 4%;
  border-radius: 3em;
  border: 0;
  background-color: var(--orange);
  color: var(--white);
}

.alarm-button-no {
  padding: 2.5% 4%;
}
</style>
