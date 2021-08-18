<template>
  <div class="book-item-wrapper">
    <div class="book-thumbnail-wrapper">
      <img :src="thumbnail" :alt="title" :title="title" class="book-thumbnail" @click="clickBook" />
    </div>
    <div v-if="index % 4 == 0" class="book-bottom"></div>
  </div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  name: "BookItem",
  components: {},
  props: {
    index: {
      type: Number,
    },
    id: {
      type: Number,
    },
    thumbnail: {
      type: String,
    },
    title: {
      type: String,
    },
  },
  methods: {
    clickBook() {
      Swal.fire({
        title: this.title,
        imageUrl: this.thumbnail,
        html: "<div></div>",
        showConfirmButton: false,
      });
    },
  },
};
</script>

<style scoped>
.book-item-wrapper {
  position: relative;
  align-items: center;
}
.book-thumbnail-wrapper {
  position: relative;
  align-items: end;
}
.book-thumbnail {
  width: 5.5rem;
  height: 8rem;
  margin-top: 1.8rem;
  border-radius: 1rem;
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
.book-bottom {
  position: absolute;
  z-index: 10;
  left: -1.5rem;
  bottom: -0.4rem;
  width: 31rem;
  height: 0.8rem;
  margin: 0 auto;
  border-radius: 1rem;
  background-color: var(--very-light-brown);
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
</style>
