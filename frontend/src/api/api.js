export default {
  URL: "http://localhost:8080/api",
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

    // clubs
    createClub: "/clubs",
    getClubInfo: "/clubs/",
    updateClubInfo: "/clubs/",
    deleteClub: "/clubs/",
    getJoinedClub: "/clubs/list",

    // book-club
    createBook: "/bookclubs",
    deleteBook: "/bookclubs/",
    createMeeting: "/bookclubs",
    cancleMeeting: "/bookclubs/",
    getBookClubInfo: "/bookclubs/",
    getBookClubList: "/bookclubs/list/",

    // file-upload
    uploadProfileImg: "/files/user",
    uploadBoardFile: "/files/board",
  },
};
