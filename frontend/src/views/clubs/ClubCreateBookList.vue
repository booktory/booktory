<template>
  <div>
    <div v-if="bookList && bookList.length != 0">
      <ClubCreateBookListItem
        v-for="(book, idx) in bookList"
        :key="idx"
        :book="book"
        @select-book="onSelectBook"
      />
    </div>
    <div class="empty" v-else>
      <span class="font-body-4">검색된 책이 없습니다</span>
    </div>
    <p class="label font-body-4">선택한 책 목록</p>
    <div v-if="selectedBooks && selectedBooks.length != 0">
      <ClubCreateBookListSelected
        v-for="(selectedBook, idx) in selectedBooks"
        :key="'selected' + idx"
        :selectedBook="selectedBook"
        @delete-book="onDeleteBook"
      />
    </div>
    <div class="empty" v-else>
      <span class="font-body-4">선택한 책이 없습니다</span>
    </div>
  </div>
</template>

<script>
import ClubCreateBookListItem from "@/views/clubs/ClubCreateBookListItem.vue";
import ClubCreateBookListSelected from "@/views/clubs/ClubCreateBookListSelected.vue";
import { mapState } from "vuex";

export default {
  name: "ClubCreateBookList",
  components: {
    ClubCreateBookListItem,
    ClubCreateBookListSelected,
  },
  computed: {
    ...mapState("searchStore", ["bookList"]),
  },
  props: {
    selectedBooks: {
      type: Array,
    },
  },
  methods: {
    onSelectBook: function (book) {
      if (this.selectedBooks.indexOf(book) < 0) {
        this.selectedBooks.push(book);
      }
    },
    onDeleteBook: function (book) {
      const index = this.selectedBooks.indexOf(book);
      this.selectedBooks.splice(index, 1);
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
  text-align: left;
  margin: 3.5rem 0 1.5rem 4.5rem;
}
.empty {
  text-align: left;
  margin: 2.5rem 0 0 5rem;
  color: var(--grey);
}
</style>
