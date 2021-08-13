<template>
  <div class="footer">
    <div class="navbar-container">
      <div class="item" @click="$router.push({ name: 'ClubHome' })">
        <p class="sub-item icon">
          <icon-base :iconColor="selected == 'home' ? 'var(--orange)' : 'var(--grey)'"
            ><icon-home
          /></icon-base>
        </p>
        <p :class="{ orange: selected == 'home' }" class="sub-item font-body-5">홈</p>
      </div>
      <div class="item">
        <p class="sub-item icon">
          <icon-base :iconColor="selected == 'rwm' ? 'var(--orange)' : 'var(--grey)'"
            ><icon-rwm
          /></icon-base>
        </p>
        <p :class="{ orange: selected == 'rwm' }" class="sub-item font-body-5">같이읽기</p>
      </div>
      <div class="item" @click="$router.push({ name: 'MyBook' })">
        <p class="sub-item icon">
          <icon-base :iconColor="selected == 'mybook' ? 'var(--orange)' : 'var(--grey)'"
            ><icon-mybook
          /></icon-base>
        </p>
        <p :class="{ orange: selected == 'mybook' }" class="sub-item font-body-5">내 서재</p>
      </div>
      <div class="item" @click="$router.push({ name: 'MyPage' })">
        <p class="sub-item icon">
          <icon-base :iconColor="selected == 'mypage' ? 'var(--orange)' : 'var(--grey)'"
            ><icon-mypage
          /></icon-base>
        </p>
        <p :class="{ orange: selected == 'mypage' }" class="sub-item font-body-5">마이페이지</p>
      </div>
    </div>
  </div>
</template>

<script>
import IconHome from "@/components/icons/IconHome.vue";
import IconRwm from "@/components/icons/IconRwm.vue";
import IconMybook from "@/components/icons/IconMybook.vue";
import IconMypage from "@/components/icons/IconMypage.vue";

export default {
  components: { IconHome, IconRwm, IconMybook, IconMypage },
  name: "Navbar",
  props: {
    selected: {
      type: String,
    },
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
* {
  background-color: var(--white);
}

.navbar-container {
  display: grid;
  padding: 0;
  grid-template-columns: repeat(4, 1fr);
  justify-items: center;
  align-items: center;
}

.sub-item {
  margin: auto;
  padding-top: 0.5em;
}

.orange {
  color: var(--orange);
  font-weight: bold;
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
  bottom: -5em;
}
</style>
