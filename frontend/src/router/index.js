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
  // 책토리 이용약관
  {
    path: "/register/booktory",
    name: "BooktoryTos",
    component: () => import("@/views/accounts/BooktoryTos.vue"),
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
    name: "ClubCreate",
    component: () => import("@/views/clubs/ClubCreate.vue"),
  },
  // 클럽 만들기 - 책 선택
  {
    path: "/club/create/book",
    name: "ClubCreateBook",
    component: () => import("@/views/clubs/ClubCreateBook.vue"),
  },
  // 클럽 찾기
  {
    path: "/club/search",
    name: "ClubSearch",
    component: () => import("@/views/clubs/ClubSearch.vue"),
  },
  // 클럽 찾기 검색바페이지
  {
    path: "/club/search/barpage",
    name: "ClubSearchBarPage",
    component: () => import("@/views/clubs/ClubSearchBarPage.vue"),
  },
  // 클럽 찾기 검색결과 상세페이지
  {
    path: "/club/search/barpage/item",
    name: "ClubSearchBarPageListItem",
    component: () => import("@/views/clubs/ClubSearchBarPageListItem.vue"),
  },

  {
    path: "/clubdetail",
    name: "ClubdetailHome",
    component: () => import("@/views/clubdetail/ClubdetailHome.vue"),
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
  // 비밀번호 변경
  {
    path: "/mypage/update/password",
    name: "MyProfileUpdatePassword",
    component: () => import("@/views/mypage/MyProfileUpdatePassword.vue"),
  },
  // 계정 관리
  {
    path: "/mypage/manage",
    name: "MyPageManage",
    component: () => import("@/views/mypage/MyPageManage.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
