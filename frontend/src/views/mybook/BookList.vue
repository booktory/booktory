<template>
  <div>
    <p class="label font-body-3">읽은 책 ({{ bookList.length }})</p>
    <div class="book-list-wrapper">
      <BookItem v-for="(book, index) in bookList" :key="index" :index="index" :book="book" />
    </div>
  </div>
</template>

<script>
import BookItem from "@/views/mybook/BookItem.vue";

export default {
  name: "BookList",
  components: {
    BookItem,
  },
  props: {
    bookList: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  text-align: left;
  width: 30rem;
  margin: 2rem auto 0;
}
.book-list-wrapper {
  width: 28rem;
  display: grid;
  grid-template-columns: repeat(4, auto);
  gap: 0.5rem;
  margin: 0 auto 5rem;
}
</style>
