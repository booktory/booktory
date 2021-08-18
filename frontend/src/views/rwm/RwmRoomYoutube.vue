<template>
  <div class="youtube-wrapper">
<iframe width="90%" :src="apiLink" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>  </div>
</template>

<script>

export default {
  name: "RwmRoomYoutube",
  props:{
    apiLink : String,
  },
  components: {
  },
  computed: {
  },
  methods: {
  },
  created() {
  },
};

//?autoplay=1&controls=0&fs=0&loop=1&modestbranding=1
//https://www.youtube.com/embed/hHQoTeJrky0?autoplay=1&controls=0&fs=0&loop=1&modestbranding=1
//https://www.youtube.com/embed?listType=playlist&list=PL93wm79QIgCih2Uu9SS7N4vWJY_sv46NJ
</script>

