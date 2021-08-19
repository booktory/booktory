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
        <div class="sub-info">
          <div v-if="board.fileUrl" @click="clickFile" class="file">
            <icon-base :width="'1.6rem'" :height="'1.6rem'" :iconColor="'var(--grey)'"
              ><icon-file
            /></icon-base>
          </div>
          <div v-if="userId == board.userId" class="font-body-5 delete" @click="clickDelete">
            <icon-base :width="'1.6rem'" :height="'1.6rem'" :iconColor="'var(--grey)'"
              ><icon-x
            /></icon-base>
          </div>
        </div>
        <span class="font-body-3 nickname">{{ board.nickname }}</span>
        <span class="font-body-5">{{ convertTime(board.date) }}</span>
      </div>
    </div>
    <div v-html="board.contents" class="board-content font-body-4"></div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import IconFile from "@/components/icons/IconFile.vue";
import { mapState, mapActions } from "vuex";
var moment = require("moment");
require("moment-timezone");
moment.tz.setDefault("Asia/Seoul");

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
  computed: {
    ...mapState(["userId"]),
  },
  methods: {
    ...mapActions("boardStore", ["deleteBoard"]),
    // 삭제하기 버튼
    clickDelete() {
      Swal.fire({
        showCancelButton: true,
        title: "게시글을 삭제 하시겠습니까?",
        confirmButtonText: "네, 삭제할래요",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteBoard(this.board.boardId);
        }
      });
    },
    // 첨부된 파일 확인
    clickFile() {
      window.open(this.board.fileUrl);
    },
    // 등록 시간 년월일 변환
    convertTime(data) {
      let ampm = moment(data).format("A") == "AM" ? "오전" : "오후";
      let dateStr = moment(data).format("YYYY년 M월 D일 " + ampm + " h시 mm분");
      return dateStr;
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
  display: flex;
  gap: 0.7rem;
}
.nickname {
  font-weight: bold;
  color: var(--orange);
}
.board-content {
  margin: 1rem 0.7rem;
  text-align: justify;
  word-wrap: break-word;
}
</style>
