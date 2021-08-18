<template>
  <div class="rwm-cards-detail" @click="clickRwmCard">
    <div class="rwm-img-wrapper">
      <img :src="img" :alt="img" class="rwm-img" />
    </div>
    <h5 class="rwm-title">{{ name }}</h5>
    <p class="rwm-user">{{ userCount }}명이 참여하고 있어요</p>
    <p class="rwm-info">{{ info }}</p>

    <div v-if="index % 2 == 0" class=""></div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "RwmMainCardsDetail",
  components: {},
  props: {
    index: {
      type: Number,
    },
    id: {
      type: Number,
    },
    img: {
      type: String,
    },
    name: {
      type: String,
    },
    info: {
      type: String,
    },
    userCount: {
      type: Number,
    },
  },
  methods: {
    clickRwmCard() {
      router.push({ name: "RwmEnterance", query: { id: this.$props.id } });
    },
  },
};
</script>

<style scoped>
.rwm-cards-detail {
  border-radius: 1rem;
  box-shadow: 0 0.2rem 0.6rem 0.2rem rgba(161, 160, 228, 0.16);
  background-color: var(--white);
  padding: 0.7rem 0.7rem 1rem;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}
.rwm-cards-detail * {
  margin: 0;
}
.rwm-img {
  border-radius: 0.7rem;
  width: 100%;
  height: auto;
}
.rwm-user {
  color: var(--grey);
}
</style>
