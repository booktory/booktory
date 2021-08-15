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
  // 비밀번호 찾기 - 새로운 비밀번호 입력
  {
    path: "/password/update",
    name: "FindPasswordUpdate",
    component: () => import("@/views/accounts/FindPasswordUpdate.vue"),
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
  // 클럽 문의 게시판
  {
    path: "/club/question",
    name: "ClubQuestion",
    component: () => import("@/views/clubs/ClubQuestion.vue"),
  },

  // 클럽상세 홈
  {
    path: "/clubdetail",
    name: "ClubdetailHome",
    component: () => import("@/views/clubdetail/ClubdetailHome/ClubdetailHome.vue"),
  },
  // 클럽상세 모임일정
  {
    path: "/clubdetail/meeting",
    name: "ClubdetailMeeting",
    component: () => import("@/views/clubdetail/ClubdetailMeeting/ClubdetailMeeting.vue"),
  },
  // 클럽상세 모임일정 만들기
  {
    path: "/clubdetail/meeting/create",
    name: "ClubdetailMeetingCreate",
    component: () => import("@/views/clubdetail/ClubdetailMeeting/ClubdetailMeetingCreate.vue"),
  },
  // 클럽상세 클럽관리
  {
    path: "/clubdetail/manage",
    name: "ClubdetailManage",
    component: () => import("@/views/clubdetail/ClubdetailManage/ClubdetailManage.vue"),
  },
  // 클럽상세 클럽관리 - 클럽정보 수정
  {
    path: "/clubdetail/manage/update",
    name: "ClubdetailManageUpdate",
    component: () => import("@/views/clubdetail/ClubdetailManage/ClubdetailManageUpdate.vue"),
  },
  // 클럽상세 클럽관리 - 읽을 책 추가
  {
    path: "/clubdetail/manage/create",
    name: "ClubdetailManageCreate",
    component: () => import("@/views/clubdetail/ClubdetailManage/ClubdetailManageCreate.vue"),
  },
  // 클럽상세 클럽관리 - 가입신청 관리
  {
    path: "/clubdetail/manage/user",
    name: "ClubdetailManageUser",
    component: () => import("@/views/clubdetail/ClubdetailManage/ClubdetailManageUser.vue"),
  },

  // 클럽상세 책 확인
  {
    path: "/clubdetail/book",
    name: "ClubdetailBook",
    component: () => import("@/views/clubdetail/ClubdetailBook/ClubdetailBook.vue"),
  },

  // 클럽상세 책 추가
  {
    path: "/clubdetail/book/add",
    name: "ClubdetailBookAdd",
    component: () => import("@/views/clubdetail/ClubdetailBook/ClubdetailBookAdd.vue"),
  },

  // 클럽상세 담벼락
  {
    path: "/clubdetail/board",
    name: "ClubdetailBoard",
    component: () => import("@/views/clubdetail/ClubdetailBoard/ClubdetailBoard.vue"),
  },

  // 내 서재
  {
    path: "/mybook",
    name: "MyBook",
    component: () => import("@/views/mybook/MyBook.vue"),
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
  // 회원 탈퇴
  {
    path: "/mypage/delete",
    name: "DeleteUser",
    component: () => import("@/views/mypage/DeleteUser.vue"),
  },
  // 회원 탈퇴 시 비밀번호 확인
  {
    path: "/mypage/delete/confirm",
    name: "DeleteUserConfirm",
    component: () => import("@/views/mypage/DeleteUserConfirm.vue"),
  },

  // 알람
  {
    path: "/notification",
    name: "Notification",
    component: () => import("@/views/notification/Notification.vue"),
  },
  // 미팅
  {
    path: "/meeting",
    name: "Meeting",
    component: () => import("@/views/meeting/MeetingRoom.vue"),
  },

  //같이 읽기
  {
    path: "/rwm",
    name: "RwmMain",
    component: () => import("@/views/rwm/RwmMain.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
