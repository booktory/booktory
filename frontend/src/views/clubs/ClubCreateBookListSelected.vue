<template>
  <div>
    <div class="club-card">
      <div class="icon delete-icon" @click="deleteBook">
        <icon-base :iconColor="'var(--light-brown)'"><icon-delete /></icon-base>
      </div>
      <div class="club-card-img">
        <img :src="bookImg" alt="club image" />
      </div>
      <div class="club-card-text">
        <h6>{{ bookTitle }}</h6>
        <p>{{ bookAuthor }} | {{ bookTranslators }}</p>
        <p>{{ bookPublisher }} | {{ bookDate }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import IconDelete from "@/components/icons/IconDelete.vue";

export default {
  name: "ClubCreateBookListSelected",
  components: {
    IconDelete,
  },
  props: {
    selectedBook: {
      type: [String, Object],
    },
  },
  methods: {
    deleteBook: function () {
      this.$emit("delete-book", this.selectedBook);
    },
  },
  computed: {
    bookId: function () {
      return this.selectedBook.id;
    },
    bookImg: function () {
      return this.selectedBook.thumbnail;
    },
    bookTitle: function () {
      return this.selectedBook.title;
    },
    bookAuthor: function () {
      return this.selectedBook.author;
    },
    bookTranslators: function () {
      return this.selectedBook.translators;
    },
    bookPublisher: function () {
      return this.selectedBook.publisher;
    },
    bookDate: function () {
      return this.selectedBook.date;
    },
  },
};
</script>

<style lang="scss" scoped>
.club-card {
  position: relative;
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 80%;
  height: 7rem;

  background-color: var(--very-light-grey);
  border-radius: 1rem;
  margin-bottom: 1rem;

  .delete-icon {
    position: absolute;
    bottom: 0.6rem;
    right: 0.6rem;
    z-index: 10;
  }

  &-img {
    width: 5rem;
    height: 100%;

    img {
      width: 5rem;
      height: 7rem;
      border-radius: 1em 0 0 1em;
      box-shadow: 0 3% 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
    }
  }
  &-text {
    width: 100%;
    text-align: left;
    margin-left: 1.2rem;
    position: relative;
    overflow: hidden;

    h6 {
      white-space: nowrap;
      overflow: hidden;
      text-align: left;
      margin: 0;
      margin-top: 1rem;
    }
    p {
      text-align: left;
      margin: 0;
      margin-top: 0.5rem;
    }
  }
}
</style>
