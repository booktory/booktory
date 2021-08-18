<template>
  <div class="youtube-wrapper">
    <!-- <iframe width="90%" :src="apiLink" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>   -->

    <youtube :video-id="this.youtube_parser(this.apiLink)" :player-vars="playerVars" ref="youtube" @playing="playing" class="youtube-player" height="0" width="0"></youtube>
    <img :src="this.imgUrl" alt="rwm thumbnail" @click="playVideo" class="rwm-img"/>
    <!-- <button @click="playVideo">play</button> -->
    
  </div>
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'

Vue.use(VueYoutube)

export default {
  name: "RwmRoomYoutube",
  props:{
    apiLink : String,
    imgUrl : String,
    playerVars: {
      type: Object,
      playerVars: {
        autoplay: 0
      }
    }
  },
  methods: {
    async playVideo() {
      await this.player.playVideo()
      // Do something after the playVideo command
    },
    playing() {
      console.log(' we are watching!!!')
    },
    youtube_parser(url){
      var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
      var match = url.match(regExp);
      return (match&&match[7].length==11)? match[7] : false;
    },
  },
  computed: {
    player () {
      return this.$refs.youtube.player
    }
  },
  created(){
    console.log("created");
    // this.playVideo();
  },
  mounted(){
    console.log("mounted");
    setTimeout(() => {
      console.log('1seconds');
      this.playVideo()
    }, 1000);
  }
};

//?autoplay=1&controls=0&fs=0&loop=1&modestbranding=1
//https://www.youtube.com/embed/hHQoTeJrky0?autoplay=1&controls=0&fs=0&loop=1&modestbranding=1
//https://www.youtube.com/embed?listType=playlist&list=PL93wm79QIgCih2Uu9SS7N4vWJY_sv46NJ
</script>


<style scoped>
.rwm-img{
  width: 80vw;
}
.youtube-wrapper{
  width: 80vw;
  display: relative;
}
.youtube-player{
  display: none;
}
iframe {
  width: 100%;
  max-width: 650px; /* Also helpful. Optional. */
}
</style>
