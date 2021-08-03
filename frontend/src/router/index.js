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

  // 비밀번호 찾기
  {
    path: "/password",
    name: "FindPassword",
    component: () => import("@/views/accounts/FindPassword.vue"),
  },

  // 홈 화면(내 클럽 보기)
  {
    path: "/club",
    name: "MyClub",
    component: () => import("@/views/clubs/MyClub.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
