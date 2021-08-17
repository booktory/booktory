<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">읽을 책 추가</h4>
          <ClubdetailBookAddBar />
          <ClubdetailBookAddList :selectedBooks="selectedBooks" />
          <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickAdd">
            추가하기
          </button>
        </div>
      </div>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import ClubdetailBookAddBar from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddBar.vue";
import ClubdetailBookAddList from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubdetailBookAdd",
  components: {
    TopHeader,
    Navbar,
    ClubdetailBookAddBar,
    ClubdetailBookAddList,
  },
  computed: {
    ...mapState("clubStore", ["clubId", "clubImage"]),
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
    ...mapActions("bookclubStore", ["createBook"]),
    ...mapActions("searchStore", ["initBookList"]),
    // 추가하기 버튼 클릭
    clickAdd() {
      if (this.isSubmit) {
        let books = [];
        for (var i = 0; i < this.selectedBooks.length; i++) {
          books.push(this.selectedBooks[i].id);
        }
        let bookclubData = {
          books: books,
          clubId: this.clubId,
        };
        this.createBook(bookclubData);
      }
    },
    // 선택한 책 있으면 추가하기 버튼 활성화
    checkSelectedBooks() {
      this.isSubmit = this.selectedBooks.length > 0;
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  created() {
    this.initBookList();
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailBookAdd.scss";
</style>
