<template>
  <div class="container">
    <TopHeader :nickname="nickname" />
    <h4 class="title">내가 읽은 책</h4>
    <BookList :bookList="bookList" />
    <Navbar :selected="'mybook'" class="footer" />
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import BookList from "@/views/mybook/BookList.vue";

export default {
  name: "MyBook",
  components: {
    TopHeader,
    Navbar,
    BookList,
  },
  data: function() {
    return {
      nickname: "책토리",
      bookList: [
        {
          id: 0,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 1,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 2,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 3,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 4,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 5,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 6,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 7,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 8,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 9,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 10,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 11,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 12,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 13,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 14,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 15,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 111,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 21,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 110,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 133,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 232,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 30,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 145,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 29,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
        {
          id: 90,
          title: "해리포터 : 죽음의 성물1",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 197,
          title: "해리포터 : 죽음의 성물2",
          thumbnail: "http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg",
        },
        {
          id: 28,
          title: "해리포터 : 불의 잔",
          thumbnail:
            "https://upload.wikimedia.org/wikipedia/ko/f/fb/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC%99%80_%EB%B6%88%EC%9D%98_%EC%9E%94_%EC%98%81%ED%99%94.jpg",
        },
      ],
    };
  },
};
</script>

<style scoped></style>
