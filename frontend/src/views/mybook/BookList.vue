<template>
  <div>
    <p class="label font-body-3">읽은 책 ({{ bookListSize }})</p>
    <div class="book-list-wrapper">
      <BookItem
        v-for="(item, index) in bookList"
        :key="item.id"
        :index="index"
        :id="item.id"
        :thumbnail="item.thumbnail"
        :title="item.title"
      />
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
  data() {
    return {
      bookListSize: this.bookList.length,
    };
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  text-align: left;
  margin: 5% 0 1.5% 12%;
}
.book-list-wrapper {
  width: auto;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 2%;
  margin: 0 9% 20rem;
}
</style>
