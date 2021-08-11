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
// import "./ClubdetailHome.scss";
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

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.clubdetail-container {
  width: 100vw;

  .bg-image {
    height: 40vh;
    background-image: url("../images/club-backgroud.png");
    background-size: cover;
    position: relative;

    .icon {
      right: 4vw;
      top: 2vh;
      position: absolute;
    }

    .card {
      width: 100%;
      height: 70vh;
      top: 20vh;

      background-color: var(--beige);
      border-radius: 20% 20% 0 0 / 10% 10% 0 0;
      position: absolute;

      .main * {
        text-align: left;
      }

      // 여기서부터 메인 시작!
      .main {
        text-align: left;
        padding: 10%;

        .tag {
          margin: 0 0.2%;
          padding: 1.6% 2%;
          border: 0;
          border-radius: 20%;
          color: white;
          background-color: #a4c0f3;
        }

        .meeting {
          display: flex;
          justify-content: center;
          align-items: center;

          .metting-button {
            width: 35%;
            padding: 10% 2% 10%;
            margin: 2%;

            border-radius: 20px;
            border: 0;

            color: white;
            background-color: #bdbcdb;
          }
        }

        .books {
          .books-head {
            display: flex;
            justify-content: space-between;
            align-items: center;
          }
          .books-list {
            display: flex;
            justify-content: space-around;
            align-items: center;

            .book-thumbnail {
              width: 8em;
              height: 12em;
              margin: 2%;
              border-radius: 1em;
              box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
            }
          }
        }
      }
    }
  }
}

.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
