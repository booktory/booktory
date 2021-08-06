<template>
  <div>
    <h4 class="title">계정 관리</h4>
    <div class="menu-wrapper">
      <div class="menu-item" @click="clickQuestion">
        <div class="icon">
          <icon-base><icon-mail /></icon-base>
        </div>
        <span class="font-body-1">문의 및 의견</span>
      </div>
      <div class="menu-item">
        <div class="icon">
          <icon-base><icon-edit /></icon-base>
        </div>
        <span class="font-body-1">책토리 이용약관</span>
      </div>
      <div class="menu-item">
        <div class="icon">
          <icon-base><icon-logout /></icon-base>
        </div>
        <span class="font-body-1">로그아웃</span>
      </div>
      <div class="menu-item">
        <div class="icon">
          <icon-base><icon-trash /></icon-base>
        </div>
        <span class="font-body-1">회원 탈퇴</span>
      </div>
    </div>
  </div>
</template>

<script>
import IconMail from "@/components/icons/IconMail.vue";
import IconEdit from "@/components/icons/IconEdit.vue";
import IconLogout from "@/components/icons/IconLogout.vue";
import IconTrash from "@/components/icons/IconTrash.vue";

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
    clickQuestion() {
      location.href =
        "mailto:booktory607@gmail.com?subject=책토리 Feedback&body=궁금하신 점이나 버그, 의견 등을 남겨주시면 확인 후 메일로 답변을 보내드릴게요!%0D%0A내용을 자세하게 적어 보내주시면 더 좋은 답변을 드릴 수 있습니다:)";
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
  margin: 10% 10.7% 0;
  padding: 0;
}
.menu-item {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 1.6em;
}
</style>
