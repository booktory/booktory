<template>
  <div class="navbar">
    <div class="icon left" @click="$router.go(-1)">
      <icon-base><icon-arrow-left /></icon-base>
    </div>
    <span v-if="userNickname" class="font-body-4 welcome">안녕하세요, {{ userNickname }}님</span>
    <div v-if="userNickname" class="icon right">
      <icon-base :width="'1.6em'" :height="'1.6em'"><icon-bell /></icon-base>
      <div v-if="true" class="noti"></div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  components: {},
  name: "TopHeader",
  computed: {
    ...mapState(["userNickname"]),
  },
  methods: {},
  created() {},
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
