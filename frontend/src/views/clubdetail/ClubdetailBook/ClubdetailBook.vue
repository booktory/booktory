<template>
  <div class="container bg-image">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 서재</h4>
          <div class="main-head">
            <h5>읽는 중</h5>
            <span
              v-if="clubInfo.isLeader"
              class="font-body-4"
              @click="$router.push({ name: 'ClubdetailBookAdd' })"
              >책 추가하기</span
            >
          </div>
          <!-- 읽는 중 -->
          <div class="now-reading">
            <div v-if="nowbookclub != null">
              <div class="reading-card m-top-1">
                <div class="reading-card-left">
                  <img :src="nowbookclub.bookThumbnail" alt="" />
                </div>
                <div class="reading-card-right">
                  <div class="reading-card-head">
                    <h5>{{ nowbookclub.bookTitle }}</h5>
                  </div>
                  <div class="reading-card-body font-body-4">
                    {{
                      nowbookclub.bookAuthor.length > 8
                        ? nowbookclub.bookAuthor.substr(0, 8) + "・・・"
                        : nowbookclub.bookAuthor
                    }}&nbsp;|&nbsp;{{ nowbookclub.bookTranslators }}
                  </div>
                  <div class="reading-card-footer font-body-4">
                    {{
                      nowbookclub.bookPublisher.length > 8
                        ? nowbookclub.bookPublisher.substr(0, 8) + "・・・"
                        : nowbookclub.bookPublisher
                    }}&nbsp;|&nbsp;{{ nowbookclub.bookDate }}
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 reading-card-no">읽고 있는 책이 없습니다</div>
          </div>
          <!-- 앞으로 읽을 책 -->
          <div class="reading">
            <h5>앞으로 읽을 책</h5>
            <div v-if="nextbookclubList.length > 0">
              <div v-for="(book, idx) in nextbookclubList" :key="idx" class="reading-card m-top-1">
                <div class="reading-card-left">
                  <img :src="book.bookThumbnail" alt="" />
                </div>
                <div class="reading-card-right">
                  <div class="reading-card-head">
                    <h5>{{ book.bookTitle }}</h5>
                  </div>
                  <div class="reading-card-body font-body-4">
                    {{
                      book.bookAuthor.length > 8
                        ? book.bookAuthor.substr(0, 8) + "・・・"
                        : book.bookAuthor
                    }}&nbsp;|&nbsp;{{ book.bookTranslators }}
                  </div>
                  <div class="reading-card-footer font-body-4">
                    {{
                      book.bookPublisher.length > 8
                        ? book.bookPublisher.substr(0, 8) + "・・・"
                        : book.bookPublisher
                    }}&nbsp;|&nbsp;{{ book.bookDate }}
                  </div>
                  <div class="icon delete-icon" @click="clickDeleteBook(book.id)">
                    <icon-base :iconColor="'var(--light-brown)'"><icon-delete /></icon-base>
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 reading-card-no">읽을 책이 없습니다</div>
          </div>

          <!-- 읽었어요 -->
          <div class="reading">
            <h5>읽었어요</h5>
            <div v-if="prebookclubList.length > 0">
              <div v-for="(book, idx) in prebookclubList" :key="idx" class="reading-card m-top-1">
                <div class="reading-card-left">
                  <img :src="book.bookThumbnail" alt="" />
                </div>
                <div class="reading-card-right">
                  <span class="font-body-5 reading-card-date">
                    {{ $moment(book.endDateTime).format("YYYY년 M월 D일 읽음") }}
                  </span>
                  <div class="reading-card-head">
                    <h5>{{ book.bookTitle }}</h5>
                  </div>
                  <div class="reading-card-body font-body-4">
                    {{
                      book.bookAuthor.length > 8
                        ? book.bookAuthor.substr(0, 8) + "・・・"
                        : book.bookAuthor
                    }}&nbsp;|&nbsp;{{ book.bookTranslators }}
                  </div>
                  <div class="reading-card-footer font-body-4">
                    {{
                      book.bookPublisher.length > 8
                        ? book.bookPublisher.substr(0, 8) + "・・・"
                        : book.bookPublisher
                    }}&nbsp;|&nbsp;{{ book.bookDate }}
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="font-body-4 reading-card-no">아직 읽은 책이 없습니다</div>
          </div>
        </div>
      </div>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import IconDelete from "@/components/icons/IconDelete.vue";
import Swal from "sweetalert2";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubdetailBook",
  components: {
    TopHeader,
    Navbar,
    IconDelete,
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "clubId"]),
    ...mapState("bookclubStore", [
      "bookclubList",
      "nowbookclub",
      "nextbookclubList",
      "prebookclubList",
    ]),
  },
  methods: {
    ...mapActions("bookclubStore", ["getBookClubList", "deleteBook"]),
    clickDeleteBook(id) {
      Swal.fire({
        showCancelButton: true,
        title: "읽을 책을 삭제 하시겠습니까?",
        confirmButtonText: "네, 삭제할래요",
        cancelButtonText: "아니요",
      }).then((result) => {
        if (result.isConfirmed) {
          const params = {
            bookclubId: id,
            clubId: this.clubId,
          };
          this.deleteBook(params);
        }
      });
    },
  },
  created() {
    this.getBookClubList(this.clubId);
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailBook.scss";
.m-top-1 {
  margin-top: 1rem;
}
.m-top-2 {
  margin-top: 2rem;
}
</style>
