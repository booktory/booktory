export default {
  URL: "http://localhost:8080/api",
  ROUTES: {
    // accounts
    login: "/users/login",
    register: "/users",
    registerExtraInfo: "/users/extra",
    findPassword: "/users/password/",
    goResetPassword: "/users/password/reset/",
    logout: "/users/logout",

    // mypage
    getUserInfo: "/users/me",
    getReadBooks: "/users/books",
    updateUserInfo: "/users",
    changePassword: "/users/password",
    deleteUser: "/users",

    // notification
    registerFCMToken: "/notification",
  },
};
