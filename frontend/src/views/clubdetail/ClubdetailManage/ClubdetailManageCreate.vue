<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">읽을 책 추가</h4>
          <ClubCreateBookBar @input-keyword="onInputBookname" />
          <ClubCreateBookList :books="books" />
          <button
            type="button"
            class="button-2 m-top-10"
            :disabled="!isSubmit"
            @click="clickCreate"
          >
            추가하기
          </button>
        </div>
      </div>
    </div>
    <Navbar :selected="'manage'" class="footer" />
  </div>
</template>

<script>
import "./ClubdetailManageCreate.scss";
import Navbar from "@/views/clubdetail/Navbar.vue";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import ClubCreateBookBar from "@/views/clubs/ClubCreateBookBar.vue";
import ClubCreateBookList from "@/views/clubs/ClubCreateBookList.vue";

export default {
  name: "ClubdetailManageCreate",
  components: {
    Navbar,
    TopHeader,
    ClubCreateBookBar,
    ClubCreateBookList,
  },
  data() {
    return {
      bookName: "",
      isSubmit: true,
    };
  },
  computed: {
    books: function () {
      return this.$store.state.examples.bookclubs[0].bookList;
    },
  },
  methods: {
    clickCreate() {
      alert("읽을 책이 추가되었습니다");
    },
    onInputBookname: function (bookName) {
      this.bookName = bookName;
    },
  },
};
</script>

<style scoped></style>
