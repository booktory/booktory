<template>
  <div>
    <ClubdetailManageUserItem
      v-for="item in userList"
      :key="item.id"
      :userClubId="item.id"
      :nickname="item.userNickname"
      :profileImg="item.userProfileImg"
      :isApply="state == 'apply'"
    />
  </div>
</template>

<script>
import ClubdetailManageUserItem from "@/views/clubdetail/ClubdetailManage/ClubdetailManageUserItem.vue";

export default {
  name: "ClubdetailManageUserList",
  components: {
    ClubdetailManageUserItem,
  },
  props: {
    userList: {
      type: Array,
    },
    state: {
      type: String,
    },
  },
  methods: {},
};
</script>

<style scoped></style>
