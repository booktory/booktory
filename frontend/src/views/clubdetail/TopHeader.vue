<template>
  <div class="navbar">
    <div>
      <div v-if="yesBack" class="icon left shadow" @click="$router.go(-1)">
        <icon-base :iconColor="'var(--white)'"><icon-arrow-left /></icon-base>
      </div>
    </div>
    <div></div>
    <div class="icon right shadow" @click="$router.push({ name: 'ClubHome' })">
      <icon-base :iconColor="'var(--white)'"><icon-x /></icon-base>
    </div>
  </div>
</template>

<script>
export default {
  name: "TopHeader",
  data() {
    return {
      yesBack: this.noBack ? false : true,
    };
  },
  props: {
    noBack: {
      type: Boolean,
    },
  },
};
</script>

<style scoped>
.right {
  justify-self: left;
}
.shadow {
  filter: drop-shadow(0.1rem 0.1rem 7px black);
}
</style>
