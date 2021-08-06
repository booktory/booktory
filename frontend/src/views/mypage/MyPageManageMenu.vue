<template>
  <div>
    <h4 class="title">계정 관리</h4>
    <div class="menu-wrapper">
      <div class="menu-item">
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
