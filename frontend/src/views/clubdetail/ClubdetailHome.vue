<template>
  <div class="container">
    <!-- 배경화면 -->
    <!-- <img src="https://picsum.photos/200/300" alt="img" /> -->
    <!-- 카드 -->
    <div class="card">
      <div class="head">
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
        <h5>클럽 서재</h5>
        <span v-for="(book, idx) in books" :key="idx" class="book-img">
          <img :src="book.thumbnail" alt="" />
        </span>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
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

<style scoped>
.container {
  width: 100vw;
  margin-bottom: 20vh;
}
/* Tablet */
@media (min-width: 768px) {
  * {
    font-size: 2.2vw;
  }
  .container {
    width: 100vw;
  }
}
/* Desktop */
@media (min-width: 1024px) {
  * {
    font-size: 18px;
  }
  .container {
    width: 100vw;
    max-width: 768px;
  }
}

.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}

.card * {
  text-align: left;
}
.card {
  border-radius: 5em;
  padding: 5%;
  margin-top: 30%;
  bottom: 0;
  background-color: white;
}

.tag {
  margin: 0 0.2%;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 10px;
  color: white;
  background-color: #a4c0f3;
}

.meeting {
  display: flex;
  justify-content: center;
  align-items: center;
}

.metting-button {
  width: 35%;
  padding: 10% 2% 10%;
  margin: 2%;

  border-radius: 20px;
  border: 0;

  color: white;
  background-color: #bdbcdb;
}

.books img {
  width: 20%;
  margin: 0 3%;
  border-radius: 1em;
}
</style>
