<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 관리</h4>
          <div v-if="true" class="menu-wrapper">
            <div class="menu-item" @click="$router.push({ name: 'ClubdetailManageUpdate' })">
              <div class="icon">
                <icon-base><icon-edit /></icon-base>
              </div>
              <span class="font-body-3">클럽 정보 수정</span>
            </div>
            <div class="menu-item" @click="$router.push({ name: 'ClubHome' })">
              <div class="icon">
                <icon-base><icon-message /></icon-base>
              </div>
              <span class="font-body-3">문의 게시판</span>
            </div>
            <div class="menu-item" @click="$router.push({ name: 'ClubdetailManageCreate' })">
              <div class="icon">
                <icon-base><icon-book /></icon-base>
              </div>
              <span class="font-body-3">읽을 책 추가</span>
            </div>
            <div class="menu-item" @click="$router.push({ name: 'ClubdetailManageUser' })">
              <div class="icon">
                <icon-base><icon-users /></icon-base>
              </div>
              <span class="font-body-3">가입신청 관리</span>
            </div>
            <div class="menu-item" @click="clickDelete">
              <div class="icon">
                <icon-base><icon-trash /></icon-base>
              </div>
              <span class="font-body-3">클럽 삭제</span>
            </div>
          </div>
          <div v-else class="menu-wrapper">
            <div class="menu-item" @click="clickDeleteUser">
              <div class="icon">
                <icon-base><icon-logout /></icon-base>
              </div>
              <span class="font-body-3">클럽 탈퇴</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

import Navbar from "@/views/clubdetail/Navbar.vue";
import IconEdit from "@/components/icons/IconEdit.vue";
import IconMessage from "@/components/icons/IconMessage.vue";
import IconBook from "@/components/icons/IconBook.vue";
import IconUsers from "@/components/icons/IconUsers.vue";
import IconTrash from "@/components/icons/IconTrash.vue";
import IconLogout from "@/components/icons/IconLogout.vue";
import Swal from "sweetalert2";

export default {
  name: "ClubdetailManage",
  components: {
    Navbar,
    IconEdit,
    IconMessage,
    IconBook,
    IconUsers,
    IconTrash,
    IconLogout,
  },
  computed: {
    ...mapState("clubStore", ["isLeader"]),
  },
  methods: {
    ...mapActions("clubStore", ["deleteClub", "deleteClubUser"]),
    clickDelete() {
      Swal.fire({
        showCancelButton: true,
        title: "클럽 삭제",
        html: "클럽 정보가 모두 삭제되고 다시 되돌릴 수 없습니다.<br>정말 클럽을 삭제 하시겠습니까?",
        confirmButtonText: "삭제하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteClub();
        }
      });
    },
    clickDeleteUser() {
      Swal.fire({
        showCancelButton: true,
        title: "클럽 탈퇴",
        html: "정말 클럽에서 탈퇴 하시겠습니까?",
        confirmButtonText: "탈퇴하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteClubUser();
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailManage.scss";
</style>
