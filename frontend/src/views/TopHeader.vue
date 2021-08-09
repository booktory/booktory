<template>
  <div class="navbar">
    <div class="icon left" @click="$router.go(-1)">
      <icon-base><icon-arrow-left /></icon-base>
    </div>
    <span class="font-body-4 welcome">안녕하세요, {{ user.nickname }}님</span>
    <div class="icon right">
      <icon-base :width="'1.6em'" :height="'1.6em'"><icon-bell /></icon-base>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  components: {},
  name: "Navbar",
  computed: {
    ...mapState(["user"]),
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
}
.welcome {
  padding-right: 0.4em;
}
</style>
