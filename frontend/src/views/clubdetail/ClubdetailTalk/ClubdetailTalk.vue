<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <h4 class="title">담벼락</h4>
          <p class="font-body-6">
            클럽 멤버원들과 자유롭게 대화하며 의견을 나누는 공간입니다. 올바른 담벼락 문화를 위해
            상대방을 배려하는 바르고 고운말을 사용합시다.
          </p>
          <div class="talk-card">
            <div class="article">
              <div class="article-head"></div>
              <div class="article-body"></div>
            </div>
          </div>
          <div class="talk-input">
            <input type="text" />
            <button>보내기</button>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailTalk",
  components: {
    Navbar,
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailTalk.scss";
</style>
