<template>
  <div class="navbar">
    <div>
      <div v-if="yesBack" class="icon left" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <div></div>
    <div class="icon right" @click="$router.push({ name: 'ClubHome' })">
      <icon-base><icon-x /></icon-base>
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
</style>
