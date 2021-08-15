<template>
  <div class="clubdetail-container">
    <div class="bg-image">
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
    </div>
    <Navbar :selected="'manage'" class="footer" />
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
  computed: {
    ...mapState("clubStore", ["applyList", "joinedList"]),
  },
  methods: {
    ...mapActions("clubStore", ["findApplyList", "findJoinedList"]),
  },
  created() {
    this.findApplyList();
    this.findJoinedList();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailManageUser.scss";
.user-list {
  margin: 1rem 0 3rem 0.6rem;
}
.empty {
  padding: 0.7rem 0.4rem;
}
</style>
