<template>
  <div class="container">
    <TopHeader :nickname="'책토리'" />
    <MyProfile :mainBadge="mainBadge" :nickname="nickname" :email="email" />
    <BadgeList :mainBadge="mainBadge" :badgeList="badgeList" />
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import MyProfile from "@/views/mypage/MyProfile.vue";
import BadgeList from "@/views/mypage/BadgeList.vue";
import Navbar from "@/views/Navbar.vue";
import TopHeader from "@/views/TopHeader.vue";

export default {
  name: "MyPage",
  components: {
    TopHeader,
    MyProfile,
    BadgeList,
    Navbar,
  },
  data: function() {
    return {
      mainBadge: "자타공인 책덕후",
      nickname: "책토리",
      email: "booktory@ssafy.com",
      badgeList: [
        { state: false, name: "몰라" },
        { state: true, name: "모임의 시작" },
        { state: true, name: "우리는 친구" },
        { state: true, name: "엄격한 지도자" },
        { state: false, name: "몰라" },
        { state: true, name: "자타공인 책덕후" },
        { state: true, name: "담벼락 매니아" },
        { state: true, name: "훌륭한 팔로워" },
        { state: true, name: "카리스마 리더" },
        { state: false, name: "몰라" },
        { state: true, name: "책을 좋아해" },
        { state: true, name: "나는야 인싸" },
        { state: true, name: "외로워도 슬퍼도" },
        { state: false, name: "몰라" },
        { state: false, name: "몰라" },
      ],
    };
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
