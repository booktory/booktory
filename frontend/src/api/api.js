export default {
  URL: "https://i5a607.p.ssafy.io/api",
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
    registerBookMemo: "/users/books",
    cancelBookMemo: "/users/books/",

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
    attendMeeting: "/bookclubs/",
    leaveMeeting: "/bookclubs/",
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

    //rwm
    getRwmRoomInfo: "/rwms/",
    getRwmList: "/rwms",
    enterRwmRoom: "/rwms/",
    exitRwmRoom: "/rwms/",
    getRwmParticipant: "/rwms/",
  },
};
