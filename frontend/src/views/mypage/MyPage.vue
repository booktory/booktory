<template>
  <div class="container">
    <div>
      <TopHeader />
      <MyProfile
        v-if="userInfo"
        :mainBadge="mainBadge"
        :nickname="userInfo.nickname"
        :email="userInfo.email"
        :profileImg="userInfo.profileImg"
      />
      <BadgeList :badgeList="badgeList" />
      <Navbar :selected="'mypage'" class="footer" />
    </div>
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
