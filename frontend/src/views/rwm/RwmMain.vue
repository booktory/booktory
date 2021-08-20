<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">Read With Me</h4>
      <p class="sub-title-c">
        모두가 함께 모여 책을 읽는 공간입니다 :)<br />마음에 드는 방을 골라서 입장하세요
      </p>
      <RwmMainCards v-if="rwmList" :rwmList="rwmList" />
      <Navbar :selected="'rwm'" />
    </div>
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

<style scoped>
.sub-title-c {
  font-size: 1.1rem;
  margin-bottom: 1.8rem;
}
</style>
