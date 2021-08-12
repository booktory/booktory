<template>
  <div class="">
    <ClubdetailBookAddListItem
      v-for="(book, idx) in books"
      :key="idx"
      :book="book"
      @select-book="onSelectBook"
    />
    <label>선택한 책 목록</label>

    <ClubdetailBookAddListSelected
      v-for="(selectedBook, idx) in selectedBooks"
      :key="'selected' + idx"
      :selectedBook="selectedBook"
      @delete-book="onDeleteBook"
    />
  </div>
</template>

<script>
import ClubdetailBookAddListItem from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddListItem.vue";
import ClubdetailBookAddListSelected from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddListSelected.vue";

export default {
  name: "ClubdetailBookAddList",
  components: {
    ClubdetailBookAddListItem,
    ClubdetailBookAddListSelected,
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

<style scoped>
label {
  text-align: left;
  display: block;
  margin: 5.5% 0 1.5% 14%;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--grey);
}

.m-top-1 {
  margin-top: 1rem;
}

.m-top-2 {
  margin-top: 2rem;
}
</style>
