<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        <icon-base><arrow-left /></icon-base>
      </div>
    </div>
    <div>
      <input v-model="clubName" type="text" placeholder="검색할 클럽명을 입력해주세요" />
    </div>
    <label> 검색된 클럽 목록 </label>
    <ClubSearchBarPageList
      v-for="(club, idx) in clubList"
      :key="idx"
      :club="club"
      @select-club="onSelectClub"
      class="page-item"
    />
  </div>
</template>

<script>
import IconBase from "../../components/icons/IconBase.vue";
import ArrowLeft from "../../components/icons/ArrowLeft.vue";
import ClubSearchBarPageList from "@/views/clubs/ClubSearchBarPageList.vue";

export default {
  name: "ClubSearchBarPage",
  components: {
    ClubSearchBarPageList,
    IconBase,
    ArrowLeft,
  },
  data: function () {
    return {
      clubName: "",
    };
  },
  methods: {
    onSelectClub: function (club) {
      console.log(club);
    },
  },
  computed: {
    clubList: function () {
      return this.$store.state.examples.bookclubs[0].clubList;
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 0;
}

input {
  width: 100%;
  height: 20%;
  padding: 7.5% 5% 7.5% 20%;
  text-align: left;
  border: 0;
  background-color: #fff;
  box-sizing: border-box;
}

label {
  text-align: left;
  display: block;
  margin: 5.5% 0 1.5% 14%;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--grey);
}
</style>
