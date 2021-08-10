<template>
  <div class="container">
    <div class="top-box">
      <input
        v-model="clubName"
        type="text"
        placeholder="검색할 클럽명을 입력해주세요"
        class="absolute-box font-body-5"
      />
      <div class="icon top-icon" @click="$router.go(-1)">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
    </div>
    <div class="club-search-result">
      <label> 검색된 클럽 목록 </label>
      <ClubSearchBarPageList
        v-for="(club, idx) in clubList"
        :key="idx"
        :club="club"
        @select-club="onSelectClub"
        class="page-item"
      />
    </div>
  </div>
</template>

<script>
import ClubSearchBarPageList from "@/views/clubs/ClubSearchBarPageList.vue";
// import TopHeader from "@/views/TopHeader.vue";

export default {
  name: "ClubSearchBarPage",
  components: {
    ClubSearchBarPageList,
    // TopHeader,
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

<style lang="scss" scoped>
@mixin clearfix() {
  &::after {
    content: "";
    display: block;
    clear: both;
  }
}

.top-box {
  width: 100%;
  position: relative;
  margin: 0;
  @include clearfix();

  .absolute-box {
    position: absolute;
    margin: 0;
    top: 0;
    left: 0;
  }
  .top-icon {
    position: absolute;
    margin: 0;
    top: 1.5rem;
    left: 1.5rem;
  }

  input {
    position: absolute;
    width: 100%;
    height: 3rem;
    padding: 7.5% 5% 7.5% 20%;
    text-align: left;
    border: 0;
    background-color: #fff;
    box-sizing: border-box;
  }
}
.club-search-result {
  padding-top: 20%;

  label {
    text-align: left;
    display: block;
    margin: 0 0 1.5% 14%;
    font-size: 1.2rem;
    font-weight: 500;
    color: var(--grey);
  }
}
</style>
