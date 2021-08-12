<template>
  <div class="container">
    <TopHeader />
    <h4 class="title">내가 읽은 책</h4>
    <BookList v-if="bookList" :bookList="bookList" />
    <Navbar :selected="'mybook'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import BookList from "@/views/mybook/BookList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "MyBook",
  components: {
    TopHeader,
    Navbar,
    BookList,
  },
  computed: {
    ...mapState("mybookStore", ["bookList"]),
  },
  methods: {
    ...mapActions("mybookStore", ["findBookList"]),
  },
  created() {
    this.findBookList();
  },
};
</script>

<style scoped></style>
