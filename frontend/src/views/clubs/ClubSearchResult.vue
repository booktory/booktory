<template>
  <div>
    <div v-if="club">
      <div class="club-card" @click="selectClub">
        <div class="club-card-img">
          <img
            :src="
              clubImg
                ? clubImg
                : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/club.png'
            "
            alt="클럽이미지"
          />
        </div>
        <div class="club-card-text">
          <h6>{{ clubName }}</h6>
          <p><b>클럽장</b>{{ leaderName }}&nbsp;|&nbsp;<b>참가자</b> {{ nowMember }}명</p>
          <div class="club-card-text-genres">
            <span class="tag" v-for="(genre, idx) in genres" :key="idx">{{ genre }} </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "ClubSearchResult",
  props: {
    club: {
      type: Object,
    },
  },
  methods: {
    // 검색된 클럽 클릭
    selectClub: function () {
      router.push({ name: "ClubSearchBarPageListItem", query: { clubId: this.club.id } });
    },
  },
  computed: {
    clubImg: function () {
      return this.club.clubImg;
    },
    clubName: function () {
      return this.club.name;
    },
    leaderName: function () {
      return this.club.leaderName;
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
