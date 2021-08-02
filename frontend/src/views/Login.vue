<template>
  <div class="login">
    <div class="container">
      <div class="navbar">
        <router-link to="/">
          <img class="icon" src="../assets/icons/arrow-left.png" alt="" />
        </router-link>
      </div>
      <div class="title">
        <p class="main_title">로그인</p>
        <p class="sub_title">비밀번호를 입력해주세요</p>
      </div>
      <div class="password_outer">
        <input type="password" id="password" placeholder="비밀번호를 입력해주세요" />
      </div>
      <p class="message">8자 이상 입력해주세요</p>
      <button type="button" class="btn">확인</button>
      <router-link to="/find">
        <p class="find_password">비밀번호 찾기</p>
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.navbar {
  text-align: left;
}

.icon {
  width: 24px;
  height: 24px;
  margin: 20px 0 0 20px;
}

.title {
  width: 89.3%;
  height: 59px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: stretch;
  gap: 12px;
  margin: 56px 5.3% 41px 5.3%;
  padding: 0;
}

.main_title {
  margin: 0;
  height: 29px;
  font-family: NotoSansKR;
  font-size: 24px;
  font-weight: bold;
  line-height: 1.2;
  text-align: center;
  color: #212121;
}

.sub_title {
  margin: 0;
  height: 18px;
  font-family: NotoSansKR;
  font-size: 15px;
  line-height: 1.2;
  text-align: center;
  color: #212121;
}

.password_outer {
  display: inline-block;
  text-align: left;
  width: 84%;
  height: 40px;
  border-radius: 24px;
  box-shadow: 0 4px 8px 0 rgba(142, 141, 208, 0.16);
  background-color: #ffffff;
}

#password {
  width: 88%;
  padding: 13px 6% 12px;
  border: 0;
  background: transparent;
}

#password::placeholder {
  font-family: NotoSansKR;
  font-size: 15px;
  line-height: 1.2;
  color: #d6d6d9;
}

.message {
  width: 25.6%;
  height: 12px;
  margin-left: 13.3%;
  font-family: NotoSansKR;
  font-size: 10px;
  line-height: 1.2;
  text-align: left;
  color: #8a8894;
}

.btn {
  width: 213px;
  height: 48px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 200px auto 12px;
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
}

.find_password {
  height: 18px;
  font-family: NotoSansKR;
  font-size: 15px;
  line-height: 1.2;
  text-align: center;
  color: #8a8894;
}

a {
  text-decoration: none;
}
</style>
