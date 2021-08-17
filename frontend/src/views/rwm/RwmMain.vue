<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">Read With Me</h4>
    <span class="">모두가 함께 모여 책을 읽는 모임 장소입니다.<br>마음에 드는 방을 선택해서 입장하세요.</span>
    <RwmMainCards v-if="rwmList" :rwmList="rwmList" />
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import RwmMainCards from "@/views/rwm/RwmMainCards.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "RwmMain",
  components: {
    TopHeader,
    Navbar,
    RwmMainCards,
  },
  computed: {
    ...mapState("rwmStore", ["rwmList"]),
  },
  methods: {
    ...mapActions("rwmStore", ["findRwmList"]),
  },
  created() {
    this.findRwmList();
  },
};
</script>

<style scoped></style>
