<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">내가 읽은 책</h4>
      <BookList v-if="bookList && bookList.length > 0" :bookList="bookList" />
      <div v-else>
        <img class="logo" src="@/assets/images/book_page.png" alt="내가 읽은 책" />
        <p class="label font-body-2">아직 읽은 책이 없어요!</p>
        <p class="content font-body-3">모임에 참여해서 서재를 채워보세요:)</p>
      </div>
      <Navbar :selected="'mybook'" class="footer" />
    </div>
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

<style scoped>
.logo {
  opacity: 40%;
  width: 60%;
  margin: 2rem 0 -2rem;
  object-fit: contain;
}
.label {
  font-weight: bold;
  font-style: italic;
  margin: 3rem 0 0;
}
.content {
  margin: 1rem 0;
}
</style>
