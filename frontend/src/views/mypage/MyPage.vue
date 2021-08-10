<template>
  <div class="container">
    <TopHeader />
    <MyProfile
      v-if="userInfo"
      :mainBadge="this.mainBadge"
      :nickname="this.userInfo.nickname"
      :email="this.userInfo.email"
    />
    <BadgeList :badgeList="this.badgeList" />
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import MyProfile from "@/views/mypage/MyProfile.vue";
import BadgeList from "@/views/mypage/BadgeList.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "MyPage",
  components: {
    TopHeader,
    MyProfile,
    BadgeList,
    Navbar,
  },
  computed: {
    ...mapState("mypageStore", ["userInfo", "mainBadge", "badgeList"]),
  },
  data: function () {
    return {};
  },
  methods: {
    ...mapActions("mypageStore", ["findUserInfo"]),
  },
  created() {
    this.findUserInfo();
  },
};
</script>

<style scoped></style>
