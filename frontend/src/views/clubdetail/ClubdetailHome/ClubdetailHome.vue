<template>
  <div v-if="clubInfo" class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>

      <div class="card">
        <div class="main">
          <h3>
            {{ clubInfo.name }}
          </h3>
          <div class="genre-keyword">
            <span
              class="tag font-body-4"
              v-for="(genre, idx) in clubInfo.genres"
              :key="idx"
              :value="genre"
            >
              {{ genreList[genre - 1] }}
            </span>
          </div>
          <div class="font-body-2 m-top-1">
            {{ clubInfo.info }}
          </div>

          <div class="m-top-1">
            <span class="font-body-4"><b>클럽장</b> {{ clubInfo.leaderName }}</span
            >&nbsp;|&nbsp;<span class="font-body-4"><b>참가자</b> {{ clubInfo.nowMember }}명</span>
          </div>

          <div class="meeting m-top-2">
            <button type="button" class="meeting-button">
              <div class="meeting-button-head">
                <div class="meeting-button-head-icon">
                  <icon-base><icon-video /></icon-base>
                </div>

                <div v-if="clubInfo.title != null" class="meeting-button-head-date">
                  <p>
                    {{ new Date(clubInfo.endDateTime).getMonth() + 1 }}월
                    {{ new Date(clubInfo.endDateTime).getDate() }}일
                  </p>
                  <p>
                    {{ new Date(clubInfo.endDateTime).getHours() }}:{{
                      new Date(clubInfo.endDateTime).getMinutes()
                    }}
                    예정
                  </p>
                </div>
              </div>
              <div v-if="clubInfo.title != null" class="meeting-button-body">모임 참여하기</div>
              <div v-else class="meeting-button-body">모임 개설하기</div>
            </button>
            <button
              type="button"
              class="meeting-button"
              @click="$router.push({ name: 'ClubdetailMetting' })"
            >
              <div class="meeting-button-head">
                <div class="meeting-button-head-icon">
                  <icon-base><icon-bookmark /></icon-base>
                </div>
              </div>
              <div class="meeting-button-body">일정 확인하기</div>
            </button>
          </div>

          <div class="rules m-top-2">
            <h5>운영규칙</h5>
            <p class="font-body-2 m-top-1">• 한 달에 {{ clubInfo.volumeRule }}권을 읽어요</p>
            <p class="font-body-2 m-top-1">• {{ clubInfo.weekRule }}주에 1번 만나요</p>
            <p v-if="clubInfo.freeRule" class="font-body-2 m-top-1">• {{ clubInfo.freeRule }}</p>
          </div>

          <div class="books m-top-2">
            <div class="books-head">
              <h5>클럽 서재</h5>
              <div class="bookcard-info-more">
                <span class="font-body-4" @click="$router.push({ name: 'ClubdetailBook' })"
                  >책 목록 더보기</span
                >
              </div>
            </div>
            <div class="books-list m-top-1">
              <span v-for="(book, idx) in bookclubList" :key="idx">
                <div v-if="idx < 4">
                  <img :src="book.bookThumbnail" alt="" class="book-thumbnail" />
                </div>
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
import { mapActions, mapState } from "vuex";
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
    ...mapState("clubStore", ["clubInfo", "clubId"]),
    ...mapState("searchStore", ["genreList"]),
    ...mapState("bookclubStore", ["bookclubList"]),
  },
  data() {
    return {
      // endDate: this.clubInfo.endDateTime,
      // clubId: this.$route.param.clubId,
    };
  },
  created() {
    this.getBookclubList(this.clubId);
  },
  methods: {
    ...mapActions("bookclubStore", ["getBookclubList"]),
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
          padding: 0.2rem 0.6rem;
          margin: 0.5rem 0 0 0.4rem;
          border: 0;
          border-radius: 1em;
          color: white;
          background-color: var(--light-orange);
        }

        .meeting {
          display: flex;
          justify-content: center;
          align-items: center;

          .meeting-button {
            width: 15rem;
            height: 10rem;
            margin: 2%;

            border-radius: 20px;
            border: 0;

            color: white;
            background-color: var(--very-light-brown);
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
