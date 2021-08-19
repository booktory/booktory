<template>
  <div class="youtube-wrapper">
    <youtube
      v-show="false"
      :video-id="this.rwmPlayList.videoId"
      :player-vars="playerVars"
      ref="youtube"
      @playing="playing"
      @ready="playVideo"
      @cued="playVideo"
    ></youtube>
    <img
      :src="this.rwmRoomInfo.img"
      :alt="this.rwmRoomInfo.name"
      :title="this.rwmRoomInfo.name"
      @click="playVideo"
      class="rwm-image"
    />
    <p class="sub-info">음악이 재생되지 않을 때는 이미지를 클릭해주세요</p>
    <RwmRoomPlayList
      v-if="this.rwmPlayList"
      :rwmPlayList="this.rwmPlayList.timeStamp"
      :songNum="this.songNum"
    />
  </div>
</template>
<script>
import Vue from "vue";
import VueYoutube from "vue-youtube";
import RwmRoomPlayList from "@/views/rwm/RwmRoomPlayList.vue";

Vue.use(VueYoutube);

export default {
  name: "RwmRoomYoutube",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        start: 0,
      },
      plyingTime: 0,
      nowSong: [],
      songNum: 0,
      playlistInterval: "",
    };
  },
  props: {
    rwmRoomInfo: Object,
    rwmPlayList: Object,
  },
  components: {
    RwmRoomPlayList,
  },
  computed: {
    player() {
      return this.$refs.youtube.player;
    },
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log("video start");
    },
    youtube_parser(url) {
      var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
      var match = url.match(regExp);
      return match && match[7].length == 11 ? match[7] : false;
    },
    getNowSongArray() {
      let cnt = 0;
      for (let i = 0; i < this.rwmPlayList.timeStamp.length; ++i) {
        let tmp = this.rwmPlayList.timeStamp[i].startTime;
        for (let j = this.nowSong.length; j < tmp; ++j) {
          this.nowSong.push(cnt);
        }
        cnt++;
      }
    },
    async getTime() {
      let res = await this.player.getCurrentTime();
      this.getState();
      res = parseInt(res);
      if (res >= this.nowSong.length) this.songNum = this.nowSong[this.nowSong.length - 1];
      else this.songNum = this.nowSong[parseInt(res)] - 1;
    },
    async getState() {
      const res = await this.player.getPlayerState();
      if (res != 1) {
        this.playVideo();
      }
      return res;
    },
  },
  mounted() {
    this.playVideo();
    this.getNowSongArray();
    this.playlistInterval = setInterval(this.getTime, 1000);
  },
  destroyed() {
    clearInterval(this.playlistInterval);
  },
};
</script>

<style scoped>
.rwm-image {
  width: 29rem;
  border-radius: 1rem;
}
.sub-info {
  font-size: 0.8rem;
  color: var(--grey);
  margin: 0.3rem 0 0;
}
</style>
