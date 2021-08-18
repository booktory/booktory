<template>
  <div class="container">
    <RwmRoomHeader />
    <div class="rwm-api-wrapper">
      <RwmRoomYoutube v-if="this.rwmRoomInfo" :apiLink = "this.rwmRoomInfo.apiLink"/>
      <div v-if="this.rwmRoomInfo">
        <!-- <img :src= "this.rwmRoomInfo.img" alt="rwm thumbnail" width="80%"/> -->
        <h5 class="">{{this.rwmRoomInfo.name}}</h5>
        <p class="">{{this.rwmRoomInfo.userCount}}명이 참여하고 있어요</p>
        <p class="">{{this.rwmRoomInfo.info}}</p>      
      </div>

    </div>
    <button class="button-2" @click="clickExitRwmRoom">나가기</button>
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import RwmRoomHeader from "@/views/rwm/RwmRoomHeader.vue";
import RwmRoomYoutube from "@/views/rwm/RwmRoomYoutube.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "RwmMain",
  components: {
    RwmRoomHeader,
    RwmRoomYoutube,
    Navbar,
  },
  computed: {
    ...mapState("rwmStore", ["rwmRoomInfo"]),
  },
  methods: {
    ...mapActions("rwmStore", ["exitRwmRoom", "findRwmRoomInfo"]),
    clickExitRwmRoom(){
      this.exitRwmRoom(this.$route.query.id);
    },  
  },
  created() {
    this.findRwmRoomInfo(this.$route.query.id);
  },
  created() {},
};
</script>

<style scoped>
.rwm-api-wrapper {
  width: 70vw;
  height: 60vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: baseline;
  margin: 2rem auto;
  padding: 3rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(142, 141, 208, 0.16);
  background-color: var(--white);
}
</style>
