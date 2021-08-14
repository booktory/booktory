<template>
  <div class="badge-item-wrapper" @click="clickBadge">
    <div v-if="badge.state" class="isTrue">
      <img
        :src="'https://booktory.s3.ap-northeast-2.amazonaws.com/static/badge/' + badgeId + '.png'"
        class="badgeImage"
      />
      <div v-if="badge.isMain" class="main-badge">
        <icon-base :iconColor="'var(--white)'"><icon-star /></icon-base>
      </div>
      <p class="badgeName font-body-5">{{ badge.name }}</p>
    </div>
    <div v-else class="isFalse">
      <img
        :src="'https://booktory.s3.ap-northeast-2.amazonaws.com/static/badge/' + badgeId + '.png'"
        class="badgeImage"
      />
      <p class="badgeName font-body-5">{{ badge.name }}</p>
    </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import IconStar from "@/components/icons/IconStar.vue";
import { mapActions } from "vuex";

export default {
  name: "BadgeItem",
  components: {
    IconStar,
  },
  props: {
    badgeId: {
      type: Number,
    },
    badge: {
      type: Object,
    },
  },
  methods: {
    ...mapActions("mypageStore", ["changeMainBadge"]),
    clickBadge() {
      Swal.fire({
        title: this.badge.name,
        showConfirmButton: false,
        showDenyButton: this.badge.state,
        denyButtonColor: "var(--brown)",
        denyButtonText: "대표배지 설정",
        html:
          "<div class=" +
          (this.badge.state ? "" : "isFalse") +
          "><img class=" +
          " width='200' height='200' src='https://booktory.s3.ap-northeast-2.amazonaws.com/static/badge/" +
          this.badgeId +
          ".png'><br>획득 방법: " +
          this.badge.content +
          "</div>",
      }).then((result) => {
        if (result.isDenied) {
          if (this.badge.isMain) {
            Swal.fire({
              icon: "error",
              text: "현재 설정된 대표 배지입니다",
            });
          } else {
            Swal.fire({
              showCancelButton: true,
              title: this.badge.name,
              text: "대표 배지를 변경하시겠습니까?",
              confirmButtonText: "네, 변경할래요",
              cancelButtonText: "취소",
            }).then((result) => {
              if (result.isConfirmed) {
                this.changeMainBadge(this.badgeId);
              }
            });
          }
        }
      });
    },
  },
};
</script>

<style scoped>
.badge-item-wrapper {
  position: relative;
  align-items: center;
  /* gap: 1%; */
}
.badgeImage {
  width: 8em;
}
.back {
  width: 7em;
  height: 7em;
  background: red;
}
.badgeName {
  margin: 0 auto 0.6em;
}
.main-badge {
  width: 1.6em;
  height: 1.6em;
  position: absolute;
  top: 0;
  left: 0;
  background-color: var(--brown);
  border-radius: 10em;
  padding: 0.5em;
}
</style>
