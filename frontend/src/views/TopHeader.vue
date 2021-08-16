<template>
  <div v-if="userNickname" class="navbar">
    <div class="icon left" @click="$router.go(-1)">
      <icon-base><icon-arrow-left /></icon-base>
    </div>
    <span class="font-body-4 welcome">안녕하세요, {{ userNickname }}님</span>
    <div class="icon right" @click="$router.push({ name: 'Notification' })">
      <icon-base :width="'1.6em'" :height="'1.6em'"><icon-bell /></icon-base>
      <div v-if="!alarmRead" class="noti"></div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import fire from "@/firebase.js";

export default {
  components: {},
  name: "TopHeader",
  computed: {
    ...mapState(["userNickname"]),
    ...mapState("mypageStore", ["alarmRead"]),
  },
  methods: {
    ...mapActions(["fetchUser"]),
    ...mapActions("mypageStore", ["fetchAlarmRead"]),
    pollAlarm() {
      setInterval(() => {
        let chk = true;
        const usersref = fire.database().ref(`users/${this.userNickname}`).limitToLast(1);
        usersref.on("value", (list) => {
          const data = list.val();
          for (let key in data) {
            if (data[key].readStatus == 0) {
              chk = false;
            }
          }
        });
        this.fetchAlarmRead(chk);
      }, 500);
    },
  },
  created() {
    this.pollAlarm();
  },
  mounted() {
    this.fetchUser();
  },
};
</script>

<style scoped>
.right {
  justify-self: left;
  align-items: center;
  display: flex;
  position: relative;
}
.welcome {
  padding-right: 0.4em;
}
.noti {
  width: 0.4em;
  height: 0.4em;
  background-color: var(--orange);
  border-radius: 50%;
  position: absolute;
  left: 1.6em;
  bottom: 1.6em;
}
</style>
