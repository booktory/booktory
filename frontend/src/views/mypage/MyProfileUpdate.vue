<template>
  <div v-if="userInfo" class="container">
    <div>
      <TopHeader :nickname="'책토리'" />
      <h4 class="title">프로필 수정</h4>
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
          <div class="icon profile-btn" @click="clickProfileBtn">
            <icon-base><icon-camera /></icon-base>
          </div>
          <div class="profile-img">
            <img
              :src="
                userInfo.profileImg
                  ? userInfo.profileImg
                  : 'https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/profile.png'
              "
              alt="Profile Image"
            />
          </div>
        </div>
      </div>
      <div class="input-div">
        <label for="nickname">닉네임</label>
        <div>
          <input
            v-model="userInfo.nickname"
            v-bind:class="{
              error: error.nickname,
              complete: !error.nickname && userInfo.nickname.length !== 0,
            }"
            type="text"
            id="nickname"
            maxlength="10"
            placeholder="닉네임을 입력해주세요"
            autocapitalize="none"
            autocorrect="none"
            required
          />
        </div>
        <p v-if="error.nickname" class="message">{{ error.nickname }}</p>
      </div>
      <div class="input-div">
        <label for="name">이름</label>
        <div>
          <input
            v-model="userInfo.name"
            v-bind:class="{
              error: error.name,
              complete: !error.name,
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
          v-model="userInfo.birth"
          v-bind:class="{
            error: error.birth,
            complete: !error.birth,
          }"
          value-type="format"
          placeholder="생년월일을 선택해주세요"
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
            v-model="userInfo.phone"
            v-bind:class="{
              error: error.phone,
              complete: !error.phone,
            }"
            type="tel"
            id="phone"
            maxlength="11"
            placeholder="전화번호를 입력해주세요"
            required
            @keyup.enter="clickUpdate"
          />
        </div>
        <p v-if="error.phone" class="message">{{ error.phone }}</p>
      </div>
      <button type="button" class="button-2 m-top-10" @click="clickUpdate">수정하기</button>
      <p class="text-link" @click="$router.push({ name: 'MyProfileUpdatePassword' })">
        비밀번호 변경
      </p>
      <Navbar :selected="'mypage'" class="footer" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import IconCamera from "@/components/icons/IconCamera.vue";
import axios from "axios";
import SERVER from "@/api/api";
import Swal from "sweetalert2";

export default {
  name: "MyProfileUpdate",
  components: {
    TopHeader,
    Navbar,
    IconCamera,
  },
  computed: {
    ...mapState("mypageStore", ["userInfo"]),
  },
  data() {
    return {
      error: {
        nickname: false,
        name: false,
        birth: false,
        phone: false,
      },
    };
  },
  watch: {
    userInfo: {
      deep: true,
      handler() {
        this.checkNicknameForm();
        this.checkNameForm();
        this.checkBirthForm();
        this.checkPhoneForm();
      },
    },
  },
  methods: {
    ...mapActions("mypageStore", ["findUserInfo", "updateUserInfo"]),
    // 프로필 수정하기 버튼
    clickUpdate() {
      if (this.checkVaild()) {
        this.updateUserInfo(this.userInfo);
      } else {
        Swal.fire({
          icon: "warning",
          html: "유효하지 않은 값은 넣을 수 없어요!<br>입력한 값을 다시 확인해주세요.",
          showConfirmButton: false,
          timer: 1500,
        });
      }
    },
    clickProfileBtn() {
      Swal.fire({
        html: "<h5 style='margin: 0;'>프로필 사진 설정</h5>",
        confirmButtonText: "사진 업로드",
        showDenyButton: true,
        denyButtonColor: "var(--light-brown)",
        denyButtonText: "기본 이미지로 변경",
        showCancelButton: true,
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          this.clickProfileImg();
        } else if (result.isDenied) {
          this.userInfo.profileImg = "";
        }
      });
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
        .post(SERVER.URL + SERVER.ROUTES.uploadProfileImg, form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          this.userInfo.profileImg = data;
        })
        .catch((err) => console.log(err));
    },
    // 생년월일 입력 제한
    disabledAfterTodayAndBefore100Year(date) {
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      return date > today || date < new Date(today.getTime() - 100 * 365 * 24 * 3600 * 1000);
    },
    // 유효성 검사 통과 시만 수정 가능
    checkVaild() {
      if (this.userInfo.nickname.length > 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        return isSubmit;
      }
    },
    // 닉네임 유효성 검사
    checkNicknameForm() {
      if (this.userInfo.nickname.length > 0 && this.userInfo.nickname.length < 2) {
        this.error.nickname = "닉네임이 너무 짧아요! 2자 이상 입력해주세요";
      } else if (this.userInfo.nickname.length > 10) {
        this.error.nickname = "닉네임이 너무 길어요! 10자 이하로 입력해주세요";
      } else this.error.nickname = false;
    },
    // 이름 유효성 검사
    checkNameForm() {
      if (this.userInfo.name && this.userInfo.name.length > 0 && this.userInfo.name.length > 20) {
        this.error.name = "이름이 너무 길어요! 20자 이하로 입력해주세요";
      } else if (this.userInfo.name.length >= 1 && !this.validName(this.userInfo.name)) {
        this.error.name = "이름은 한글과 영문으로만 입력 가능 해요";
      } else this.error.name = false;
    },
    validName(name) {
      // eslint-disable-next-line
      var reg = /^[가-힣a-zA-Z\s]+$/;
      return reg.test(name);
    },
    // 생년월일 유효성 검사
    checkBirthForm() {
      if (
        this.userInfo.birth &&
        this.userInfo.birth.length > 0 &&
        !this.validBirth(this.userInfo.birth)
      ) {
        this.error.birth = "생년월일을 올바른 형식으로 입력해주세요";
      } else this.error.birth = false;
    },
    validBirth(birth) {
      // eslint-disable-next-line
      var reg = /^\d{4}-\d{2}-\d{2}$/;
      return reg.test(birth);
    },
    // 전화번호 유효성 검사
    checkPhoneForm() {
      if (this.userInfo.phone && this.userInfo.phone.length > 0 && this.userInfo.phone.length < 9) {
        this.error.phone = "전화번호가 너무 짧아요";
      } else if (this.userInfo.phone.length > 11) {
        this.error.phone = "전화번호가 너무 길어요";
      } else if (this.userInfo.phone.length >= 9 && !this.validPhone(this.userInfo.phone)) {
        this.error.phone = "전화번호를 숫자로만 입력해주세요";
      } else this.error.phone = false;
    },
    validPhone(phone) {
      // eslint-disable-next-line
      var reg = /^[0-9]*$/;
      return reg.test(phone);
    },
  },
  created() {
    this.findUserInfo();
  },
};
</script>

<style scoped></style>
