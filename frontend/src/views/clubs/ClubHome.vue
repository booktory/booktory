<template>
  <div class="container bg-img">
    <div>
      <TopHeader :isHome="true" />
      <ClubList />
      <div class="text-right">
        <span @click="clickSearch">클럽 찾기</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span
          @click="$router.push({ name: 'ClubCreate' })"
          >클럽 만들기</span
        >
      </div>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import ClubList from "@/views/clubs/ClubList.vue";
import Navbar from "@/views/Navbar.vue";
import TopHeader from "@/views/TopHeader.vue";
import router from "@/router";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubHome",
  components: {
    ClubList,
    Navbar,
    TopHeader,
  },
  computed: {
    ...mapState("clubStore", ["clubImage"]),
  },
  watch: {
    clubImage: {
      handler() {
        this.setBackgroundImage();
      },
    },
  },
  methods: {
    ...mapActions("searchStore", ["initClubList"]),
    clickSearch() {
      this.initClubList();
      router.push({ name: "ClubSearch" });
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--background-" + this.clubImage + ")";
    },
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style scoped>
.bg-img {
  width: 100vw;
  height: 100%;
  min-height: 100vh;
  background-size: cover;
  z-index: -1;
}
.text-right {
  text-align: right;
  font-size: 1.4rem;
  margin: 0 3.5rem;
  color: var(--dark-grey);
}
.text-right span {
  font-size: 1.4rem;
  font-weight: bold;
}
</style>
