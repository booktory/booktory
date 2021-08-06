<template>
  <div class="">
    <div class="club-card" @click="deleteBook">
      <div class="club-card-img">
        <!-- <img :src="clubImg" alt="club image" /> -->
        이미지
      </div>
      <div class="club-card-text">
        <h6>{{ bookTitle }}</h6>
        <p>{{ bookAuthor }} | {{ bookTranslators }}</p>
        <p>{{ bookPublisher }} | {{ bookDate }}</p>
      </div>
    </div>
    <hr />
  </div>
</template>

<script>
export default {
  name: "ClubCreateBookListSelected",
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

<style>
label {
  text-align: left;
  display: block;
  margin: 5.5% 0 1.5% 14%;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--grey);
}

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
