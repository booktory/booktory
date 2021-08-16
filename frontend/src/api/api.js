export default {
  URL: "http://localhost:8080/api",
  ROUTES: {
    // accounts
    login: "/users/login",
    socialLogin: "/users/social",
    register: "/users",
    registerExtraInfo: "/users/extra",
    findPassword: "/users/password/",
    resetPassword: "/users/password/reset",
    logout: "/users/logout",

    // mypage
    getUserInfo: "/users/me",
    updateUserInfo: "/users",
    changePassword: "/users/password",
    deleteUser: "/users",

    // mybook
    getReadBooks: "/users/books",

    // clubs
    createClub: "/clubs",
    getClubInfo: "/clubs/",
    updateClub: "/clubs/",
    deleteClub: "/clubs/",
    findClubList: "/clubs/list",
    questions: "/questions/",

    // book-club
    createBook: "/bookclubs",
    deleteBook: "/bookclubs/",
    createMeeting: "/bookclubs",
    cancelMeeting: "/bookclubs/",
    getBookClubInfo: "/bookclubs/",
    getBookClubList: "/bookclubs/list/",

    // board
    findBoardList: "/boards/",
    registerBoard: "/boards/",
    deleteBoard: "/boards/",

    // search
    searchBookByKeyword: "/search/books",
    searchClubByNameAndGenre: "/search/clubs",

    // file-upload
    uploadProfileImg: "/files/user",
    uploadBoardFile: "/files/board",

    // notification
    registerFCMToken: "/notification",
  },
};
