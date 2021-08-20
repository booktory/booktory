<template>
  <div class="rwm-cards-wrapper">
    <RwmMainCardsDetail
      v-for="(item, index) in rwmList"
      :key="index"
      :index="index"
      :id="item.id"
      :img="item.img"
      :info="item.info"
      :name="item.name"
      :userCount="item.userCount"
    />
  </div>
</template>

<script>
import RwmMainCardsDetail from "@/views/rwm/RwmMainCardsDetail.vue";

export default {
  name: "RwmMainCards",
  components: {
    RwmMainCardsDetail,
  },
  props: {
    rwmList: {
      type: Array,
    },
  },
  computed: {},
  methods: {},
  created() {},
};
</script>

<style scoped>
.rwm-cards-wrapper {
  width: 32rem;
  display: grid;
  grid-template-columns: repeat(2, auto);
  gap: 1.8rem 0.9rem;
  margin: 0 auto;
}
</style>
