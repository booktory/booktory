<template>
  <div class="home">
    <div class="container">
      <div class="title">
        <p class="main_title color-main-orange">책토리와 함께하는 <mark>독서모임</mark></p>
        <p class="sub_title">책을 읽고 사람들과 함께 생각을 공유해요.</p>
      </div>
      <img alt="Vue logo" src="../assets/booktori.png" />
      <button type="button" class="btn" @click="$router.push({ path: '/email' })">시작하기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
};
</script>

<style>
:root {
  --naver: #00b900;
  --main-brown: #904402;
  --main-light-orange: #fdcc97;
  --main-orange: #ff7f00;
  --main-light-brown: #bf5f0e;
  --text-black: #212121;
  --text-white: #ffffff;
  --text-grey: #8a8894;
  --text-light-grey: #d6d6d9;
  --text-disabled: #c5c0db;
  --bg-white: #ffffff;
  --bg-beige: #fffbf5;
  --bg-disabled: #f4f3fe;
  --bg-alert: #f1ece5;
  --bg-blur: rgba(0, 0, 0, 0.2);
  --bg-light-blue-2: #f2f5fa;
  --bg-card: #f3f3f3;
  --stroke-focused: #f845fc;
  --stroke-gray: #bdbcdb;
  --stroke-brown: #c39a76;
  --btn-deep-blue: #687ef2;
  --btn-light-blue: #a4c0f3;
  --btn-blue: #6695e5;
  --btn-white: #ffffff;
  --btn-disable: #eeeeee;
  --btn-kakao: #ffe812;
}

body {
  display: flex;
  justify-content: center;
  padding: 0;
  margin: 0;
  background-color: #fffbf5;
}

/* Mobile (Default) */
.container {
  width: 100vw;
}
/* Tablet */
@media (min-width: 768px) {
  .container {
    width: 70vw;
  }
}
/* Desktop */
@media (min-width: 1024px) {
  .container {
    width: 50vw;
    max-width: 768px;
  }
}

.title {
  width: 89.3%;
  height: 59px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: stretch;
  gap: 12px;
  margin: 100px 5.3% 41px 5.3%;
  padding: 0;
}

.main_title {
  margin: 0;
  height: 29px;
  font-family: NotoSansKR;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: #212121;
}

.sub_title {
  margin: 0;
  height: 18px;
  font-family: NotoSansKR;
  font-size: 15px;
  text-align: center;
  color: #212121;
}

.btn {
  width: 213px;
  height: 48px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 60px auto 12px;
  padding: 20px 32px;
  border-radius: 50px;
  border: 0;
  background-color: #a4c0f3;
  flex-grow: 0;
  font-family: NotoSansKR;
  font-size: 20px;
  line-height: 1.2;
  letter-spacing: 7px;
  text-align: center;
  color: #ffffff;
  cursor: pointer;
}

.color-main-orange mark {
  background: none;
  color: var(--main-orange);
}
</style>
