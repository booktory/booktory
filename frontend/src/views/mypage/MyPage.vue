<template>
  <div>
    <MyProfile />
    <BadgeList />
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import MyProfile from "@/views/mypage/MyProfile.vue";
import BadgeList from "@/views/mypage/BadgeList.vue";
import Navbar from "@/views/Navbar.vue";

export default {
  name: "ClubHome",
  components: {
    MyProfile,
    BadgeList,
    Navbar,
  },
};
</script>

<style scoped>
.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
