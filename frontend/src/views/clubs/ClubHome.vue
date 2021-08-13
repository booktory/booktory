<template>
  <div class="container bg-img">
    <TopHeader />
    <ClubList />
    <div class="text-right">
      <span class="text-link" @click="$router.push({ name: 'ClubSearch' })">클럽 찾기 | </span>
      <span class="text-link" @click="$router.push({ name: 'ClubCreate' })">클럽 만들기</span>
    </div>
    <Navbar :selected="'home'" class="footer" />
  </div>
</template>

<script>
import ClubList from "@/views/clubs/ClubList.vue";
import Navbar from "@/views/Navbar.vue";
import TopHeader from "@/views/TopHeader.vue";

export default {
  name: "ClubHome",
  components: {
    ClubList,
    Navbar,
    TopHeader,
  },
  data: function () {
    return {
      showNavbar: true,
      lastScrollPosition: 0,
    };
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },
  methods: {
    onScroll() {
      let fixedNavbar = document.querySelector(".footer");
      // Get the current scroll position
      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop;
      // Because of momentum scrolling on mobiles, we shouldn't continue if it is less than zero
      if (currentScrollPosition < 0) {
        return;
      }
      // Here we determine whether we need to show or hide the navbar
      this.showNavbar = currentScrollPosition < this.lastScrollPosition;
      if (this.showNavbar) {
        console.log("scroll up");
        fixedNavbar.classList.remove("show");
      } else {
        console.log("scroll down");
        fixedNavbar.classList.add("show");
      }
      // Set the current scroll position as the last scroll position
      this.lastScrollPosition = currentScrollPosition;
    },
  },
};
</script>

<style scoped>
.bg-img {
  height: 100%;
  background-image: linear-gradient(to bottom, var(--white) 0%, var(--light-grey) 100%),
    url("./images/club-backgroud.png");
  background-blend-mode: multiply;
  background-size: cover;
}
.text-right {
  text-align: right;
  margin-right: 10%;
}
.text-link {
  font-size: 1.5rem;
  color: var(--white);
}

.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
  transition: bottom 0.8s ease;
}
.show {
  bottom: -4.5em;
}
</style>
