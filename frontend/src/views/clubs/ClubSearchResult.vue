<template>
  <div>
    <div v-if="club">
      <div class="club-card" @click="selectClub">
        <div class="club-card-img">
          <div class="club-card-img-div">
            <img
              :src="
                'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-' +
                clubImg +
                '.jpg'
              "
              alt="클럽이미지"
            />
          </div>
        </div>
        <div class="club-card-text">
          <h6>{{ clubName }}</h6>
          <p><b>클럽장</b>{{ leaderName }}&nbsp;|&nbsp;<b>참가자</b> {{ nowMember }}명</p>
          <div class="club-card-text-genres">
            <span
              :class="{ orange: genreList[genre - 1].isSelect }"
              class="tag"
              v-for="(genre, idx) in genres"
              :key="idx"
              >{{ genreList[genre - 1].name }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { mapState } from "vuex";

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
    ...mapState("searchStore", ["genreList"]),
    clubImg: function () {
      return this.club.img;
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

    &-div {
      width: 5rem;
      height: 7.5rem;
      border-radius: 1rem 0 0 1rem;
      overflow: hidden;
      position: relative;

      img {
        min-width: 100%;
        height: 7.5rem;
        margin: auto;
        position: absolute;
        left: -100%;
        right: -100%;
        top: -100%;
        bottom: -100%;
      }
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
.orange {
  background-color: var(--orange);
}
</style>
