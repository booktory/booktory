<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">읽을 책 입력</h4>
    <span class="">가져오신 책 이름을 입력해주세요</span>

    <div class="input-div">
      <div>
        <input
          v-model="rwmEnter.bookTitle"
          type="text"
          id="title"
          placeholder="책 이름을 입력해주세요"
          @keyup.enter="clickRwmEnter"
        />
      </div>
    </div>

    <button type="button" class="button-2" @click="clickRwmEnter">입장하기</button>
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions } from "vuex";

export default {
  name: "RwmEnterane",
  components: {
    TopHeader,
    Navbar,
  },
  data(){
    return {
      rwmEnter:{
        id: this.$route.query.id,
        bookTitle: "",
      },
    };
  },
  methods: {
    ...mapActions("rwmStore", ["enterRwmRoom"]),
    clickRwmEnter() {
      this.enterRwmRoom(this.rwmEnter);
    },
  },
  created() {
  },
};
</script>

<style scoped></style>
