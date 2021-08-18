<template>
  <div v-if="club">
    <div class="club-card" @click="selectClub">
      <div class="club-card-img">
        <img
          :src="
            'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-' +
            clubImg +
            '.jpg'
          "
          alt="클럽이미지"
        />
      </div>
      <div class="club-card-text">
        <h6 v-html="clubName"></h6>
        <p><b>클럽장</b> {{ leaderName }}&nbsp;|&nbsp;<b>참가자</b> {{ nowMember }}명</p>
        <div class="club-card-text-genres">
          <span class="tag" v-for="(genre, idx) in genres" :key="idx">{{
            genreList[genre - 1].name
          }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { mapState } from "vuex";

export default {
  name: "ClubSearchBarPageList",
  props: {
    club: {
      type: Object,
    },
    keyword: {
      type: String,
    },
  },
  methods: {
    // 검색된 클럽 클릭
    selectClub: function () {
      router.push({ name: "ClubSearchBarPageListItem", query: { clubId: this.club.id } });
    },
  },
  computed: {
    ...mapState("searchStore", ["genreList"]),
    clubImg: function () {
      return this.club.img;
    },
    clubName: function () {
      let clubName = this.club.name.replaceAll(
        this.keyword,
        "<span style='color: var(--orange);'>" + this.keyword + "</span>"
      );
      return clubName;
    },
    leaderName: function () {
      return this.club.nickname;
    },
    nowMember: function () {
      return this.club.nowMember;
    },
    genres: function () {
      return this.club.genres;
    },
  },
};
</script>

<style lang="scss" scoped>
.club-card {
  display: flex;
  width: 30rem;
  height: 7.5rem;
  margin: 0 auto;
  background-color: var(--very-light-grey);
  border-radius: 1rem;
  margin-bottom: 1rem;

  &-img {
    width: 5rem;
    height: 100%;

    img {
      width: 5rem;
      height: 7.5rem;
      border-radius: 1rem 0 0 1rem;
    }
  }
  &-text {
    width: 100%;
    text-align: left;
    margin-left: 1.2rem;
    position: relative;
    overflow: hidden;

    h6 {
      white-space: nowrap;
      overflow: hidden;
      margin: 0;
      margin-top: 1rem;
    }
    p {
      margin: 0;
      margin-top: 0.5rem;
    }
    * {
      text-align: left;
    }
  }
}

.tag {
  display: inline-block;
  padding: 0.2rem 0.5rem;
  margin: 0.4rem 0.3rem 0 0;
  border: 0;
  border-radius: 1em;
  color: var(--white);
  background-color: var(--light-orange);
}
</style>
