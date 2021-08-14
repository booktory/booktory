<template>
  <div class="">
    <div class="club-card" @click="selectBook">
      <div class="club-card-img">
        <img :src="bookImg" alt="책이미지" />
      </div>
      <div class="club-card-text">
        <h6>{{ bookTitle }}</h6>
        <p>{{ bookAuthor }}&nbsp;|&nbsp;{{ bookTranslators }}</p>
        <p>{{ bookPublisher }}&nbsp;|&nbsp;{{ bookDate }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubCreateBookListItem",
  props: {
    book: {
      type: Object,
    },
  },
  methods: {
    selectBook: function () {
      this.$emit("select-book", this.book);
    },
  },
  computed: {
    bookId: function () {
      return this.book.id;
    },
    bookImg: function () {
      return this.book.thumbnail;
    },
    bookTitle: function () {
      return this.book.title;
    },
    bookAuthor: function () {
      return this.book.author;
    },
    bookTranslators: function () {
      return this.book.translators;
    },
    bookPublisher: function () {
      return this.book.publisher;
    },
    bookDate: function () {
      return this.book.date;
    },
  },
};
</script>

<style lang="scss" scoped>
.club-card {
  display: flex;
  width: 80%;
  height: 7rem;
  margin: 0 auto;
  background-color: var(--very-light-grey);
  border-radius: 1rem;
  margin-bottom: 1rem;

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
