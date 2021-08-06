<template>
  <div class="">
    <div class="club-card" @click="selectClub">
      <div class="club-card-img">
        <!-- <img :src="clubImg" alt="club image" /> -->
        이미지
      </div>
      <div class="club-card-text">
        <h6>{{ clubName }}</h6>
        <p><span>클럽장</span>{{ leaderId }} | <span>참가자</span>{{ maxMember }}</p>
        <div class="club-card-genres">
          <span class="genre-keyword" v-for="(genre, idx) in genres" :key="idx" :value="genre">
            <button class="tag">{{ genre.genreName }}</button>
          </span>
        </div>
      </div>
    </div>
    <hr />
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
      return this.club.img;
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

<style scoped>
.club-card {
  width: 80%;
  height: 15%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
  border-radius: 10px;
  background-color: #f1ece5;
}

img {
  object-fit: cover;
}

.club-card-text * {
  text-align: left;
}

.tag {
  margin: 0 0.2%;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 10px;
  color: white;
  background-color: #a4c0f3;
}
</style>
