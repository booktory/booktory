<template>
  <div v-if="userNickname" class="navbar">
    <div>
      <div v-if="yesBack" class="icon left" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
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
  data() {
    return {
      yesBack: this.isHome ? false : true,
    };
  },
  props: {
    isHome: {
      type: Boolean,
    },
  },
  computed: {
    ...mapState(["userNickname", "userId"]),
    ...mapState("mypageStore", ["alarmRead"]),
  },
  methods: {
    ...mapActions(["fetchUser"]),
    ...mapActions("mypageStore", ["fetchAlarmRead"]),
    pollAlarm() {
      setInterval(() => {
        let chk = true;
        const usersref = fire.database().ref(`users/${this.userId}`).limitToLast(1);
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
    this.fetchUser();
    this.pollAlarm();
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
