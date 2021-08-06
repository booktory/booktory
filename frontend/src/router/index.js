import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home.vue"),
  },

  // 로그인
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/accounts/Login.vue"),
  },
  // 회원가입
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/accounts/Register.vue"),
  },
  // 회원가입 추가 정보
  {
    path: "/register/extrainfo",
    name: "ExtraInfo",
    component: () => import("@/views/accounts/ExtraInfo.vue"),
  },
  // 비밀번호 찾기
  {
    path: "/password",
    name: "FindPassword",
    component: () => import("@/views/accounts/FindPassword.vue"),
  },

  // 홈 화면(내 클럽 보기)
  {
    path: "/club",
    name: "ClubHome",
    component: () => import("@/views/clubs/ClubHome.vue"),
  },
  // 클럽 만들기
  {
    path: "/club/create",
    name: "CreateClub",
    component: () => import("@/views/clubs/CreateClub.vue"),
  },

  // 마이페이지
  {
    path: "/mypage",
    name: "MyPage",
    component: () => import("@/views/mypage/MyPage.vue"),
  },
  // 프로필 수정
  {
    path: "/mypage/update",
    name: "MyProfileUpdate",
    component: () => import("@/views/mypage/MyProfileUpdate.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
