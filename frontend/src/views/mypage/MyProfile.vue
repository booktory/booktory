<template>
  <div>
    <div class="wrapper">
      <h4 class="title">나의 프로필</h4>
      <div class="icon settings-btn" @click="$router.push({ name: 'MyPageManage' })">
        <icon-base><icon-settings /></icon-base>
      </div>
    </div>
    <div class="user-info">
      <img
        class="profile-image"
        :src="profileImg ? profileImg : 'https://via.placeholder.com/50'"
        alt="profile-image"
      />
      <div class="info-frame">
        <span v-if="mainBadge" class="font-body-4 main-badge">{{ mainBadge }}</span>
        <h5>{{ nickname }}</h5>
        <span class="font-body-4">{{ email }}</span>
      </div>
    </div>
    <button
      type="button"
      class="button-1 update-btn"
      @click="$router.push({ name: 'MyProfileUpdate' })"
    >
      프로필 수정
    </button>
  </div>
</template>

<script>
import IconSettings from "@/components/icons/IconSettings.vue";

export default {
  name: "MyProfile",
  components: {
    IconSettings,
  },
  props: {
    mainBadge: {
      type: String,
    },
    nickname: {
      type: String,
    },
    email: {
      type: String,
    },
    profileImg: {
      type: String,
    },
  },
};
</script>

<style scoped>
.wrapper {
  position: relative;
}
.settings-btn {
  position: absolute;
  top: 0;
  right: 0;
  margin-right: 8%;
}
.update-btn {
  margin-top: 1.5rem;
}
.main-badge {
  color: var(--brown);
  font-weight: bold;
}
.user-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4.8%;
  margin: 6.4% 8.2% 0;
  padding: 0 2%;
}
.profile-image {
  width: 6em;
  height: 6em;
  border-radius: 10em;
}
.info-frame {
  display: flex;
  flex-direction: column;
}
.info-frame * {
  text-align: left;
  margin: 1% 0;
}
</style>
