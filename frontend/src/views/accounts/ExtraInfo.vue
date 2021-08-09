<template>
  <div class="container m-top">
    <h4 class="title">추가정보 입력</h4>
    <p class="sub-title">추가정보를 입력해주세요</p>
    <div class="m-top-5">
      <div class="profile-img-div">
        <input
          class="hidden-item"
          ref="image"
          id="profileImg"
          type="file"
          accept="image/*"
          @change="uploadImage()"
        />
        <div class="icon profile-btn" @click="clickProfileImg">
          <icon-base><icon-camera /></icon-base>
        </div>
        <img class="profile-img" src="@/assets/images/profile_default.svg" alt="Profile Image" />
      </div>
    </div>
    <div class="input-div">
      <label for="name">이름</label>
      <div>
        <input
          v-model="extraData.name"
          v-bind:class="{
            error: error.name,
            complete: !error.name && extraData.name.length !== 0,
          }"
          type="text"
          id="name"
          maxlength="20"
          placeholder="이름을 입력해주세요"
          autocapitalize="none"
          autocorrect="none"
          required
        />
      </div>
      <p v-if="error.name" class="message">{{ error.name }}</p>
    </div>
    <div class="input-div">
      <label for="birth">생년월일</label>
      <date-picker
        id="birth"
        v-model="extraData.birth"
        v-bind:class="{
          error: error.birth,
          complete: !error.birth && extraData.birth.length !== 0,
        }"
        format="YYYY-MM-DD"
        type="date"
        placeholder="생년월일을 선택해주세요"
        :default-value="defaultDate"
        :disabled-date="disabledAfterTodayAndBefore100Year"
        :clearable="false"
        required
      ></date-picker>
      <p v-if="error.birth" class="message">{{ error.birth }}</p>
    </div>
    <div class="input-div">
      <label for="phone">전화번호</label>
      <div>
        <input
          v-model="extraData.phone"
          v-bind:class="{
            error: error.phone,
            complete: !error.phone && extraData.phone.length !== 0,
          }"
          type="tel"
          id="phone"
          maxlength="11"
          placeholder="전화번호를 입력해주세요"
          required
        />
      </div>
      <p v-if="error.phone" class="message">{{ error.phone }}</p>
    </div>
    <button type="button" class="button-2 white-bg m-top-10" @click="clickSkip">넘어가기</button>
    <button type="button" class="button-2" @click="clickExtraInfo">추가정보 입력</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import router from "@/router";
import axios from "axios";
import Swal from "sweetalert2";
import IconCamera from "@/components/icons/IconCamera.vue";

export default {
  name: "ExtraInfo",
  components: { IconCamera },
  data() {
    return {
      extraData: {
        email: "wonji616@gmail.com",
        profileImg: "",
        name: "",
        birth: "",
        phone: "",
      },
      defaultDate: new Date(new Date().getTime() - 20 * 365 * 24 * 3600 * 1000),
      error: {
        name: false,
        birth: false,
        phone: false,
      },
    };
  },
  watch: {
    extraData: {
      deep: true,
      handler() {
        this.checkNameForm();
        this.checkBirthForm();
        this.checkPhoneForm();
      },
    },
  },
  methods: {
    ...mapActions("accountStore", ["registerExtraInfo"]),
    // 넘어가기 버튼 클릭
    clickSkip() {
      Swal.fire({
        icon: "success",
        title: "회원가입 완료",
        html: "회원가입이 완료 되었습니다.<br>지금부터 책토리를 이용하실 수 있어요!",
      });
      router.push({ name: "Login" });
    },
    // 추가정보 입력 버튼 클릭
    clickExtraInfo() {
      // 유효성 검사 통과 시 작동
      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      if (isSubmit) {
        this.registerExtraInfo(this.extraData);
      }
    },
    // 프로필 사진 버튼 클릭
    clickProfileImg() {
      this.$refs["image"].click();
    },
    // 프로필 사진 업로드
    uploadImage() {
      let form = new FormData();
      let image = this.$refs["image"].files[0];
      form.append("file", image);
      axios
        .post("/api/files/user", form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          this.extraData.profileImg = data;
          console.log(this.extraData.profileImg);
        })
        .catch((err) => console.log(err));
    },
    // 생년월일 입력 제한
    disabledAfterTodayAndBefore100Year(date) {
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      return date > today || date < new Date(today.getTime() - 100 * 365 * 24 * 3600 * 1000);
    },
    checkNameForm() {
      if (this.extraData.name.length > 0 && this.extraData.name.length > 20) {
        this.error.name = "이름이 너무 길어요! 20자 이하로 입력해주세요";
      } else if (this.extraData.name.length >= 1 && !this.validName(this.extraData.name)) {
        this.error.name = "이름은 한글과 영문으로만 입력 가능 해요";
      } else this.error.name = false;
    },
    validName(name) {
      // eslint-disable-next-line
      var reg = /^[가-힣a-zA-Z\s]+$/;
      return reg.test(name);
    },
    checkBirthForm() {
      if (this.extraData.birth.length > 0 && !this.validBirth(this.extraData.birth)) {
        this.error.birth = "생년월일을 올바른 형식으로 입력해주세요";
      } else this.error.birth = false;
    },
    validBirth(birth) {
      // eslint-disable-next-line
      var reg = /^\d{4}-\d{2}-\d{2}$/;
      return reg.test(birth);
    },
    checkPhoneForm() {
      if (this.extraData.phone.length > 0 && this.extraData.phone.length < 9) {
        this.error.phone = "전화번호가 너무 짧아요";
      } else if (this.extraData.phone.length > 11) {
        this.error.phone = "전화번호가 너무 길어요";
      } else if (this.extraData.phone.length >= 9 && !this.validPhone(this.extraData.phone)) {
        this.error.phone = "전화번호를 숫자로만 입력해주세요";
      } else this.error.phone = false;
    },
    validPhone(phone) {
      // eslint-disable-next-line
      var reg = /^[0-9]*$/;
      return reg.test(phone);
    },
  },
  mounted() {},
};
</script>

<style scoped></style>
