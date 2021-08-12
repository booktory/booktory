<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <h4 class="title">읽을 책 추가</h4>
          <ClubdetailBookAddBar @input-keyword="onInputBookname" class="m-top-2" />
          <ClubdetailBookAddList :books="books" class="m-top-2" />
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";
import ClubdetailBookAddBar from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddBar.vue";
import ClubdetailBookAddList from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddList.vue";

export default {
  name: "ClubdetailBookAdd",
  components: {
    Navbar,
    ClubdetailBookAddBar,
    ClubdetailBookAddList,
  },
  data: function () {
    return {
      bookName: "",
    };
  },
  methods: {
    onInputBookname: function (bookName) {
      console.log(bookName);
      this.bookName = bookName;
    },
  },
  computed: {
    books: function () {
      return this.$store.state.examples.bookclubs[0].bookList;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailBookAdd.scss";
.m-top-1 {
  margin-top: 1rem;
}

.m-top-2 {
  margin-top: 2rem;
}
</style>
