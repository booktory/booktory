<template>
  <div class="container m-top">
    <h4 class="title">추가정보 입력</h4>
    <p class="sub-title">프로필을 입력해주세요</p>
    <div class="m-top-10">
      <div class="profile-img-div">
        <input
          class="hidden-item"
          ref="image"
          type="file"
          accept="image/*"
          @change="uploadImage()"
        />
        <div class="icon profile-btn" @click="clickProfile">
          <icon-base><icon-camera /></icon-base>
        </div>
        <img class="profile-img" src="@/assets/images/profile_default.svg" alt="Profile Image" />
      </div>
    </div>
    <div class="input-div">
      <label for="name">이름</label>
      <div>
        <input
          v-model="extrainfoData.name"
          type="text"
          id="name"
          placeholder="이름을 입력해주세요"
        />
      </div>
    </div>
    <div class="input-div">
      <label for="birth">생년월일</label>
      <date-picker
        id="birth"
        v-model="extrainfoData.birth"
        format="YYYY-MM-DD"
        type="date"
        placeholder="YYYY-MM-DD"
        :default-value="defaultDate"
        :disabled-date="disabledAfterTodayAndBefore100Year"
        :clearable="false"
      ></date-picker>
      <!-- </div> -->
      <p class="message">생년월일을 선택해주세요</p>
    </div>
    <div class="input-div">
      <label for="phone">전화번호</label>
      <div>
        <input v-model="extrainfoData.phone" type="tel" id="phone" placeholder="010-0000-0000" />
      </div>
      <p class="message">전화번호를 입력해주세요</p>
    </div>
    <button type="button" class="button-2 white-bg m-top-10" @click="clickSkip">넘어가기</button>
    <button type="button" class="button-2" @click="clickExtraInfo">확인</button>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import Swal from "sweetalert2";
import IconCamera from "@/components/icons/IconCamera.vue";

export default {
  name: "Register",
  components: { IconCamera },
  data() {
    return {
      extrainfoData: {
        profile_img: "",
        name: "",
        birth: "",
        phone: "",
      },
      defaultDate: new Date(new Date().getTime() - 20 * 365 * 24 * 3600 * 1000),
    };
  },
  computed: {},
  methods: {
    clickProfile() {
      this.$refs["image"].click();
    },
    uploadImage() {
      let form = new FormData();
      let image = this.$refs["image"].files[0];

      form.append("image", image);

      console.log(image);

      // axios
      //   .post("/upload", form, {
      //     header: { "Content-Type": "multipart/form-data" },
      //   })
      //   .then(({ data }) => {
      //     this.images = data;
      //   })
      //   .catch((err) => console.log(err));
    },
    disabledAfterTodayAndBefore100Year(date) {
      const today = new Date();
      today.setHours(0, 0, 0, 0);

      return date > today || date < new Date(today.getTime() - 100 * 365 * 24 * 3600 * 1000);
    },
    clickSkip() {
      Swal.fire({
        icon: "success",
        title: "회원가입 완료",
        html: "회원가입이 완료 되었습니다.<br>지금부터 책토리를 이용하실 수 있어요!",
      });
      router.push({ name: "Login" });
    },
    clickExtraInfo() {
      axios
        .post("/users/extra", {
          profile_img: this.extrainfoData.profile_img,
          name: this.extrainfoData.nickname,
          birth: this.extrainfoData.email,
          phone: this.extrainfoData.password,
        })
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "회원가입 완료",
            html: "회원가입이 완료 되었습니다.<br>지금부터 책토리를 이용하실 수 있어요!",
          });
          console.log(res.data);
          router.push({ name: "Login" });
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "추가정보 입력 실패",
            text: "추가정보 입력에 실패했습니다.",
          });
          console.log(error);
        });
    },
  },
  mounted() {},
};
</script>

<style scoped></style>
