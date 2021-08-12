<template>
  <video class="video-area" autoplay />
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
<style scoped>
.video-area {
  width: 100%;
}
</style>
