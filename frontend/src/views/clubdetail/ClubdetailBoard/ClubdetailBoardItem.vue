<template>
  <div class="board-item-wrapper">
    <div class="board-frame">
      <div class="profileImg">
        <img
          :src="
            board.profileImg
              ? board.profileImg
              : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
          "
          alt="프로필 사진"
        />
      </div>
      <div class="board-info">
        <div v-if="board.fileUrl" class="sub-info">
          <div @click="clickFile" class="file">
            <icon-base :width="'1.6rem'" :height="'1.6rem'" :iconColor="'var(--grey)'"
              ><icon-file
            /></icon-base>
          </div>
        </div>
        <span class="font-body-3 nickname">{{ board.nickname }}</span>
        <span class="font-body-5">{{ board.date }}</span>
      </div>
    </div>
    <div v-html="board.contents" class="board-content font-body-4"></div>
  </div>
</template>

<script>
import IconFile from "@/components/icons/IconFile.vue";

export default {
  name: "ClubdetailBoardItem",
  components: {
    IconFile,
  },
  props: {
    board: {
      type: Object,
    },
  },
  methods: {
    clickFile() {
      window.open(this.board.fileUrl);
    },
  },
};
</script>

<style scoped>
* {
  text-align: left;
}
.board-item-wrapper {
  padding: 0;
}
.board-frame {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  align-items: center;
  gap: 0.7rem;
  padding: 0;
}
.profileImg {
  width: 4rem;
  height: 4rem;
}
.board-info {
  position: relative;
  display: grid;
  grid-template-rows: 3fr 2fr;
  justify-content: start;
  align-items: end;
  gap: 0.2rem;
  padding: 0;
}
.sub-info {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0.5rem 0.2rem;
}
.file {
  float: right;
  margin: -0.2rem 0.5rem 0 0;
  padding: 0;
}
.nickname {
  font-weight: bold;
  color: var(--orange);
}
.board-content {
  margin: 1rem 0.7rem;
  text-align: justify;
}
</style>
