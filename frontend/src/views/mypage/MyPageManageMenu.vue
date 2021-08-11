<template>
  <div>
    <h4 class="title">계정 관리</h4>
    <div class="menu-wrapper">
      <div class="menu-item" @click="clickQuestion">
        <div class="icon">
          <icon-base><icon-mail /></icon-base>
        </div>
        <span class="font-body-3">의견 보내기</span>
      </div>
      <div class="menu-item" @click="$router.push({ name: 'BooktoryTos' })">
        <div class="icon">
          <icon-base><icon-edit /></icon-base>
        </div>
        <span class="font-body-3">책토리 이용약관</span>
      </div>
      <div class="menu-item" @click="clickLogout">
        <div class="icon">
          <icon-base><icon-logout /></icon-base>
        </div>
        <span class="font-body-3">로그아웃</span>
      </div>
      <div class="menu-item" @click="$router.push({ name: 'DeleteUser' })">
        <div class="icon">
          <icon-base><icon-trash /></icon-base>
        </div>
        <span class="font-body-3">회원 탈퇴</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import IconMail from "@/components/icons/IconMail.vue";
import IconEdit from "@/components/icons/IconEdit.vue";
import IconLogout from "@/components/icons/IconLogout.vue";
import IconTrash from "@/components/icons/IconTrash.vue";
import Swal from "sweetalert2";

export default {
  name: "MyPageManageMenu",
  components: {
    IconMail,
    IconEdit,
    IconLogout,
    IconTrash,
  },
  props: {},
  methods: {
    ...mapActions(["logout"]),
    clickQuestion() {
      location.href =
        "mailto:booktory607@gmail.com?subject=책토리 Feedback&body=책토리 홈페이지를 이용하면서 궁금한 점, 버그, 의견 등을 이메일로 보내주시면 확인 후 답변을 보내드리겠습니다!%0D%0A내용을 자세하게 적어주시면 더 좋은 답변을 드릴 수 있어요:)";
    },
    clickLogout() {
      Swal.fire({
        showCancelButton: true,
        title: "로그아웃",
        text: "로그아웃 하시겠습니까?",
        confirmButtonText: "로그아웃",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.logout();
        }
      });
    },
  },
};
</script>

<style scoped>
.menu-wrapper {
  width: auto;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  gap: 2em;
  margin: 8% 10.7% 0;
  padding: 0;
}
.menu-item {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 1em;
}
.icon {
  width: 1.6em;
  height: 1.6em;
}
</style>
