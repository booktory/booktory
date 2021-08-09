export default {
  state: {
    meetingList: [
      {
        id: 0,
        bookId: 0,
        bookTitle: "The Pshchology of Money",
        bookThumbnail: "이미지",
        startDateTime: "2021-08-01",
        endDateTime: "2021-08-21",
        userList: [
          {
            userId: 0,
            userNickname: "소프트콘",
            profileImg: "유저프로필1",
          },
          {
            userId: 1,
            userNickname: "나야나",
            profileImg: "유저프로필2",
          },
        ],
      },
      {
        id: 1,
        bookId: 1,
        bookTitle: "만들어진 신",
        bookThumbnail: "이미지",
        startDateTime: "2021-07-24",
        endDateTime: "2021-08-01",
        userList: [
          {
            userId: 0,
            userNickname: "소프트콘",
            profileImg: "유저프로필1",
          },
          {
            userId: 1,
            userNickname: "나야나",
            profileImg: "유저프로필2",
          },
        ],
      },
    ],

    genresList: [
      "소설",
      "장르소설",
      "희곡",
      "시",
      "고전",
      "만화",
      "에세이",
      "경제경영",
      "자기계발",
      "외국어",
      "IT",
      "인문학",
      "사회과학",
      "역사",
      "자연과학",
      "예술",
      "대중문화",
      "종교/역학",
      "기타",
    ],

    bookclubs: [
      {
        id: 0,
        book_id: 0,
        club_id: 0,
        start_datetime: "2021-08-01",
        end_datetime: "2021-08-20",
        clubList: [
          {
            id: 0,
            name: "해리포터 책방",
            leader_id: "소프트콘",
            date: "2021-07-11",
            img: "https://picsum.photos/200/300",
            info: "해리포터 시리즈 영문 원서를 읽어요!",
            max_member: 8,
            is_open: true,
            volum_rule: 2,
            week_rule: 2,
            free_rule: "만나서 독서토론을 진행해요",
            genres: [{ genreName: "소설" }, { genreName: "장르소설" }],
          },
          {
            id: 1,
            name: "해리포터 불사조 읽기",
            leader_id: "불사조맨",
            date: "2021-07-11",
            img: "https://picsum.photos/200/300?grayscale",
            info: "영화로 만들어진 다양한 책들을 읽고 함께 이야기를 나눠요.",
            max_member: 8,
            is_open: true,
            volum_rule: 2,
            week_rule: 2,
            free_rule: "만나서 독서토론을 진행해요",
            genres: [{ genreName: "소설" }, { genreName: "장르소설" }, { genreName: "희곡" }],
          },
          {
            id: 2,
            name: "해리포터 볼드모트 모여라",
            leader_id: "볼드모트",
            date: "2021-07-11",
            img: "https://picsum.photos/200/300?blur",
            info: "영화로 만들어진 다양한 책들을 읽고 함께 이야기를 나눠요.",
            max_member: 8,
            is_open: true,
            volum_rule: 2,
            week_rule: 2,
            free_rule: "만나서 독서토론을 진행해요",
            genres: [{ genreName: "소설" }, { genreName: "장르소설" }, { genreName: "희곡" }],
          },
        ],
        bookList: [
          {
            id: 0,
            title: "해리포터 : 죽음의 성물1",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 1,
            title: "해리포터 : 죽음의 성물2",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 2,
            title: "해리포터 : 불의 잔",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20191206",
            thumbnail:
              "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
          },
        ],
      },
      {
        id: 1,
        book_id: 1,
        club_id: 1,
        start_datetime: "2021-07-01",
        end_datetime: "2021-07-20",
        clubList: [
          {
            id: 1,
            name: "영화 읽기",
            leader_id: "짱짱맨",
            date: "2021-07-11",
            img: "@/assets/images/club-backgroud.png",
            info: "영화로 만들어진 다양한 책들을 읽고 함께 이야기를 나눠요.",
            max_member: 8,
            is_open: true,
            volum_rule: 2,
            week_rule: 2,
            free_rule: "만나서 독서토론을 진행해요",
            genres: [{ genreName: "소설" }, { genreName: "장르소설" }, { genreName: "희곡" }],
          },
        ],
        bookList: [
          {
            id: 0,
            title: "해리포터 : 죽음의 성물1",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 1,
            title: "해리포터 : 죽음의 성물2",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 2,
            title: "해리포터 : 불의 잔",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20191206",
            thumbnail:
              "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
          },
        ],
      },
      {
        id: 0,
        book_id: 0,
        club_id: 0,
        start_datetime: "2021-08-01",
        end_datetime: "2021-08-20",
        clubList: [
          {
            id: 0,
            name: "최고 수익률 투자클럽",
            leader_id: "워렌 버핏",
            date: "2021-07-11",
            img: "https://image.shutterstock.com/image-vector/picture-icon-image-photo-260nw-1672289161.jpg",
            info: "해리포터 시리즈 영문 원서를 읽어요!",
            max_member: 8,
            is_open: true,
            volum_rule: 2,
            week_rule: 2,
            free_rule: "만나서 독서토론을 진행해요",
            genres: [{ genreName: "소설" }, { genreName: "장르소설" }],
          },
        ],
        bookList: [
          {
            id: 0,
            title: "해리포터 : 죽음의 성물1",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 1,
            title: "해리포터 : 죽음의 성물2",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20200312",
            thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
          },
          {
            id: 2,
            title: "해리포터 : 불의 잔",
            isbn: "9788983927736",
            info: "문학",
            author: "J.K. 롤링",
            translators: "강동혁",
            publisher: "문학수첩",
            price: "",
            date: "20191206",
            thumbnail:
              "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
          },
        ],
      },
    ],
  },
  mutations: {},
  actions: {},
  modules: {},
};
