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
        <b>클럽장</b> {{ club.leader_id }}&nbsp;|&nbsp;<b>참가자</b> {{ club.max_member }}명
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
              <span class="font-body-4">책 목록 더보기</span>
            </div>
            <span class="bookcard-info-now font-body-5">읽고 있는 책</span>
            <h5 class="bookcard-info-title">
              {{ book.title.length > 30 ? book.title.substr(0, 30) + "・・・" : book.title }}
            </h5>
            <div class="bookcard-info-subtitle font-body-4">
              {{
                book.author.length > 8 ? book.author.substr(0, 8) + "・・・" : book.author
              }}&nbsp;|&nbsp;{{
                book.publisher.length > 8 ? book.publisher.substr(0, 8) + "・・・" : book.publisher
              }}
            </div>
          </div>
        </div>
      </div>
      <!-- 모임 정보 -->
      <div class="meeting">
        <h5>2021.08.17 오후 9:00</h5>
        <span class="font-body-4">모임까지 00일 00시 00분 남았습니다.</span>
      </div>
      <button :disabled="!isStart" class="button-2 m-top-5">모임 입장하기</button>
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
  data() {
    return {
      isStart: false,
    };
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
  width: 30rem;
  min-height: 50vh;
  margin: 5% auto;
  padding: 4rem 1rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px 4px rgba(161, 160, 228, 0.16);
  background-color: var(--white);
}

.bookcard-background {
  width: 100%;
  margin: 1.5rem auto;
  padding: 1.5rem 0 1.5rem;
  background-color: var(--very-light-grey);
  border-radius: 1em;
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
  background-color: var(--light-orange);
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

.meeting {
  margin-top: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
.meeting h4,
h5 {
  margin: 0.7rem;
}
</style>
