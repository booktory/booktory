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
  computed: {
  },
  methods: {
  },
  created() {
  },
};
</script>

<style scoped>
.rwm-cards-wrapper {
  width: auto;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 2%;
  margin: 0 9% 20rem;
}

</style>
