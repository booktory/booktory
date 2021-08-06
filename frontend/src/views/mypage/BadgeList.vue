<template>
  <div>
    <p class="label font-body-3">나의 배지</p>
    <div class="badge-list-wrapper">
      <BadgeItem
        v-for="(item, index) in badgeList"
        :key="item.id"
        :index="index"
        :state="item.state"
        :name="item.name"
        :mainBadge="mainBadge"
      />
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
  props: {
    mainBadge: {
      type: String,
    },
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
