<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>

      <div class="card">
        <div class="main">
          <h3>
            {{ bookclub.name }}
          </h3>
          <span
            class="genre-keyword"
            v-for="(genre, idx) in bookclub.genres"
            :key="idx"
            :value="genre"
          >
            <button class="tag">{{ genre.genreName }}</button>
          </span>
          <div class="font-body-2">
            {{ bookclub.info }}
          </div>

          <div>
            <span class="font-body-4">{{ bookclub.leader_id }}</span> |
            <span class="font-body-4">{{ bookclub.max_member }}명</span>
          </div>

          <div class="meeting">
            <button type="button" class="metting-button">모임 참여하기</button>
            <button type="button" class="metting-button">일정 확인하기</button>
          </div>

          <div class="rules">
            <h5>운영규칙</h5>
            <p class="font-body-2">- 한 달에 {{ bookclub.volum_rule }}권을 읽어요</p>
            <p class="font-body-2">- {{ bookclub.week_rule }}주에 1번 만나요</p>
            <p class="font-body-2">- {{ bookclub.free_rule }}</p>
          </div>

          <div class="books">
            <div class="books-head">
              <h5>클럽 서재</h5>
              <button>더 보기</button>
            </div>
            <div class="books-list">
              <span v-for="(book, idx) in books" :key="idx">
                <img :src="book.thumbnail" alt="" class="book-thumbnail" />
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import "./ClubdetailHome.scss";
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailHome",
  components: {
    Navbar,
  },
  computed: {
    bookclub: function () {
      return this.$store.state.examples.bookclubs[0].clubList[0];
    },
    books: function () {
      return this.$store.state.examples.bookclubs[0].bookList;
    },
  },
};
</script>

<style></style>
