<template>
  <div class="navbar-container">
    <div class="item">
      <p class="sub-item selected icon">
        <img src="@/assets/icons/home.svg" alt="home">
      </p>
      <p class="sub-item selected font-body-4">
        홈
      </p>
    </div>
    <div class="item">
      <p class="sub-item icon">
        <img src="@/assets/icons/rwm.svg" alt="rwm">
      </p>
      <p class="sub-item font-body-4">
        같이읽기
      </p>
    </div>
    <div class="item">
      <p class="sub-item icon">
        <img src="@/assets/icons/mybook.svg" alt="mybook">
      </p>
      <p class="sub-item font-body-4">
        내 서재
      </p>
    </div>
    <div class="item">
      <p class="sub-item icon">
        <img src="@/assets/icons/mypage.svg" alt="mypage">
      </p>
      <p class="sub-item font-body-4">
        마이페이지
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Navbar",
}
</script>

<style scoped>
* {
  background-color: #bbbbbb;
}

.navbar-container {  
  display: grid;
  padding: 3% 3% 3%;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
  align-items: start;
}

.sub-item {
  margin: auto;
  padding: 5px;
}

.selected {
  color: #6695e5;
}

</style>