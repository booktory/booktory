<template>
  <div class="">
    <div class="club-card" @click="selectBook">
      <div class="club-card-img">
        <!-- <img :src="bookImg" alt="club image" /> -->
        이미지
      </div>
      <div class="club-card-text">
        <h6>{{ bookTitle }}</h6>
        <p>{{ bookAuthor }} | {{ bookTranslators }}</p>
        <p>{{ bookPublisher }} | {{ bookDate }}</p>
      </div>
    </div>
    <hr />
    {{ selectedBook }}
  </div>
</template>

<script>
export default {
  name: "ClubCreateBookListItem",
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

<style>
.club-card {
  width: 80%;
  height: 15%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
  border-radius: 10px;
  background-color: #f1ece5;
}

img {
  object-fit: cover;
}

.club-card-text * {
  text-align: left;
}
</style>
