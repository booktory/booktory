<template>
  <div class="">
    <div class="club-card" @click="selectBook">
      <div class="club-card-img">
        <img :src="bookImg" alt="club image" />
      </div>
      <div class="club-card-text">
        <h6>{{ bookTitle }}</h6>
        <p class="font-body-5">{{ bookAuthor }} | {{ bookTranslators }}</p>
        <p class="font-body-5">{{ bookPublisher }} | {{ bookDate }}</p>
      </div>
    </div>
    {{ selectedBook }}
  </div>
</template>

<script>
export default {
  name: "ClubdetailBookAddListItem",
  props: {
    book: {
      type: Object,
    },
    selectedBook: {
      type: Object,
    },
  },
  methods: {
    selectBook: function () {
      this.$emit("select-book", this.book);
    },
  },
  computed: {
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
  justify-content: center;
  align-items: flex-start;
  margin: 0 auto;
  width: 90%;
  height: 7rem;

  background-color: var(--very-light-grey);
  border-radius: 1rem;
  margin-bottom: 1rem;

  &-img {
    width: 30%;
    height: 100%;
    text-align: center;
    // background-color: var(--beige);

    img {
      width: 5rem;
      height: 7rem;
      border-radius: 1em;
      box-shadow: 0 3% 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
    }
  }
  &-text {
    width: 70%;
    text-align: left;
    margin-left: 1rem;

    h6 {
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
