<template>
  <div class="container">
    <TopHeader :nickname="'책토리'" />
    <h4 class="title">프로필 수정</h4>
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
        <!-- 추후에 :src로 userInfoData.profile_img와 바인딩 -->
        <img class="profile-img" src="@/assets/images/profile_default.svg" alt="Profile Image" />
      </div>
    </div>
    <div class="input-div">
      <label for="nickname">닉네임</label>
      <div>
        <input
          v-model="userInfoData.nickname"
          type="text"
          id="nickname"
          placeholder="닉네임을 입력해주세요"
        />
      </div>
    </div>
    <div class="input-div">
      <label for="name">이름</label>
      <div>
        <input
          v-model="userInfoData.name"
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
        v-model="userInfoData.birth"
        value-type="date"
        placeholder="YYYY-MM-DD"
        :default-value="userInfoData.birth"
        :disabled-date="disabledAfterTodayAndBefore100Year"
        :clearable="false"
      ></date-picker>
      <!-- </div> -->
      <p class="message">생년월일을 선택해주세요</p>
    </div>
    <div class="input-div">
      <label for="phone">전화번호</label>
      <div>
        <input v-model="userInfoData.phone" type="tel" id="phone" placeholder="010-0000-0000" />
      </div>
      <p class="message">전화번호를 입력해주세요</p>
    </div>
    <button type="button" class="button-2 m-top-10" @click="clickUpdate">수정하기</button>
    <p class="text-link" @click="$router.push({ name: 'MyProfileUpdatePassword' })">
      비밀번호 변경
    </p>
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import IconCamera from "@/components/icons/IconCamera.vue";
import axios from "axios";
import Swal from "sweetalert2";

export default {
  name: "MyProfileUpdate",
  components: {
    TopHeader,
    Navbar,
    IconCamera,
  },
  data() {
    return {
      userInfoData: {
        profile_img: "",
        nickname: "책토리",
        name: "다람쥐",
        birth: new Date(2001, 0, 1),
        phone: "",
      },
    };
  },
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
    clickUpdate() {
      axios
        .post("/users/extra", {
          profile_img: this.userInfoData.profile_img,
          nickname: this.userInfoData.nickname,
          name: this.userInfoData.nickname,
          birth: this.userInfoData.email,
          phone: this.userInfoData.password,
        })
        .then((res) => {
          Swal.fire({
            icon: "success",
            title: "프로필 수정 완료",
            html: "프로필이 수정 되었습니다.<br>지금부터 바뀐 정보를 확인하실 수 있어요!",
          });
          console.log(res.data);
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "프로필 수정 실패",
            text: "프로필 수정에 실패했습니다.",
          });
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
