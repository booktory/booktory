<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">읽을 책 입력</h4>
    <span class="">가져오신 책 이름을 입력해주세요</span>

    <div class="input-div">
      <div>
        <input
          type="text"
          placeholder="책 이름을 입력해주세요"
        />
      </div>
    </div>

    <button type="button" class="button-2">입장하기</button>
    <Navbar :selected="'rwm'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";

export default {
  name: "RwmEnterane",
  components: {
    TopHeader,
    Navbar,
  },
  computed: {
  },
  methods: {
  },
  created() {
  },
};
</script>

<style scoped></style>
