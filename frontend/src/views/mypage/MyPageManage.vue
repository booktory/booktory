<template>
  <div class="container">
    <TopHeader :nickname="'책토리'" />
    <MyPageManageMenu />
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import MyPageManageMenu from "@/views/mypage/MyPageManageMenu.vue";

export default {
  name: "MyPageManage",
  components: {
    TopHeader,
    Navbar,
    MyPageManageMenu,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
