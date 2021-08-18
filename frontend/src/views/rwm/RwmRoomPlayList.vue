<template>
  <div>
      <div>이전곡 : {{this.getSong((this.songNum)-1)}}</div>

      <div>현재곡 : {{this.getSong(this.songNum)}}</div>

      <div>다음곡 : {{this.getSong(this.songNum+1)}}</div>

  </div>
</template>

<script>
export default {

  name: "RwmMain",
  components: {},
  props:{
    rwmPlayList: Array,
    songNum: Number,
  },
  computed: {
  },
  methods: {
    getSong(num){
      const size = this.rwmPlayList.length;
      if(num >= size || num < 0) return "없음";
      return (this.rwmPlayList[num].artist + " - " + this.rwmPlayList[num].title); //artist - title
    }
  },
}

</script>
