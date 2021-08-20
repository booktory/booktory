<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <!-- <div class="video-nickname"> -->
    <div>
      <h6 style="margin: 0">{{ clientData }}</h6>
    </div>
    <!-- </div> -->
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
<style scoped>
.video-nickname {
  position: absolute;
  top: 0;
}
</style>
