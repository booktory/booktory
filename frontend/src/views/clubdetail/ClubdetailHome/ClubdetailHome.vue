<template>
  <div class="container bg-image">
    <div v-if="clubInfo">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>

      <div class="card">
        <div class="main">
          <h3>
            {{ clubInfo.name }}
          </h3>
          <div class="genre-keyword">
            <span class="tag font-body-4" v-for="(genre, idx) in clubInfo.genres" :key="idx">
              {{ genreList[genre - 1].name }}
            </span>
          </div>
          <div class="font-body-3 m-top-1">
            {{ clubInfo.info }}
          </div>

          <div class="m-top-1">
            <span class="font-body-4"><b>클럽장</b> {{ clubInfo.leaderName }}</span
            >&nbsp;|&nbsp;<span class="font-body-4"><b>참가자</b> {{ clubInfo.nowMember }}명</span>
          </div>

          <div class="meeting m-top-2">
            <div v-if="clubInfo.title != null">
              <button
                :disabled="!meetingInfo.isOpen"
                @click="clickMeeting"
                type="button"
                class="meeting-button"
              >
                <div class="meeting-button-head">
                  <div class="meeting-button-head-icon">
                    <icon-base><icon-video /></icon-base>
                  </div>
                  <div
                    v-html="convertTime(clubInfo.endDateTime)"
                    class="meeting-button-head-date"
                  ></div>
                </div>
                <h5 class="meeting-button-body">모임 입장하기</h5>
              </button>
            </div>
            <div v-else>
              <button
                @click="$router.push({ name: 'ClubdetailMeetingCreate' })"
                type="button"
                class="meeting-button"
              >
                <div class="meeting-button-head">
                  <div class="meeting-button-head-icon">
                    <icon-base><icon-video /></icon-base>
                  </div>
                  <div class="meeting-button-head-date"></div>
                </div>
                <h5 class="meeting-button-body">모임 개설하기</h5>
              </button>
            </div>
            <button
              type="button"
              class="meeting-button"
              @click="$router.push({ name: 'ClubdetailMeeting' })"
            >
              <div class="meeting-button-head">
                <div class="meeting-button-head-icon">
                  <icon-base><icon-bookmark /></icon-base>
                </div>
              </div>
              <h5 class="meeting-button-body">일정 확인하기</h5>
            </button>
          </div>

          <div class="rules m-top-2">
            <h5>운영규칙</h5>
            <p class="font-body-3 m-top-1">• 한 달에 {{ clubInfo.volumeRule }}권을 읽어요</p>
            <p class="font-body-3 m-top-1">• {{ clubInfo.weekRule }}주에 1번 만나요</p>
            <p v-if="clubInfo.freeRule" class="font-body-3 m-top-1">• {{ clubInfo.freeRule }}</p>
          </div>

          <div class="books m-top-2">
            <div class="books-head">
              <h5>클럽 서재</h5>
              <span @click="$router.push({ name: 'ClubdetailBook' })" class="font-body-4"
                >책 목록 더보기</span
              >
            </div>
            <div class="books-list m-top-1">
              <span v-for="(book, idx) in bookclubList" :key="idx">
                <img
                  :src="book.bookThumbnail"
                  :title="book.bookTitle"
                  alt="책사진"
                  class="book-thumbnail"
                />
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" :selected="'home'" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Swal from "sweetalert2";
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
    ...mapState("clubStore", ["clubInfo", "meetingInfo", "clubId"]),
    ...mapState("searchStore", ["genreList"]),
    ...mapState("bookclubStore", ["bookclubList"]),
  },
  methods: {
    ...mapActions("bookclubStore", ["getBookclubList", "attendMeeting"]),
    ...mapActions("clubStore", ["findClubInfo"]),
    clickMeeting() {
      Swal.fire({
        showCancelButton: true,
        title: "모임 입장",
        text: "모임에 입장하시겠습니까?",
        confirmButtonText: "입장하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.attendMeeting(this.meetingInfo.bookclubId);
        }
      });
    },
    // 모임 시간 년월일 변환
    convertTime(data) {
      if (!data) return "예정된 모임 없음";
      let date = new Date(data);
      let year = date.getFullYear() % 100;
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let ampm = hour / 12 >= 1 ? "오후 " : "오전 ";
      let minute = date.getMinutes();
      let dateStr =
        year + "년 " + month + "월 " + day + "일<br>" + ampm + (hour % 12) + "시 " + minute + "분";
      return dateStr;
    },
  },
  data() {
    return {};
  },
  created() {
    this.findClubInfo(this.clubId);
    this.getBookclubList(this.clubId);
  },
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.bg-image {
  height: 100vh;
  background-image: url("../images/club-backgroud.png");
  background-size: cover;
  position: relative;

  .icon {
    right: 1.5rem;
    top: 1.5rem;
    position: absolute;
  }

  .card {
    width: 100%;
    height: 70rem;
    top: 17em;
    position: absolute;

    .main * {
      text-align: left;
    }

    // 여기서부터 메인 시작!
    .main {
      height: inherit;
      text-align: left;
      padding: 3rem 3rem 4rem;
      background-color: var(--beige);
      border-radius: 5em 5em 0 0;

      .tag {
        display: inline-block;
        padding: 0.2rem 0.6rem;
        margin: 0.5rem 0 0 0.4rem;
        border: 0;
        border-radius: 1em;
        color: var(--white);
        background-color: var(--light-brown);
      }

      .meeting {
        display: flex;
        justify-content: center;
        align-items: center;

        .meeting-button:disabled {
          background-color: var(--medium-grey);
        }
        .meeting-button {
          width: 14.5rem;
          height: 10rem;
          margin: 0 auto;
          padding: 1.5rem;
          border-radius: 2rem;
          border: 0;
          color: var(--white);
          background-color: var(--very-light-brown);
          box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.3);

          &-head {
            display: flex;
            justify-content: space-between;
            align-items: center;

            &-icon {
              display: inline-block;
              color: var(--white);
              width: 3.3em;
              height: 3.3em;
            }
            &-date {
              display: inline-block;
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
          gap: 1rem;
          overflow: scroll;

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
