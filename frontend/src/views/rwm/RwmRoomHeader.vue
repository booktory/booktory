<template>
  <div class="navbar rwm-header-wrapper">
    <div class="icon rwm-header-item" @click="$router.go(-2)">
      <icon-base><icon-arrow-left /></icon-base>
    </div>
    <h4 class="title rwm-header-title">Read With Me</h4>
    <div class="icon rwm-header-btn" @click="viewParticipant">
      <icon-base><icon-users /></icon-base>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  components: {},
  name: "RwmRoomHeader",
  computed: {},
  methods: {
    ...mapActions("rwmStore", ["findRwmParticipant"]),
    // 참여자 목록
    viewParticipant() {
      this.findRwmParticipant(this.$route.query.id);
    },
  },
  created() {},
};
</script>

<style scoped>
.rwm-header-wrapper {
  background-color: var(--medium-orange);
}
.rwm-header-title {
  margin: 0;
  justify-self: center;
}
.rwm-header-btn {
  justify-self: left;
}
</style>
