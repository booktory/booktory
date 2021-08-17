<template>
  <div class="container bg-img">
    <div>
      <TopHeader :noBack="true" />
      <div v-if="clubInfo">
        <div class="card">
          <div class="main">
            <h3>
              {{ clubInfo.name }}
            </h3>
            <div class="sub-info">
              <p class="icon" @click="clickShare">
                <icon-base><icon-share /></icon-base>
              </p>
            </div>
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
              >&nbsp;|&nbsp;<span class="font-body-4"
                ><b>참가자</b> {{ clubInfo.nowMember }}명</span
              >
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
              <div v-else-if="!isLeader">
                <button disabled type="button" class="meeting-button">
                  <div class="meeting-button-head">
                    <div class="meeting-button-head-icon">
                      <icon-base><icon-video /></icon-base>
                    </div>
                  </div>
                  <h5 class="meeting-button-body">예정된 모임 없음</h5>
                </button>
              </div>
              <div v-else>
                <button
                  @click="$router.push({ name: 'ClubdetailMeetingCreate' })"
                  type="button"
                  class="meeting-button create"
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
              <p class="font-body-3">•&nbsp;&nbsp;한 달에 {{ clubInfo.volumeRule }}권을 읽어요</p>
              <p class="font-body-3">•&nbsp;&nbsp;{{ clubInfo.weekRule }}주에 1번 만나요</p>
              <p v-if="clubInfo.freeRule" class="font-body-3 m-top-1">
                •&nbsp;&nbsp;{{ clubInfo.freeRule }}
              </p>
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
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Swal from "sweetalert2";
import Navbar from "@/views/clubdetail/Navbar.vue";
import IconVideo from "@/components/icons/IconVideo.vue";
import IconBookmark from "@/components/icons/IconBookmark.vue";
import IconShare from "@/components/icons/IconShare.vue";
var moment = require("moment");

export default {
  name: "ClubdetailHome",
  components: {
    TopHeader,
    Navbar,
    IconVideo,
    IconBookmark,
    IconShare,
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "meetingInfo", "clubId", "isLeader", "clubImage"]),
    ...mapState("searchStore", ["genreList"]),
    ...mapState("bookclubStore", ["bookclubList"]),
  },
  methods: {
    ...mapActions("bookclubStore", ["getBookClubList", "attendMeeting"]),
    ...mapActions("clubStore", ["findClubInfo"]),
    // 공유하기 버튼
    clickShare() {
      Swal.fire({
        showCancelButton: true,
        title: "클럽 공유하기",
        text: "아래의 URL을 복사해서 클럽을 공유해주세요!",
        input: "text",
        inputValue: "https://i5a607.p.ssafy.io/club/search/barpage/item?clubId=" + this.clubId,
        confirmButtonText: "복사하기",
        cancelButtonText: "취소",
        preConfirm: (url) => {
          const shareUrl = document.createElement("textarea");
          document.body.appendChild(shareUrl);
          shareUrl.value = `${url}`;
          shareUrl.select();
          document.execCommand("copy");
          document.body.removeChild(shareUrl);
        },
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            icon: "success",
            title: "URL이 복사되었어요",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
        }
      });
    },
    // 입장하기 버튼
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
      let ampm = moment(data).format("A") == "AM" ? "오전" : "오후";
      let dateStr = moment(data).format("YY년 M월 D일<br>" + ampm + " h시 mm분");
      return dateStr;
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  data() {
    return {};
  },
  created() {
    this.findClubInfo(this.clubId);
    this.getBookClubList(this.clubId);
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
.bg-img {
  width: 100vw;
  height: 100%;
  min-height: 100vh;
  background-size: contain;
  z-index: -1;
  padding: 0;

  .card {
    width: inherit;
    height: inherit;
    min-height: 80vh;
    margin-top: 10rem;
    border-radius: 5rem 5rem 0 0;
    padding-bottom: 5rem;
    background-color: var(--beige);

    .main * {
      text-align: left;
    }
    // 여기서부터 메인 시작!
    .main {
      height: 100%;
      padding: 3rem 3rem 0;
      position: relative;

      .sub-info {
        position: absolute;
        top: 2.6rem;
        right: 3rem;
        display: flex;
        gap: 0.7rem;
      }

      .rules {
        h5 {
          margin-bottom: 1rem;
        }
        p {
          margin: 0.7rem 0;
        }
      }

      h3,
      h5 {
        margin: 0;
      }

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
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1rem;
        justify-content: center;
        justify-items: center;
        align-items: center;

        .meeting-button:disabled {
          background-color: var(--medium-grey);
        }
        .meeting-button.create {
          background-color: var(--light-orange);
        }
        .meeting-button {
          width: 15rem;
          height: 10rem;
          margin: 0.3rem auto;
          padding: 1.5rem;
          border-radius: 2rem;
          border: 0;
          color: var(--white);
          background-color: var(--very-light-brown);
          box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.25);

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
</style>
