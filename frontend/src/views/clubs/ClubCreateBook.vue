<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><arrow-left /></icon-base>
      </div>
    </div>
    <h4>읽을 책 추가</h4>
    <ClubCreateBookBar @input-keyword="onInputBookname" />
    <ClubCreateBookList :books="books" />
  </div>
</template>

<script>
import ClubCreateBookBar from "@/views/clubs/ClubCreateBookBar.vue";
import ClubCreateBookList from "@/views/clubs/ClubCreateBookList.vue";
import IconBase from "../../components/icons/IconBase.vue";
import ArrowLeft from "../../components/icons/ArrowLeft.vue";

export default {
  name: "ClubCreateBook",
  components: {
    ClubCreateBookBar,
    ClubCreateBookList,
    IconBase,
    ArrowLeft,
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

<style></style>
