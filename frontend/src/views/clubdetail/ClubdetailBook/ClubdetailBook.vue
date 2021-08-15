<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon icon-back" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
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
                    <h6>
                      {{
                        nowbookclub.bookTitle.length > 22
                          ? nowbookclub.bookTitle.substr(0, 22) + "・・・"
                          : nowbookclub.bookTitle
                      }}
                    </h6>
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
            <div v-else class="font-body-4 reading-card-no">예정된 모임이 없어요 :)</div>
          </div>
          <!-- 앞으로 읽을 책 -->
          <div class="reading">
            <h5>앞으로 읽을 책</h5>
            <div v-if="nextbookclubList.length > 0">
              <div v-for="(book, idx) in nextbookclubList" :key="idx" class="reading-card m-top-1">
                <div class="icon icon-delete" @click="clickDeleteBook(book.id)">
                  <icon-base :iconColor="'var(--light-brown)'"><icon-delete /></icon-base>
                </div>
                <div class="reading-card-left">
                  <img :src="book.bookThumbnail" alt="" />
                </div>
                <div class="reading-card-right">
                  <div class="reading-card-head">
                    <h6>
                      {{
                        book.bookTitle.length > 22
                          ? book.bookTitle.substr(0, 22) + "・・・"
                          : book.bookTitle
                      }}
                    </h6>
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
            <div v-else class="font-body-4 reading-card-no">
              읽을 책이 없어요. <br />책을 더 추가해 주세요 :)
            </div>
          </div>

          <!-- 읽었어요 -->
          <div class="reading">
            <h5>읽었어요</h5>
            <div v-if="prebookclubList.length > 0">
              <div v-for="(book, idx) in prebookclubList" :key="idx" class="reading-card m-top-1">
                <span class="font-body-6 reading-card-date">
                  {{
                    book.endDateTime.substr(0, 4) +
                    "." +
                    (new Date(book.endDateTime).getMonth() + 1) +
                    "." +
                    new Date(book.endDateTime).getDate() +
                    "에 읽었어요"
                  }}
                </span>
                <div class="reading-card-left">
                  <img :src="book.bookThumbnail" alt="" />
                </div>
                <div class="reading-card-right">
                  <div class="reading-card-head">
                    <h6>
                      {{
                        book.bookTitle.length > 22
                          ? book.bookTitle.substr(0, 22) + "・・・"
                          : book.bookTitle
                      }}
                    </h6>
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
            <div v-else class="font-body-4 reading-card-no">아직 읽은 책이 없어요 :)</div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";
import { mapActions, mapState } from "vuex";
import IconDelete from "@/components/icons/IconDelete.vue";

export default {
  name: "ClubdetailBook",
  components: {
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
    ...mapActions("bookclubStore", ["deleteBook"]),
    clickDeleteBook(id) {
      const params = {
        bookclubId: id,
        clubId: this.clubId,
      };
      this.deleteBook(params);
    },
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
