<template>
  <div class="container">
    <RwmRoomHeader />
    <div class="rwm-api-wrapper">
        YOUTUBE API!!!!


    </div>
    <button class="button-2" @click="clickExitRwmRoom">나가기</button>
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import RwmRoomHeader from "@/views/rwm/RwmRoomHeader.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions } from "vuex";

export default {
  name: "RwmMain",
  components: {
    RwmRoomHeader,
    Navbar,
  },
  computed: {
  },
  methods: {
    ...mapActions("rwmStore", ["exitRwmRoom"]),
    clickExitRwmRoom(){
      this.exitRwmRoom(this.$route.query.id);
    },  
  },
  created() {
  },
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
