<template>
  <div class="">
    <ClubCreateBookListItem
      v-for="(book, idx) in books"
      :key="idx"
      :book="book"
      @select-book="onSelectBook"
    />
    <label>선택한 책 목록</label>

    <ClubCreateBookListSelected
      v-for="(selectedBook, idx) in selectedBooks"
      :key="'selected' + idx"
      :selectedBook="selectedBook"
      @delete-book="onDeleteBook"
    />
  </div>
</template>

<script>
import ClubCreateBookListItem from "@/views/clubs/ClubCreateBookListItem.vue";
import ClubCreateBookListSelected from "@/views/clubs/ClubCreateBookListSelected.vue";

export default {
  name: "ClubCreateBookList",
  components: {
    ClubCreateBookListItem,
    ClubCreateBookListSelected,
  },
  props: {
    books: {
      type: Array,
    },
  },
  data: function () {
    return {
      selectedBooks: [],
    };
  },
  methods: {
    onSelectBook: function (book) {
      console.log(book);
      this.selectedBooks.push(book);
    },
    onDeleteBook: function (book) {
      const index = this.selectedBooks.indexOf(book);
      this.selectedBooks.splice(index, 1);
    },
  },
};
</script>

<style></style>
