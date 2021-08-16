<template>
  <div v-if="myClubList && myClubList.length > 0">
    <ClubListItem
      :clubId="myClubList[clubIndex].id"
      :maxLength="myClubList.length"
      :index="clubIndex"
      @click-left="onClickLeft"
      @click-right="onClickRight"
      class="bg-img"
    />
  </div>
</template>

<script>
import ClubListItem from "@/views/clubs/ClubListItem.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubList",
  components: {
    ClubListItem,
  },
  computed: {
    ...mapState("clubStore", ["myClubList", "clubIndex"]),
  },
  methods: {
    ...mapActions("clubStore", ["findClubList", "setClubIndex"]),
    onClickLeft: function () {
      this.setClubIndex(this.clubIndex - 1);
    },
    onClickRight: function () {
      this.setClubIndex(this.clubIndex + 1);
    },
  },
  created() {
    this.findClubList();
  },
};
</script>

<style scoped></style>
