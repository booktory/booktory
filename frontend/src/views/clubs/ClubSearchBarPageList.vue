<template>
  <div class="">
    <div class="club-card" @click="selectClub">
      <div class="club-card-img">
        <img src="https://picsum.photos/200" alt="#" />
      </div>
      <div class="club-card-text">
        <h6 class="club-card-text-name">{{ clubName }}</h6>
        <p class="club-card-text-user">
          <span>클럽장</span>{{ leaderId }} | <span>참가자</span>{{ maxMember }}
        </p>
        <div class="club-card-text-genres">
          <span class="genre-keyword" v-for="(genre, idx) in genres" :key="idx" :value="genre">
            <button class="tag">{{ genre.genreName }}</button>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubSearchBarPageList",
  props: {
    club: {
      type: Object,
    },
  },
  methods: {
    selectClub: function () {
      this.$emit("select-club", this.club);
    },
  },
  computed: {
    clubImg: function () {
      return this.club.clubImg;
    },
    clubName: function () {
      return this.club.name;
    },
    leaderId: function () {
      return this.club.leader_id;
    },
    maxMember: function () {
      return this.club.max_member;
    },
    genres: function () {
      return this.club.genres;
    },
  },
};
</script>

<style lang="scss" scoped>
.club-card {
  width: 80%;
  height: 8rem;
  background-color: var(--very-light-grey);
  border-radius: 1rem;
  margin: 1rem auto;

  .club-card-img {
    width: 30%;
    height: 100%;
    float: left;
    align-content: center;

    img {
      width: 100%;
      height: 100%;
      border-radius: 1rem;
    }
  }
  .club-card-text * {
    text-align: left;
  }
  .club-card-text {
    width: 70%;
    height: 100%;
    float: left;
    &-name {
      padding: 5%;
    }
    &-user {
      padding-left: 5%;
      padding-top: 1%;
    }
    &-genres {
      padding-left: 5%;
      padding-top: 1%;
    }
  }
}

h6,
p {
  margin: 0;
}

.tag {
  display: inline-block;
  margin: 0 auto;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 1em;
  color: white;
  background-color: var(--light-orange);
  margin: 0.5% 0.4%;
}
</style>
