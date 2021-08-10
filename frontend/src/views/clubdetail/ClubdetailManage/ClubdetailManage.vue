<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 관리</h4>
          <div class="menu-wrapper">
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
            <div class="menu-item" @click="clickManage">
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
        </div>
      </div>
    </div>
    <Navbar :selected="'manage'" class="footer" />
  </div>
</template>

<script>
import { mapActions } from "vuex";
import "./ClubdetailManage.scss";
import Navbar from "@/views/clubdetail/Navbar.vue";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import IconEdit from "@/components/icons/IconEdit.vue";
import IconMessage from "@/components/icons/IconMessage.vue";
import IconBook from "@/components/icons/IconBook.vue";
import IconUsers from "@/components/icons/IconUsers.vue";
import IconTrash from "@/components/icons/IconTrash.vue";
import Swal from "sweetalert2";

export default {
  name: "ClubdetailManage",
  components: {
    Navbar,
    TopHeader,
    IconEdit,
    IconMessage,
    IconBook,
    IconUsers,
    IconTrash,
  },
  methods: {
    ...mapActions("clubStore", ["delete"]),
    clickDelete() {
      Swal.fire({
        showCancelButton: true,
        title: "클럽 삭제",
        html: "클럽 정보가 모두 삭제되고 다시 되돌릴 수 없습니다.<br>정말 클럽을 삭제 하시겠습니까?",
        confirmButtonText: "삭제하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          // 나중에 수정할 것!
          // this.delete(clubId);
          this.delete();
        }
      });
    },
  },
};
</script>

<style></style>
