<template>
  <div class="">
    <div class="club-card" @click="deleteBook">
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
export default {
  name: "ClubdetailBookAddListSelected",
  props: {
    selectedBook: {
      type: [String, Object],
    },
  },
  methods: {
    deleteBook: function () {
      console.log(this.selectedBook);
      this.$emit("delete-book", this.selectedBook);
    },
  },
  computed: {
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
  display: flex;
  justify-content: center;
  align-items: flex-start;
  margin: 1rem auto 0;
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
