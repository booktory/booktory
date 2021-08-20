<template>
  <div class="container">
    <div v-if="rwmRoomInfo">
      <RwmRoomHeader />
      <h5 class="rwm-title">{{ this.rwmRoomInfo.name }}</h5>
      <p class="rwm-title-sub">{{ this.rwmRoomInfo.info }}</p>
      <div class="rwm-api-wrapper">
        <RwmRoomYoutube
          v-if="this.rwmRoomInfo"
          :rwmPlayList="this.rwmPlayList[this.$route.query.id * 1]"
          :rwmRoomInfo="this.rwmRoomInfo"
        />
      </div>
      <p class="sub-info">책토리는 bugs::essential; 채널과 함께합니다</p>
      <button class="button-2" @click="clickExitRwmRoom">나가기</button>
      <Navbar :selected="'rwm'" />
    </div>
  </div>
</template>

<script>
import RwmRoomHeader from "@/views/rwm/RwmRoomHeader.vue";
import RwmRoomYoutube from "@/views/rwm/RwmRoomYoutube.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions, mapState } from "vuex";
import router from "@/router";

export default {
  name: "RwmMain",
  components: {
    RwmRoomHeader,
    RwmRoomYoutube,
    Navbar,
  },
  data() {
    return {
      rwmId: this.$route.query.id,
    };
  },
  computed: {
    ...mapState("rwmStore", ["rwmRoomInfo", "rwmPlayList"]),
  },
  methods: {
    ...mapActions("rwmStore", ["exitRwmRoom", "findRwmRoomInfo", "findRwmParticipant"]),
    clickExitRwmRoom() {
      router.go(-2);
    },
  },
  created() {
    this.findRwmRoomInfo(this.$route.query.id);
    this.findRwmParticipant(this.$route.query.id);
  },
  destroyed() {
    this.exitRwmRoom(this.rwmId);
  },
};
</script>

<style scoped>
.rwm-title {
  margin: 1.8rem 0 0.8rem;
}
.rwm-title-sub {
  margin: 0;
  padding: 0 3rem;
  font-size: 1rem;
}
.rwm-api-wrapper {
  width: 32rem;
  margin: 1.8rem auto;
  padding: 1.5rem 0;
  overflow: hidden;
  border-radius: 1rem;
  box-shadow: 0 0.3rem 0.6rem 0.3rem rgb(161, 160, 228, 0.12);
  background-color: var(--white);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
.sub-info {
  font-size: 0.8rem;
  color: var(--grey);
  margin: -0.2rem 0 4rem;
}
</style>
