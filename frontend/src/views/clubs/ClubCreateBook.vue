<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">읽을 책 추가</h4>
    <ClubCreateBookBar @input-keyword="onInputBookname" />
    <ClubCreateBookList :selectedBooks="selectedBooks" />
    <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickCreate">
      클럽 만들기
    </button>
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import ClubCreateBookBar from "@/views/clubs/ClubCreateBookBar.vue";
import ClubCreateBookList from "@/views/clubs/ClubCreateBookList.vue";

export default {
  name: "ClubCreateBook",
  components: {
    TopHeader,
    ClubCreateBookBar,
    ClubCreateBookList,
  },
  computed: {
    books: function () {
      return this.$store.state.examples.bookclubs[0].bookList;
    },
  },
  data: function () {
    return {
      bookName: "",
      clubInfo: {},
      selectedBooks: [],
      isSubmit: false,
    };
  },
  watch: {
    selectedBooks: {
      handler() {
        this.checkSelectedBooks();
      },
    },
  },
  methods: {
    // 클럽 만들기 버튼 클릭
    clickCreate() {},
    // 선택한 책 있으면 클럽 만들기 버튼 활성화
    checkSelectedBooks() {
      this.isSubmit = this.selectedBooks.length > 0;
    },
    onInputBookname: function (bookName) {
      this.bookName = bookName;
    },
  },
};
</script>

<style scoped></style>
