export default {
  URL: "https://localhost:8080",
  ROUTES: {
    // accounts
    login: "/users/login",
    register: "/users",
    registerExtraInfo: "/users/extra",
    findPassword: "/users/password/",
    goResetPassword: "/users/password/reset/",
    deleteUser: "/users",

    // mypage
    getUserInfo: "/users/me",
    getReadBooks: "/users/books",
    updateUserInfo: "/users",
    changePassword: "/users/password",
  },
};
