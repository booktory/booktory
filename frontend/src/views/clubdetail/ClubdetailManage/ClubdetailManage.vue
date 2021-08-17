<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 관리</h4>
          <div v-if="isLeader" class="menu-wrapper">
            <div class="menu-item" @click="$router.push({ name: 'ClubdetailManageUpdate' })">
              <div class="icon">
                <icon-base><icon-edit /></icon-base>
              </div>
              <span class="font-body-3">클럽 정보 수정</span>
            </div>
            <div class="menu-item" @click="clickQuestion">
              <div class="icon">
                <icon-base><icon-message /></icon-base>
              </div>
              <span class="font-body-3">문의 게시판</span>
            </div>
            <div class="menu-item" @click="$router.push({ name: 'ClubdetailBookAdd' })">
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
      <Navbar :selected="'manage'" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import IconEdit from "@/components/icons/IconEdit.vue";
import IconMessage from "@/components/icons/IconMessage.vue";
import IconBook from "@/components/icons/IconBook.vue";
import IconUsers from "@/components/icons/IconUsers.vue";
import IconTrash from "@/components/icons/IconTrash.vue";
import IconLogout from "@/components/icons/IconLogout.vue";
import Swal from "sweetalert2";
import router from "@/router";

export default {
  name: "ClubdetailManage",
  components: {
    TopHeader,
    Navbar,
    IconEdit,
    IconMessage,
    IconBook,
    IconUsers,
    IconTrash,
    IconLogout,
  },
  computed: {
    ...mapState("clubStore", ["clubId", "isLeader", "clubImage"]),
  },
  methods: {
    ...mapActions("clubStore", ["deleteClub", "deleteClubUser"]),
    // 문의 게시판 이동
    clickQuestion() {
      router.push({ name: "ClubQuestion", query: { clubId: this.clubId } });
    },
    // 클럽 삭제
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
    // 클럽 탈퇴
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
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailManage.scss";
</style>
