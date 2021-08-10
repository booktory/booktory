<template>
  <div>
    <!-- 헤드 => 좌우 넘기기 있음 -->
    <div class="header">
      <span v-if="index !== 0">
        <div class="icon" @click="clickLeft">
          <icon-base><icon-chevron-left /></icon-base>
        </div>
      </span>
      <span v-else>
        <div class="icon">
          <icon-base :iconColor="'none'"><chevron-left /></icon-base>
        </div>
      </span>
      <h5>
        {{ club.name }}
      </h5>
      <span v-if="index !== maxLength - 1">
        <div class="icon" @click="clickRight">
          <icon-base><icon-chevron-right /></icon-base>
        </div>
      </span>
      <span v-else>
        <div class="icon">
          <icon-base :iconColor="'none'"><chevron-right /></icon-base>
        </div>
      </span>
    </div>

    <!-- 바디1 => 클럽 정보 -->
    <div class="card-background club-info">
      <h4 class="club-info-title">{{ club.name }}</h4>
      <div class="font-body-4 club-info-user">
        클럽장 {{ club.leader_id }} | 참가자 {{ club.max_member }}
      </div>
      <div>
        <div class="font-body-3 club-info-text">
          {{ club.info }}
        </div>
        <span
          v-for="(clubGenre, idx) in club.genres"
          :key="idx"
          class="font-body-4 club-info-genre"
        >
          {{ clubGenre.genreName }}
        </span>
      </div>

      <!-- 바디2 => 책 정보 -->
      <div class="bookcard-background">
        <div class="bookcard-box">
          <img :src="book.thumbnail" alt="bookThumbnail" class="bookcard-image" />
          <div class="bookcard-info">
            <div class="bookcard-info-more">
              <p class="font-body-4">책 목록 더보기</p>
            </div>
            <!-- <span class="bookcard-info-now font-body-4">읽고 있는 책</span> -->
            <h5 class="bookcard-info-title">{{ book.title }}</h5>
            <div class="bookcard-info-subtitle font-body-4">{{ book.author }}</div>
            <div class="bookcard-info-subtitle font-body-5">{{ book.publisher }}</div>
          </div>
        </div>
        <!-- 모임 정보 -->
        <div class="meeting">
          <h5>2021.08.17 오후 9:00</h5>
          <span class="font-body-4">모임까지 00일 00시 00분 남았습니다.</span>
        </div>
      </div>
      <button class="button-2 m-top-5">모임 입장하기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubListItem",
  props: {
    bookclub: {
      type: Object,
    },
    maxLength: {
      type: Number,
    },
    index: {
      type: Number,
    },
  },
  methods: {
    clickLeft: function () {
      this.$emit("click-left");
    },
    clickRight: function () {
      this.$emit("click-right");
    },
  },
  computed: {
    club: function () {
      return this.bookclub.clubList[0];
    },
    book: function () {
      return this.bookclub.bookList[0];
    },
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header .icon {
  margin: 0 2em;
}

.card-background {
  width: 80%;
  min-height: 58vh;
  margin: 5% auto;
  padding: 8% 2%;
  border-radius: 10px;
  box-shadow: 0 4px 8px 4px rgba(161, 160, 228, 0.16);
  background-color: #ffffff;
}

.bookcard-background {
  width: 100%;
  margin: 5% auto;
  padding-bottom: 20px;
  background-color: #f3f3f3;
  border-radius: 10px;
}

.bookcard-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  gap: 10px;
  padding: 8%;
}

.bookcard-image {
  width: 8rem;
  height: 12rem;
  margin-top: 1.4rem;
  border-radius: 1rem;
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}

.bookcard-info {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.meeting {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
.meeting h4,
h5 {
  margin: 5px;
}

.club-info-title {
  margin-top: 1.5rem;
  margin-bottom: 1rem;
}
.club-info-user {
  margin-bottom: 1rem;
}
.club-info-text {
  margin-bottom: 0.5rem;
}
.club-info-genre {
  display: inline-block;
  margin: 0 auto;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 1em;
  color: white;
  background-color: var(--light-orange);
  margin: 0.5% 0.4%;
}

.bookcard-info-more {
  margin-left: auto;
  color: var(--grey);
}
/* .bookcard-info-now {
  margin-bottom: 0.5rem;
} */

.bookcard-info-title {
  text-align: justify;
  margin-bottom: 1rem;
}
.bookcard-info-subtitle {
  margin-left: 1rem;
  margin-bottom: 0.5rem;
}
</style>
