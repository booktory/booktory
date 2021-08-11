<template>
  <div class="badge-item-wrapper" @click="clickBadge">
    <div v-if="state" class="isTrue">
      <img src="@/assets/images/profile_default.svg" class="badgeImage" />
      <div v-if="isMain" class="main-badge">
        <icon-base :iconColor="'var(--white)'"><icon-star /></icon-base>
      </div>
      <p class="badgeName font-body-5">{{ name }}</p>
    </div>
    <div v-else class="isFalse">
      <img src="@/assets/images/profile_default.svg" class="badgeImage" />
      <p class="badgeName font-body-5 isFalse">{{ name }}</p>
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
    state: {
      type: Boolean,
    },
    name: {
      type: String,
    },
    content: {
      type: String,
    },
    isMain: {
      type: Boolean,
    },
  },
  methods: {
    ...mapActions("mypageStore", ["changeMainBadge"]),
    clickBadge() {
      Swal.fire({
        title: this.name,
        showConfirmButton: false,
        showDenyButton: this.state,
        denyButtonColor: "var(--brown)",
        denyButtonText: "대표배지 설정",
        html: "획득 방법: " + this.content,
      }).then((result) => {
        if (result.isDenied) {
          if (this.isMain) {
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
  gap: 1%;
}
.isFalse {
  color: var(--light-grey);
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
