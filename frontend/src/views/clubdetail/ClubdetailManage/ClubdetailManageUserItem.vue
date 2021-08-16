<template>
  <div class="user-div">
    <div class="profileImg">
      <img
        :src="
          profileImg
            ? profileImg
            : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
        "
      />
    </div>
    <span class="font-body-4">{{ nickname }}</span>
    <div class="btn-div" v-if="isApply">
      <button @click="clickAccept">수락</button>
      <button @click="clickReject" class="reject">거절</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import Swal from "sweetalert2";

export default {
  name: "ClubdetailManageUserItem",
  components: {},
  props: {
    userClubId: {
      type: Number,
    },
    nickname: {
      type: String,
    },
    profileImg: {
      type: String,
    },
    isApply: {
      type: Boolean,
    },
  },
  methods: {
    ...mapActions("clubStore", ["acceptToClub", "rejectJoin"]),
    clickAccept() {
      Swal.fire({
        showCancelButton: true,
        title: "가입신청을 수락할까요?",
        confirmButtonText: "수락하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.acceptToClub(this.userClubId);
        }
      });
    },
    clickReject() {
      Swal.fire({
        showCancelButton: true,
        title: "가입신청을 거절할까요?",
        confirmButtonText: "거절하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.rejectJoin(this.userClubId);
        }
      });
    },
  },
};
</script>

<style scoped>
.user-div {
  display: grid;
  grid-template-columns: 21% 45% 34%;
  align-items: center;
  margin-bottom: 0.6rem;
  justify-items: start;
}
.btn-div {
  justify-self: right;
}
.user-div button {
  font-size: 1.2rem;
  margin: 0 0.2rem;
  padding: 0.3rem 0.7rem;
  border-radius: 1em;
  border: 1px solid var(--dark-grey);
  background-color: var(--white);
  color: var(--dark-grey);
}
button.reject {
  border: 1px solid var(--orange);
  background-color: var(--orange);
  color: var(--white);
}
</style>
