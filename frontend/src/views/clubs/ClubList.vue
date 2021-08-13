<template>
  <div v-if="myClubList">
    <ClubListItem
      :clubId="myClubList[index].id"
      :maxLength="myClubList.length"
      :index="index"
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
    ...mapState("clubStore", ["myClubList"]),
  },
  data: function () {
    return {
      index: 0,
    };
  },
  methods: {
    ...mapActions("clubStore", ["findClubList"]),
    onClickLeft: function () {
      this.index -= 1;
    },
    onClickRight: function () {
      this.index += 1;
    },
  },
  created() {
    this.findClubList();
  },
};
</script>

<style scoped></style>
