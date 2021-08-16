<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">Read With Me</h4>
    <span class="">YOUTUBE API!!!!</span>
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
// import { mapActions, mapState } from "vuex";

export default {
  name: "RwmMain",
  components: {
    TopHeader,
    Navbar,
  },
  computed: {
    // ...mapState("rwmStore", ["rwmList"]),
  },
  methods: {
    // ...mapActions("rwmStore", ["findRwmList"]),
  },
  created() {
    // this.findRwmList();
  },
};
</script>

<style scoped></style>
