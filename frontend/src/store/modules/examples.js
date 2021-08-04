export default ({
  state: {
    bookclubs: [
      {
        "id": 0,
        "book_id": 0,
        "club_id": 0,
        "start_datetime": "2021-08-01",
        "end_datetime": "2021-08-20",
        "clubList": [
          {
            "id": 0,
            "name": "해리포터 책방",
            "leader_id": "소프트콘",
            "date": "2021-07-11",
            "img": "https://image.shutterstock.com/image-vector/picture-icon-image-photo-260nw-1672289161.jpg",
            "info": "해리포터 시리즈 영문 원서를 읽어요!",
            "max_member": 8,
            "is_open": true,
            "volum_rule": 2,
            "week_rule": 2,
            "free_rule": "만나서 독서토론을 진행해요",
            "genres" : [
              { "genreName": "소설" },
              { "genreName": "장르소설" },
            ]
          }
        ],
        "bookList": [
          { "id": 0,
          "title": "해리포터 : 죽음의 성물1",
          "isbn": "9788983927736",
          "info": "문학",
          "author": "J.K. 롤링",
          "translators": "강동혁",
          "publisher": "문학수첩",
          "price": "",
          "date": "20200312",
          "thumbnail": "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
          { "id": 1,
          "title": "해리포터 : 죽음의 성물2",
          "isbn": "9788983927736",
          "info": "문학",
          "author": "J.K. 롤링",
          "translators": "강동혁",
          "publisher": "문학수첩",
          "price": "",
          "date": "20200312",
          "thumbnail": "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
          { "id": 2,
          "title": "해리포터 : 불의 잔",
          "isbn": "9788983927736",
          "info": "문학",
          "author": "J.K. 롤링",
          "translators": "강동혁",
          "publisher": "문학수첩",
          "price": "",
          "date": "20191206",
          "thumbnail": "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg"
        }]
      }
    ]
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
