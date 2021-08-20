<template>
  <div class="footer">
    <div class="navbar-container">
      <div class="item" @click="clickHome">
        <p :class="{ orange: selected == 'home' }" class="sub-item font-body-4">클럽 홈</p>
      </div>
      <div class="item" @click="clickMeeting">
        <p :class="{ orange: selected == 'meeting' }" class="sub-item font-body-4">모임 일정</p>
      </div>
      <div class="item" @click="clickBoard">
        <p :class="{ orange: selected == 'board' }" class="sub-item font-body-4">담벼락</p>
      </div>
      <div class="item" @click="clickManage">
        <p :class="{ orange: selected == 'manage' }" class="sub-item font-body-4">클럽 관리</p>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "Navbar",
  props: {
    selected: {
      type: String,
    },
    clubId: {},
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
    // 클럽 홈
    clickHome() {
      router.push({ name: "ClubdetailHome", query: { clubId: this.clubId } });
    },
    // 모임 일정
    clickMeeting() {
      router.push({ name: "ClubdetailMeeting", query: { clubId: this.clubId } });
    },
    // 담벼락
    clickBoard() {
      router.push({ name: "ClubdetailBoard", query: { clubId: this.clubId } });
    },
    // 클럽 관리
    clickManage() {
      router.push({ name: "ClubdetailManage", query: { clubId: this.clubId } });
    },
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
        fixedNavbar.classList.remove("show");
      } else {
        fixedNavbar.classList.add("show");
      }
      // Set the current scroll position as the last scroll position
      this.lastScrollPosition = currentScrollPosition;
    },
  },
};
</script>

<style scoped>
* {
  background-color: var(--white);
}

.navbar-container {
  display: grid;
  padding: 1em 0;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
  align-items: center;
}

.sub-item {
  margin: auto;
  padding: 5px;
}
.orange {
  color: var(--orange);
  font-weight: bold;
}

.footer {
  width: 100%;
  height: 4.5rem;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
  transition: bottom 0.8s ease;
  z-index: 10;
}
.show {
  bottom: -4.5em;
}
</style>
