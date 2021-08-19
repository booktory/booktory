<template>
  <div v-if="clubInfo" class="container bg-img">
    <div>
      <TopHeader />
      <div class="header">
        <h5>{{ clubInfo.name }}</h5>
      </div>

      <!-- 바디1 => 클럽 정보 -->
      <div class="card-background club-info">
        <div class="share">
          <p class="icon" @click="clickShare">
            <icon-base><icon-share /></icon-base>
          </p>
        </div>
        <h4 class="club-info-title">{{ clubInfo.name }}</h4>
        <div class="font-body-4 club-info-user">
          <b>클럽장</b> {{ clubInfo.leaderName }}&nbsp;|&nbsp;<b>참가자</b>
          {{ clubInfo.nowMember }}명
        </div>
        <div>
          <div class="font-body-3 club-info-text">
            {{ clubInfo.info }}
          </div>
          <span
            v-for="(genre, idx) in clubInfo.genres"
            :key="idx"
            :id="genre"
            class="font-body-4 club-info-genre"
          >
            {{ genreList[genre - 1].name }}
          </span>
        </div>

        <div v-if="clubInfo.endDateTime">
          <!-- 바디2 => 책 정보 -->
          <div class="bookcard-background">
            <div class="bookcard-box">
              <img :src="clubInfo.thumbnail" alt="bookThumbnail" class="bookcard-image" />
              <div class="bookcard-info">
                <div class="bookcard-info-more">
                  <span @click="$router.push({ name: 'ClubdetailBook' })" class="font-body-4"
                    >책 목록 더보기</span
                  >
                </div>
                <span class="bookcard-info-now font-body-5">읽고 있는 책</span>
                <h5 class="bookcard-info-title">
                  {{
                    clubInfo.title.length > 30
                      ? clubInfo.title.substr(0, 30) + "・・・"
                      : clubInfo.title
                  }}
                </h5>
                <div class="bookcard-info-subtitle font-body-4">
                  {{
                    clubInfo.author.length > 8
                      ? clubInfo.author.substr(0, 8) + "・・・"
                      : clubInfo.author
                  }}&nbsp;|&nbsp;{{
                    clubInfo.publisher.length > 8
                      ? clubInfo.publisher.substr(0, 8) + "・・・"
                      : clubInfo.publisher
                  }}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-else>
          <span class="empty-meeting">읽고 있는 책이 없습니다</span>
        </div>
        <button type="button" class="button-2 white-bg m-top-5" @click="clickQuestion">
          문의 게시판
        </button>
        <button type="button" class="button-2" @click="clickApply">가입 신청</button>
      </div>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import router from "@/router";
import Swal from "sweetalert2";
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import IconShare from "@/components/icons/IconShare.vue";

export default {
  name: "ClubSearchBarPageListItem",
  components: {
    TopHeader,
    Navbar,
    IconShare,
  },
  computed: {
    ...mapState("clubStore", ["clubInfo", "meetingInfo", "clubImage"]),
    ...mapState("searchStore", ["genreList"]),
  },
  data() {
    return {
      clubId: this.$route.query.clubId,
    };
  },
  methods: {
    ...mapActions("clubStore", ["findClubOpenInfo", "applyToClub"]),
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
    // 문의게시판 버튼
    clickQuestion() {
      router.push({ name: "ClubQuestion", query: { clubId: this.clubId } });
    },
    // 가입 신청 버튼
    clickApply() {
      Swal.fire({
        showCancelButton: true,
        title: "가입 신청 하시겠습니까?",
        confirmButtonText: "신청하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.applyToClub();
        }
      });
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--background-" + this.clubImage + ")";
    },
  },
  async created() {
    this.findClubOpenInfo(this.clubId);
    await new Promise((resolve) => setTimeout(resolve, 100));
    this.setBackgroundImage();
  },
};
</script>

<style scoped>
.bg-img {
  width: 100vw;
  height: 100%;
  min-height: 100vh;
  background-size: cover;
  z-index: -1;
}

.header {
  margin: 0 2em;
}
h5 {
  display: inline-block;
  margin: 0.7rem;
}

.card-background {
  width: 30rem;
  min-height: 50%;
  margin: 5% auto;
  padding: 4rem 1rem 5rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px 4px rgba(161, 160, 228, 0.16);
  background-color: var(--white);
}

.bookcard-background {
  width: 100%;
  margin: 2.5rem auto 1.5rem;
  padding: 1.5rem 0 1.5rem;
  background-color: var(--very-light-grey);
  border-radius: 1em;
}
.club-info {
  position: relative;
}
.share {
  position: absolute;
  top: 0.8rem;
  right: 1.7rem;
}
.club-info-title {
  margin-top: 0;
  margin-bottom: 1rem;
}
.club-info-user {
  margin-bottom: 1rem;
}
.club-info-text {
  margin: 0 2rem 1rem;
}
.club-info-genre {
  display: inline-block;
  padding: 0.3rem 0.7rem;
  margin: 0 0.2rem;
  border: 0;
  border-radius: 1em;
  color: var(--white);
  background-color: var(--light-brown);
}

.bookcard-box {
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  gap: 1.6rem;
  padding: 0 1.5rem;
}
.bookcard-image {
  width: 7rem;
  border-radius: 1rem;
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
.bookcard-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.bookcard-info-more {
  position: absolute;
  top: 0;
  right: 0;
  margin-right: 1.5rem;
  color: var(--grey);
}
.bookcard-info-title {
  text-align: left;
  margin: 0.5rem 0;
}
.bookcard-info-subtitle {
  text-align: left;
}

.empty-meeting {
  display: inline-block;
  margin: 4rem 0 1rem;
  font-size: 1.4rem;
  color: var(--grey);
}
</style>
