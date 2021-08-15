<template>
  <div class="rwm-cards-detail" @click="$router.push({ name: 'RwmEnterance' })">
    <div class="rwm-img-wrapper">
       <img :src="img" :alt="img" class="rwm-img" />
    </div>
    <h5 class="">{{name}}</h5>
    <p class="">{{userCount}}명이 참여하고 있어요</p>
    <p class="">{{info}}</p>

    <div v-if="index % 2 == 0" class=""></div>


  </div>
</template>

<script>
export default {
  name: "RwmMainCardsDetail",
  components: {},
  props:{
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
    }
  },
  methods: {},
};
</script>

<style scoped>
.rwm-cards-detail {
  width: 40vw;
  height: auto;
  border-radius: 5px;
  box-shadow: 0 4px 8px 4px rgba(161, 160, 228, 0.16);
  background-color: var(--white);
}
.rwm-img{
    width: 100%;
}

</style>
