<template>
  <div>
    <div class="book-list-wrapper" v-if="bookList && bookList.length != 0">
      <ClubdetailBookAddListItem
        v-for="(book, idx) in bookList"
        :key="idx"
        :book="book"
        @select-book="onSelectBook"
      />
    </div>
    <div class="empty" v-else>
      <span class="font-body-4">검색된 책이 없습니다</span>
    </div>
    <div class="label-div">
      <p class="label font-body-4">선택한 책 목록</p>
    </div>
    <div v-if="selectedBooks && selectedBooks.length != 0">
      <ClubdetailBookAddListSelected
        v-for="(selectedBook, idx) in selectedBooks"
        :key="'selected' + idx"
        :selectedBook="selectedBook"
        @delete-book="onDeleteBook"
      />
    </div>
    <div v-else class="empty">
      <span class="font-body-4">선택한 책이 없습니다</span>
    </div>
  </div>
</template>

<script>
import ClubdetailBookAddListItem from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddListItem.vue";
import ClubdetailBookAddListSelected from "@/views/clubdetail/ClubdetailBook/ClubdetailBookAddListSelected.vue";
import Swal from "sweetalert2";
import { mapState, mapActions } from "vuex";

export default {
  name: "ClubdetailBookAddList",
  components: {
    ClubdetailBookAddListItem,
    ClubdetailBookAddListSelected,
  },
  computed: {
    ...mapState("searchStore", ["bookList"]),
    ...mapState("clubStore", ["isRegisterBook"]),
  },
  props: {
    selectedBooks: {
      type: Array,
    },
  },
  methods: {
    ...mapActions("clubStore", ["checkRegisterBook"]),
    onSelectBook: async function (book) {
      await this.checkRegisterBook(book.id);
      if (this.selectedBooks.indexOf(book) < 0) {
        if (this.isRegisterBook) {
          Swal.fire({
            icon: "warning",
            title: "클럽에 이미 등록된 책입니다",
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        } else {
          this.selectedBooks.push(book);
        }
      } else {
        Swal.fire({
          icon: "warning",
          title: "이미 선택된 책입니다",
          showConfirmButton: false,
          timer: 1500,
          timerProgressBar: false,
        });
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
.label-div {
  width: 30rem;
  margin: 2rem auto 2rem;
}
.book-list-wrapper {
  max-height: 45rem;
  overflow: scroll;
}
.label {
  font-weight: bold;
  text-align: left;
  margin: 3.5rem 0 1.5rem 1rem;
}
.empty {
  text-align: left;
  margin: 0 auto;
  width: 30rem;
  color: var(--grey);
}
.empty span {
  margin-left: 1rem;
}
</style>
