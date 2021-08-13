export default {
  URL: "https://i5a607.p.ssafy.io/api",
  ROUTES: {
    // accounts
    login: "/users/login",
    register: "/users",
    registerExtraInfo: "/users/extra",
    findPassword: "/users/password/",
    goResetPassword: "/users/password/reset/",

    // mypage
    getUserInfo: "/users/me",
    getReadBooks: "/users/books",
    updateUserInfo: "/users",
    changePassword: "/users/password",
    deleteUser: "/users",
  },
};
