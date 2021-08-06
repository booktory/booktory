<template>
  <div class="badge-item-wrapper" @click="clickBadge">
    <div v-if="state" class="isTrue">
      <img src="@/assets/images/profile_default.svg" class="badgeImage" />
      <div v-if="this.mainBadge == this.name" class="main-badge">
        <icon-base :iconColor="'var(--white)'"><icon-star /></icon-base>
      </div>
      <p class="badgeName font-body-5">{{ index }}{{ name }}</p>
    </div>
    <div v-else class="isFalse">
      <img src="@/assets/images/profile_default.svg" class="badgeImage" />
    </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import IconStar from "@/components/icons/IconStar.vue";

export default {
  name: "BadgeItem",
  components: {
    IconStar,
  },
  props: {
    index: {
      type: Number,
    },
    state: {
      type: Boolean,
    },
    name: {
      type: String,
    },
    mainBadge: {
      type: String,
    },
  },
  methods: {
    clickBadge: function() {
      Swal.fire({
        title: this.name,
        showConfirmButton: false,
        showDenyButton: this.state,
        denyButtonColor: "var(--brown)",
        denyButtonText: "대표배지 설정",
        html:
          "힘차게 보는 내는 같은 오아이스도 아름다우냐? 목숨이 청춘의 얼음에 천지는 밝은 가슴에 군영과 미인을 것이다. 위하여서 새 이성은 투명하되 힘차게 인간은 얼음 눈에 끓는다.",
      }).then((result) => {
        if (result.isDenied) {
          if (this.mainBadge == this.name) {
            Swal.fire({
              icon: "error",
              text: "현재 설정된 대표 배지입니다",
            });
          } else {
            Swal.fire({
              showCancelButton: true,
              title: this.name,
              text: "대표 배지를 변경하시겠습니까?",
              confirmButtonText: "네, 변경할래요",
              cancelButtonText: "취소",
            });
          }
        }
      });
    },
    changeMainBadge: function() {
      if (this.mainBadge == this.name) {
        Swal.fire({
          icon: "error",
          text: "현재 설정된 대표 배지입니다",
        });
      } else {
        Swal.fire({
          showCancelButton: true,
          title: this.name,
          text: "대표 배지를 변경하시겠습니까?",
          confirmButtonText: "네, 변경할래요",
          cancelButtonText: "취소",
        });
      }
    },
  },
};
</script>

<style scoped>
.badge-item-wrapper {
  position: relative;
  align-items: center;
  gap: 1%;
}
.isFalse img {
  opacity: 30%;
}
.badgeImage {
  width: 8em;
  border-radius: 10em;
}
.back {
  width: 7em;
  height: 7em;
  background: red;
}
.badgeName {
  margin: 0.3em auto 0.6em;
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
