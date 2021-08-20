<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">가입신청 관리</h4>
          <h6 class="sub-title m-top-5">신청한 회원 목록</h6>
          <ClubdetailManageUserList
            v-if="applyList && applyList.length > 0"
            class="user-list"
            :userList="applyList"
            :state="'apply'"
          />
          <div class="user-list empty" v-else>
            <span class="font-body-4">신청한 회원이 없습니다</span>
          </div>
          <h6 class="sub-title">가입된 회원 목록</h6>
          <ClubdetailManageUserList class="user-list" :userList="joinedList" :state="'accept'" />
        </div>
      </div>
      <Navbar :selected="'manage'" :clubId="this.clubId" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Navbar from "@/views/clubdetail/Navbar.vue";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import ClubdetailManageUserList from "@/views/clubdetail/ClubdetailManage/ClubdetailManageUserList.vue";

export default {
  name: "ClubdetailManageUser",
  components: {
    Navbar,
    TopHeader,
    ClubdetailManageUserList,
  },
  data() {
    return {
      clubId: this.$route.query.clubId,
    };
  },
  computed: {
    ...mapState("clubStore", ["applyList", "joinedList", "clubImage"]),
  },
  methods: {
    ...mapActions("clubStore", ["findClubInfo", "findApplyList", "findJoinedList"]),
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  async created() {
    this.findClubInfo(this.clubId);
    this.findApplyList(this.clubId);
    this.findJoinedList(this.clubId);
    await new Promise((resolve) => setTimeout(resolve, 100));
    this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailManageUser.scss";
</style>
