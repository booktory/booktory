<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <div class="main-head">
            <h4>읽는 중</h4>
            <button @click="$router.push({ name: 'ClubdetailBookAdd' })">책 추가하기</button>
          </div>
          <!-- 읽는 중 -->
          <div class="now-reading">
            <div class="now-reading-card m-top-1">
              <div class="now-reading-card-left">
                <img :src="nowBook.thumbnail" alt="" />
              </div>
              <div class="now-reading-card-right">
                <div class="now-reading-card-head">
                  <h5>{{ nowBook.title }}</h5>
                </div>
                <div class="now-reading-card-body font-body-4">
                  {{ nowBook.author }} | {{ nowBook.translators }}
                </div>
                <div class="now-reading-card-footer font-body-4">
                  {{ nowBook.publisher }} | {{ nowBook.date }}
                </div>
              </div>
            </div>
          </div>
          <!-- 앞으로 읽을 책 -->
          <div class="next-reeding">
            <h4>앞으로 읽을 책</h4>
            <div class="next-reading-card m-top-1">
              <div class="next-reading-card-left">
                <img :src="nowBook.thumbnail" alt="" />
              </div>
              <div class="next-reading-card-right">
                <div class="next-reading-card-head">
                  <h5>{{ nextBook.title }}</h5>
                </div>
                <div class="next-reading-card-body font-body-4">
                  {{ nextBook.author }} | {{ nextBook.translators }}
                </div>
                <div class="next-reading-card-footer font-body-4">
                  {{ nextBook.publisher }} | {{ nextBook.date }}
                </div>
              </div>
            </div>
          </div>

          <!-- 읽었어요 -->
          <div class="pre-meeting">
            <h4>읽었어요</h4>
            <div class="pre-reading-card m-top-1">
              <div class="pre-reading-card-left">
                <img :src="nowBook.thumbnail" alt="" />
              </div>
              <div class="pre-reading-card-right">
                <div class="pre-reading-card-head">
                  <h5>{{ preBook.title }}</h5>
                </div>
                <div class="pre-reading-card-body font-body-3">
                  {{ preBook.author }} | {{ preBook.translators }}
                </div>
                <div class="pre-reading-card-footer font-body-4">
                  {{ preBook.publisher }} | {{ preBook.date }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailBook",
  components: {
    Navbar,
  },
  computed: {
    nowBook: function () {
      return this.$store.state.examples.bookclubs[0].bookList[0];
    },
    nextBook: function () {
      return this.$store.state.examples.bookclubs[0].bookList[1];
    },
    preBook: function () {
      return this.$store.state.examples.bookclubs[0].bookList[2];
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
