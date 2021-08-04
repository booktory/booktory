<template>
  <div class="container">
    <!-- 헤드 => 좌우 넘기기 있음 -->
    <div class="header">
      <span v-if="index !== 0">
        <img src="@/assets/icons/chevron-left.svg" alt="pre" @click="clickLeft" />
      </span>
      <span v-else>
        <img src="@/assets/icons/chevron-left(empty).svg" alt="pre" />
      </span>

      <h5>
        {{ club.name }}
      </h5>
      <span v-if="index !== maxLength - 1">
        <img src="@/assets/icons/chevron-right.svg" alt="next" @click="clickRight" />
      </span>
      <span v-else>
        <img src="@/assets/icons/chevron-right(empty).svg" alt="next" />
      </span>
    </div>

    <!-- 바디1 => 클럽 정보 -->
    <div class="card-background">
      <h4>{{ club.name }}</h4>
      <div class="font-body-4">클럽장 {{ club.leader_id }} | 참가자 {{ club.max_member }}</div>
      <div>
        <div class="font-body-2">
          {{ club.info }}
        </div>
        <span v-for="(clubGenre, idx) in club.genres" :key="idx" class="font-body-4">
          {{ clubGenre.genreName }}
        </span>
      </div>

      <!-- 바디2 => 책 정보 -->
      <div class="bookcard-background">
        <div class="bookcard-box">
          <img :src="book.thumbnail" alt="bookThumbnail" class="bookcard-image" />
          <div class="bookcard-info">
            <p style="text-align: right" class="sub-title">책 목록 더보기</p>
            <span class="font-body-4">읽고 있는 책</span>
            <h5 style="text-align: left; margin: 0px">{{ book.title }}</h5>
            <div class="sub-title">{{ book.author }} | {{ book.publisher }}</div>
          </div>
        </div>
        <!-- 모임 정보 -->
        <div class="meeting">
          <h5>2021.08.17 오후 9:00</h5>
          <span class="font-body-4">모임까지 00일 00시 00분 남았습니다.</span>
        </div>
      </div>
      <button>모임 입장하기</button>
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

.card-background {
  width: 80%;
  height: 60%;
  margin: 5% auto 15%;
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
  padding: 8% 5% 5%;
}

.bookcard-image {
  width: 36%;
  height: 50%;
  border-radius: 10px;
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
</style>
