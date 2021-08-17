<template>
  <div class="rwm-header-wrapper">
    <div class="icon rwm-header-item" @click="clickExitRwmRoom">
      <icon-base><icon-arrow-left /></icon-base>
    </div>
    <h4 class="title rwm-header-item">Read With Me</h4>

    <!-- <div v-if="rwmParticipant">
      {{rwmParticipant}}
    </div> -->
    
    <div class="icon rwm-header-item" @click="viewParticipant">
      <icon-base><icon-users /></icon-base>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  components: { 
  },
  name: "RwmRoomHeader",
  computed: {
  },
  methods: {
    ...mapActions("rwmStore", ["exitRwmRoom", "findRwmParticipant"]),

    clickExitRwmRoom(){
      this.exitRwmRoom(this.$route.query.id);
    },
    viewParticipant(){
      this.findRwmParticipant(this.$route.query.id);
    },
  },
  created() {
  },
};
</script>

<style scoped>
.rwm-header-wrapper{
  display: flex;
  justify-content:space-between;
  align-items: baseline;
  background-color: #fdcc97;
}
.rwm-header-item{
  margin: 1.8rem;
}
</style>
