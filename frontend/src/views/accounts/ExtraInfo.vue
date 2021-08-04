<template>
  <div class="register">
    <div class="container">
      <div class="navbar">
        <img
          class="icon"
          alt="arrow-left"
          src="@/assets/icons/arrow-left.svg"
          @click="$router.go(-1)"
        />
      </div>
      <h4 class="title">추가정보 입력</h4>
      <p class="sub-title">프로필을 입력해주세요</p>
      <div class="m-top-10">
        <div class="profile-img-div">
          <input
            class="hidden-input"
            ref="image"
            type="file"
            accept="image/*"
            @change="uploadImage()"
          />
          <img
            class="icon profile-btn"
            src="@/assets/icons/camera.svg"
            alt="camera"
            @click="clickProfile"
          />
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
        <div>
          <input
            v-model="extrainfoData.birth"
            type="date"
            id="birth"
            data-placeholder="생년월일을
          선택해주세요"
            required
          />
        </div>
        <p class="message">생년월일 형식으로 입력해주세요</p>
      </div>
      <div class="input-div">
        <label for="phone">전화번호</label>
        <div>
          <input
            v-model="extrainfoData.phone"
            type="tel"
            id="phone"
            placeholder="전화번호를 입력해주세요"
          />
        </div>
        <p class="message">전화번호 형식으로 입력해주세요</p>
      </div>
      <button type="button" class="button-2 m-top-10" @click="clickSkip">넘어가기</button>
      <button type="button" class="button-2" @click="clickExtraInfo">확인</button>
      <p class="text-link">이용약관 보기</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";

export default {
  name: "Register",
  data() {
    return {
      extrainfoData: {
        profile_img: "",
        name: "",
        birth: "",
        phone: "",
      },
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
    clickSkip() {
      Swal.fire({
        icon: "success",
        title: "회원가입 완료",
        html: "회원가입이 완료 되었습니다.<br>지금부터 책토리를 이용하실 수 있어요!",
      });
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

<style scoped>
#birth::before {
  content: attr(data-placeholder);
}
#birth:focus::before,
#birth:valid::before {
  display: none;
}
</style>
