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
          <div class="font-body-2 m-top-1">
            {{ bookclub.info }}
          </div>

          <div class="m-top-1">
            <span class="font-body-4">{{ bookclub.leader_id }}</span> |
            <span class="font-body-4">{{ bookclub.max_member }}명</span>
          </div>

          <div class="meeting m-top-2">
            <button type="button" class="metting-button">
              <div class="metting-button-head">
                <div class="metting-button-head-icon">
                  <icon-base><icon-video /></icon-base>
                </div>
                <div class="metting-button-head-date">
                  <p>8월 15일</p>
                  <p>21:00 예정</p>
                </div>
              </div>
              <div class="metting-button-body">모임 참여하기</div>
            </button>
            <button
              type="button"
              class="metting-button"
              @click="$router.push({ name: 'ClubdetailMetting' })"
            >
              <div class="metting-button-head">
                <div class="metting-button-head-icon">
                  <icon-base><icon-bookmark /></icon-base>
                </div>
              </div>
              <div class="metting-button-body">일정 확인하기</div>
            </button>
          </div>

          <div class="rules m-top-2">
            <h5>운영규칙</h5>
            <p class="font-body-2 m-top-1">• 한 달에 {{ bookclub.volum_rule }}권을 읽어요</p>
            <p class="font-body-2 m-top-1">• {{ bookclub.week_rule }}주에 1번 만나요</p>
            <p class="font-body-2 m-top-1">• {{ bookclub.free_rule }}</p>
          </div>

          <div class="books m-top-2">
            <div class="books-head">
              <h5>클럽 서재</h5>
              <button @click="$router.push({ name: 'ClubdetailBook' })">더 보기</button>
            </div>
            <div class="books-list m-top-1">
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
import IconVideo from "@/components/icons/IconVideo.vue";
import IconBookmark from "@/components/icons/IconBookmark.vue";

export default {
  name: "ClubdetailHome",
  components: {
    Navbar,
    IconVideo,
    IconBookmark,
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
        padding: 10% 10% 20%;

        .tag {
          display: inline-block;
          margin: 0 auto;
          padding: 0.5% 1.6%;
          border: 0;
          border-radius: 1em;
          color: white;
          background-color: var(--light-orange);
          margin: 0.5% 0.4%;
        }

        .meeting {
          display: flex;
          justify-content: center;
          align-items: center;

          .metting-button {
            width: 15rem;
            height: 10rem;
            margin: 2%;

            border-radius: 20px;
            border: 0;

            color: white;
            background-color: #bdbcdb;
            box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.3);

            &-head {
              display: flex;
              justify-content: space-between;
              align-items: center;

              &-icon {
                display: inline-block;
                color: var(--white);
                width: 3.5em;
                height: 3.5em;
                margin-left: 1.5rem;
              }
              &-date {
                display: inline-block;
                margin-right: 1.5rem;
                text-align: right;
              }
            }
            &-body {
              margin-top: 1rem;
              text-align: center;
            }
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
.m-top-1 {
  margin-top: 1rem;
}

.m-top-2 {
  margin-top: 2rem;
}

.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
