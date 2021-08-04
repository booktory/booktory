<template>
  <div>
    <ClubListItem
      :bookclub="bookclubList[index]"
      :maxLength="bookclubList.length"
      :index="index"
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
  </div>
</template>

<script>
import ClubListItem from "@/views/clubs/ClubListItem.vue";

export default {
  name: "ClubList",
  components: {
    ClubListItem,
  },
  data: function () {
    return {
      index: 0,
    };
  },
  methods: {
    onClickLeft: function () {
      this.index -= 1;
    },
    onClickRight: function () {
      this.index += 1;
    },
  },
  computed: {
    bookclubList: function () {
      return this.$store.state.examples.bookclubs;
    },
  },
};
</script>

<style></style>
