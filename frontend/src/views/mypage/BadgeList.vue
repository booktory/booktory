<template>
  <div>
    <p class="label font-body-3">나의 배지</p>
    <div class="badge-list-wrapper">
      <BadgeItem v-for="(badge, index) in badgeList" :key="index" :badgeId="index" :badge="badge" />
    </div>
  </div>
</template>

<script>
import BadgeItem from "@/views/mypage/BadgeItem.vue";

export default {
  name: "BadgeList",
  components: {
    BadgeItem,
  },
  computed: {},
  props: {
    badgeList: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  text-align: left;
  margin: 5% 0 3% 12%;
}
.badge-list-wrapper {
  width: auto;
  display: grid;
  grid-template-columns: repeat(3, auto);
  gap: 2%;
  margin: 0 10.7% 0;
}
</style>
