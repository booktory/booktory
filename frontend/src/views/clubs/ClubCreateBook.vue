<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">읽을 책 추가</h4>
    <ClubCreateBookBar />
    <ClubCreateBookList :selectedBooks="selectedBooks" />
    <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickCreate">
      클럽 만들기
    </button>
    <Navbar :selected="'home'" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import ClubCreateBookBar from "@/views/clubs/ClubCreateBookBar.vue";
import ClubCreateBookList from "@/views/clubs/ClubCreateBookList.vue";
import { mapActions } from "vuex";

export default {
  name: "ClubCreateBook",
  components: {
    TopHeader,
    Navbar,
    ClubCreateBookBar,
    ClubCreateBookList,
  },
  data: function () {
    return {
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
    ...mapActions("clubStore", ["createClub"]),
    ...mapActions("searchStore", ["initBookList"]),
    // 클럽 만들기 버튼 클릭
    clickCreate() {
      if (this.isSubmit) {
        let books = [];
        for (var i = 0; i < this.selectedBooks.length; i++) {
          books.push(this.selectedBooks[i].id);
        }
        this.createClub(books);
      }
    },
    // 선택한 책 있으면 클럽 만들기 버튼 활성화
    checkSelectedBooks() {
      this.isSubmit = this.selectedBooks.length > 0;
    },
  },
  created() {
    this.initBookList();
  },
};
</script>

<style scoped></style>
