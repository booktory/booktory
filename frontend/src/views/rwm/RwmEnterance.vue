<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">읽을 책 입력</h4>
      <span class="sub-title">가져오신 책 이름을 입력해주세요</span>
      <div class="input-div m-top-10">
        <div>
          <input
            v-model.trim="rwmEnter.bookTitle"
            type="text"
            id="title"
            placeholder="책 이름을 입력해주세요"
            @keyup.enter="clickRwmEnter"
          />
        </div>
      </div>
      <button type="button" class="button-2 m-top-10" @click="clickRwmEnter">입장하기</button>
      <Navbar :selected="'rwm'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import { mapActions } from "vuex";
import Swal from "sweetalert2";

export default {
  name: "RwmEnterane",
  components: {
    TopHeader,
    Navbar,
  },
  data() {
    return {
      rwmEnter: {
        id: this.$route.query.id,
        bookTitle: "",
      },
    };
  },
  methods: {
    ...mapActions("rwmStore", ["enterRwmRoom"]),
    clickRwmEnter() {
      if (this.rwmEnter.bookTitle.length > 0) {
        this.enterRwmRoom(this.rwmEnter);
      } else {
        Swal.fire({
          icon: "warning",
          title: "읽을 책을 입력해주세요",
        });
      }
    },
  },
  created() {},
};
</script>

<style scoped></style>
