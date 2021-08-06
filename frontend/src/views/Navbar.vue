<template>
  <div class="navbar-container">
    <div class="item" @click="$router.push({ name: 'ClubHome' })">
      <p class="sub-item icon">
        <icon-base :iconColor="selected == 'home' ? 'var(--blue)' : 'var(--grey)'"
          ><icon-home
        /></icon-base>
      </p>
      <p class="sub-item font-body-4">홈</p>
    </div>
    <div class="item">
      <p class="sub-item icon">
        <icon-base :iconColor="selected == 'rwm' ? 'var(--blue)' : 'var(--grey)'"
          ><icon-rwm
        /></icon-base>
      </p>
      <p class="sub-item font-body-4">같이읽기</p>
    </div>
    <div class="item">
      <p class="sub-item icon">
        <icon-base :iconColor="selected == 'mybook' ? 'var(--blue)' : 'var(--grey)'"
          ><icon-mybook
        /></icon-base>
      </p>
      <p class="sub-item font-body-4">내 서재</p>
    </div>
    <div class="item" @click="$router.push({ name: 'MyPage' })">
      <p class="sub-item icon">
        <icon-base :iconColor="selected == 'mypage' ? 'var(--blue)' : 'var(--grey)'"
          ><icon-mypage
        /></icon-base>
      </p>
      <p class="sub-item font-body-4">마이페이지</p>
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
};
</script>

<style scoped>
* {
  background-color: lightgrey;
}

.navbar-container {
  display: grid;
  padding: 3% 3% 3%;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
  align-items: start;
}

.sub-item {
  margin: auto;
  padding: 5px;
}
</style>
