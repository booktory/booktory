<template>
  <div class="container">
    <div class="item item-1">
      <p class="sub-item selected">
        <img src="@/assets/icons/home.svg" alt="home">
      </p>
      <p class="sub-item selected">
        홈
      </p>
    </div>
    <div class="item item-2">
      <p class="sub-item icon">
        <img src="@/assets/icons/rwm.svg" alt="rwm">
      </p>
      <p class="sub-item">
        같이읽기
      </p>
    </div>
    <div class="item item-3">
      <p class="sub-item icon">
        <img src="@/assets/icons/mybook.svg" alt="mybook">
      </p>
      <p class="sub-item">
        내 서재
      </p>
    </div>
    <div class="item item-4">
      <p class="sub-item icon">
        <img src="@/assets/icons/mypage.svg" alt="mypage">
      </p>
      <p class="sub-item">
        마이페이지
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'navbar'
}
</script>

<style scoped>

.container {  
  background-color: #bbbbbb;
  display: grid;
  padding: 0 3%;
  grid-template-columns: repeat(4, auto [col-start]);
  grid-template-rows: auto;
  justify-items: center;
}

.selected {
  color: #6695e5;
}

</style>