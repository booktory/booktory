<template>
  <div class="container">
    <TopHeader :nickname="nickname" />
    <h4 class="title">책토리 탈퇴하기</h4>
    <div class="content-div m-top-10">
      <h4>{{ nickname }}님, 잠깐만요!</h4>
      <p>
        정말 탈퇴하시겠어요? 탈퇴 후엔 그동안 책토리에서 쌓은 회원님의 소중한 기록들이 삭제되고
        복구할 수 없어요😢
      </p>
      <p>
        혹시 책토리 홈페이지 이용에 불편한 점이 있으셨다면 메일을 통해 저희에게 알려주세요. 확인 후
        빠르게 피드백 할 수 있도록 노력하겠습니다!
        <a
          href="mailto:booktory607@gmail.com?subject=책토리 Feedback&body=책토리 홈페이지를 이용하면서 불편했던 점을 이메일로 보내주시면 확인 후 빠르게 피드백 하도록 하겠습니다!%0D%0A내용을 자세하게 적어주시면 더 좋은 답변을 드릴 수 있어요:)%0D%0A%0D%0A그리고 달라질 책토리를 기대하면서 회원 탈퇴는 잠시 미뤄두시는 게 어떨까요? 😊"
          >📝의견 보내러 가기</a
        >
      </p>
      <p>
        정말로 탈퇴하고 싶으시다면 탈퇴하기 버튼을 눌러주세요.
      </p>
    </div>
    <button type="button" class="button-2 m-top-10 white-bg" @click="$router.go(-1)">
      다시 이용하기
    </button>
    <button type="button" class="button-2" @click="$router.push({ name: 'DeleteUserConfirm' })">
      탈퇴하기
    </button>
    <Navbar :selected="'mypage'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";

export default {
  components: {
    TopHeader,
    Navbar,
  },
  name: "DeleteUser",
  data() {
    return {
      nickname: "책토리",
    };
  },
  methods: {},
};
</script>

<style scoped>
.content-div {
  width: auto;
  margin: 7% 8% 0;
}
.content-div h4 {
  color: var(--orange);
  margin-bottom: 7%;
}
.content-div p,
a {
  font-size: 1.2rem;
  line-height: 1.8rem;
  margin: 5% 0;
}
.content-div a {
  color: currentColor;
  font-weight: bold;
}
.content-div * {
  text-align: justify;
  margin: 0;
}
</style>
