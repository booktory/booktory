<template>
  <div class="playlist-wrapper">
    <h5 class="playlist-deco">♩ ♪ ♫ ♬ ♩ ♪ ♫ ♬ ♩ ♪ ♫ ♬</h5>
    <div class="prev">
      <span class="playlist-label">이전에 나온 노래</span>
      <h6 class="playlist-title">{{ this.getSong(this.songNum - 1).title }}</h6>
      <span class="playlist-artist">{{ this.getSong(this.songNum - 1).artist }}</span>
    </div>
    <div class="now">
      <span class="playlist-label">지금 흘러나오는 노래</span>
      <h5 class="playlist-title">{{ this.getSong(this.songNum).title }}</h5>
      <span class="playlist-artist">{{ this.getSong(this.songNum).artist }}</span>
    </div>
    <div class="next">
      <span class="playlist-label">다음에 나올 노래</span>
      <h6 class="playlist-title">{{ this.getSong(this.songNum + 1).title }}</h6>
      <span class="playlist-artist">{{ this.getSong(this.songNum + 1).artist }}</span>
    </div>
    <h5 class="playlist-deco">♩ ♪ ♫ ♬ ♩ ♪ ♫ ♬ ♩ ♪ ♫ ♬</h5>
  </div>
</template>

<script>
export default {
  name: "RwmMain",
  components: {},
  props: {
    rwmPlayList: Array,
    songNum: Number,
  },
  computed: {},
  methods: {
    getSong(num) {
      const size = this.rwmPlayList.length;
      if (num >= size || num < 0) return { artist: "", title: "없음" };
      return this.rwmPlayList[num]; //artist - title
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
}
.playlist-wrapper {
  display: flex;
  flex-direction: column;
  gap: 1.9rem;
  margin: 1.2rem 0 1rem;
  padding: 1rem;
}
.playlist-deco {
  margin: 0 auto;
}
.prev,
.now,
.next {
  display: flex;
  flex-direction: column;
  gap: 0.2rem;
}
.playlist-label {
  font-size: 0.9rem;
  font-weight: bold;
  color: var(--orange);
}
.now .playlist-label {
  font-size: 1.2rem;
}
.playlist-title {
  margin: 0.2rem 0 0.1rem;
}
.prev *,
.next * {
  color: var(--grey);
}
</style>
