<template>
  <div class="container">
    <!-- <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div> -->
    <TopHeader />
    <h5>
      {{ club.name }}
    </h5>
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
      </div>
      <button
        type="button"
        class="button-2 m-top-10"
        @click="$router.push({ name: 'ClubQuestion' })"
      >
        문의 게시판
      </button>
      <button type="button" class="button-2 m-top-10">가입 신청</button>
    </div>
  </div>
</template>
<script>
import TopHeader from "@/views/TopHeader.vue";

export default {
  name: "ClubSearchBarPageListItem",
  components: {
    TopHeader,
  },
  computed: {
    club: function () {
      return this.$store.state.examples.bookclubs[0].clubList[0];
    },
    book: function () {
      return this.$store.state.examples.bookclubs[0].bookList[0];
    },
  },
};
</script>

<style scoped>
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
</style>
