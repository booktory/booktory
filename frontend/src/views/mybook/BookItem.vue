<template>
  <div class="book-item-wrapper">
    <div class="book-thumbnail-wrapper">
      <img
        :src="book.thumbnail"
        :alt="book.title"
        :title="book.title"
        class="book-thumbnail"
        @click="clickBook"
      />
    </div>
    <div v-if="index % 4 == 0" class="book-bottom"></div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import Swal from "sweetalert2";
var moment = require("moment");

export default {
  name: "BookItem",
  components: {},
  props: {
    index: {
      type: Number,
    },
    book: {
      type: Object,
    },
  },
  data() {
    return {
      memoStyle: "",
    };
  },
  methods: {
    ...mapActions("mybookStore", ["registerBookMemo", "cancelBookMemo"]),
    clickBook() {
      Swal.fire({
        html: `
        <div
          style="
            width: auto;
            padding: 1rem 0 0.4rem;
            display: flex;
            align-items: center;
            gap: 2rem;
          "
        >
          <img
            src="${this.book.thumbnail}"
            alt="bookThumbnail"
            style="
              width: 7rem;
              height: 10rem;
              border-radius: 1rem;
              box-shadow: 0 0.2rem 0.4rem 0 var(--bg-black);
            "
          />
          <div
            style="
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: flex-start;
            "
          >
            <span class="font-body-4" style="color: var(--orange)">${moment(
              new Date(this.book.readDate)
            ).format("YYYY년 M월 D일 읽었어요")}</span>
            <h5
              class="title"
              style="
                margin: 0.7rem 0 0.8rem;
                text-align: left;
                ">${
                  this.book.title.length > 25
                    ? this.book.title.substring(0, 25) + "・・・"
                    : this.book.title
                }</h5>
            <span class="font-body-5">${
              this.book.author.length > 20
                ? this.book.author.substr(0, 20) + "・・・"
                : this.book.author
            }
            </span>
            <span class="font-body-5">${
              this.book.publisher.length > 13
                ? this.book.publisher.substr(0, 13) + "・・・"
                : this.book.publisher
            }&nbsp;|&nbsp;${this.book.date}
            </span>
          </div>
        </div>
        <div
          v-if="false"
          style='
            width: auto;
            padding: 1.5rem 1.2rem;
            margin-top: 1.5rem;
            background-color: var(--very-light-grey);
            border-radius: 1rem;
            display: flex;
            align-items: flex-start;
            gap: 2rem;
            font-size: 1.2rem;
            ${this.memoHtml}
          </div>
        `,
        confirmButtonText: this.book.memo == "" ? "새로운 메모 작성" : "메모 수정",
        showDenyButton: this.book.memo == "" ? false : true,
        denyButtonText: "메모 삭제",
      }).then((result) => {
        if (result.isConfirmed) {
          // 새로운 메모 작성 or 메모 수정
          (async () => {
            const { value: text } = await Swal.fire({
              inputLabel: this.book.memo == "" ? "새로운 메모 작성" : "작성된 메모 수정",
              input: "textarea",
              inputValue: this.book.memo,
              inputPlaceholder: "내용을 입력해주세요",
              showCancelButton: true,
              confirmButtonText: "등록",
              cancelButtonText: "취소",
            });
            if (text) {
              let memoData = {
                bookId: this.book.id,
                memo: text,
              };
              this.registerBookMemo(memoData);
            }
          })();
        } else if (result.isDenied) {
          // 작성된 메모 삭제
          Swal.fire({
            showCancelButton: true,
            title: "정말 삭제 하시겠습니까?",
            confirmButtonText: "네, 삭제할래요",
            cancelButtonText: "아니요",
          }).then((result) => {
            if (result.isConfirmed) {
              this.cancelBookMemo(this.book.id);
            }
          });
        }
      });
    },
  },
  mounted() {
    if (this.book.memo == "") {
      this.memoHtml = "justify-content: center; color: var(--grey);'>작성된 코멘트가 없습니다";
    } else {
      this.memoHtml = "text-align: justify;'>" + this.book.memo;
    }
  },
};
</script>

<style scoped>
.book-item-wrapper {
  position: relative;
  align-items: center;
}
.book-thumbnail-wrapper {
  position: relative;
  align-items: end;
}
.book-thumbnail {
  width: 5.5rem;
  height: 8rem;
  margin-top: 1.8rem;
  border-radius: 1rem;
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
.book-bottom {
  position: absolute;
  z-index: 10;
  left: -1.5rem;
  bottom: -0.4rem;
  width: 31rem;
  height: 0.8rem;
  margin: 0 auto;
  border-radius: 1rem;
  background-color: var(--very-light-brown);
  box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);
}
</style>
