<template>
  <div class="youtube-wrapper">
    <!-- <iframe width="90%" :src="apiLink" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>   -->
    <!-- youtube화면은 곧 사라질 예정 -->
    책토리는 <b>bugs::essential;</b>채널과 함께합니다.<br> 음악이 재생되지 않을때에는 이미지를 클릭해주세요.    
    <youtube :video-id="this.rwmPlayList.videoId" :player-vars="playerVars" ref="youtube" @playing="playing" @ready="playVideo" @cued="playVideo" height="0" width="0" ></youtube>
    <img :src="this.imgUrl" alt="rwm thumbnail" @click="playVideo" class="rwm-img"/>
    <RwmRoomPlayList v-if="this.rwmPlayList" :rwmPlayList="this.rwmPlayList.timeStamp" :songNum="this.songNum" />
  </div>
</template>
<script>
import Vue from "vue";
import VueYoutube from "vue-youtube";
import RwmRoomPlayList from "@/views/rwm/RwmRoomPlayList.vue";


Vue.use(VueYoutube)

export default {
  name: "RwmRoomYoutube",
  data() {
    return {
      playerVars: {
        autoplay: 1,
        start: 0,
      },
      plyingTime : 0,
      nowSong: [],
      songNum: 0,
    };
  },
  props:{
    imgUrl : String,
    rwmId : Number,
    rwmPlayList: Object,
  },
  components: {
    RwmRoomPlayList,
  },
  computed:{
    player () {
      return this.$refs.youtube.player;
    },
  },
  methods: {
    playVideo() {
      console.log("play video func");
      this.player.playVideo()
    },
    playing() {
      console.log('video start');
    },
    youtube_parser(url){
      var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
      var match = url.match(regExp);
      return (match&&match[7].length==11)? match[7] : false;
    },
    getNowSongArray(){
      let cnt = 0;
      console.log("nowsong");
      console.log(this.rwmPlayList.timeStamp);
      for(let i = 0 ; i < this.rwmPlayList.timeStamp.length; ++i){
        let tmp = this.rwmPlayList.timeStamp[i].startTime;
        for(let j = this.nowSong.length; j < tmp; ++j){
          this.nowSong.push(cnt);
        }
        cnt++;
      }
    },
    async getTime() {
      let res = await this.player.getCurrentTime();
      this.getState();
      console.log("now song");
      res = parseInt(res);
      if(res >= this.nowSong.length) this.songNum = this.nowSong[this.nowSong.length-1];
      else this.songNum = this.nowSong[parseInt(res)]-1;
      
      console.log(this.rwmPlayList.timeStamp[this.songNum]);
    },
    async getState(){
      const res = await this.player.getPlayerState();
      if(res != 1){
        this.playVideo();
      }
      return res;
    }
  },
  mounted(){
    console.log("mounted");
    this.playVideo();
    this.getNowSongArray();
    setInterval(this.getTime, 1000);
  }
};
</script>


<style scoped>
.rwm-img{
  width: 80vw;
}
.youtube-wrapper{
  width: 80vw;
  display: relative;
}
</style>
