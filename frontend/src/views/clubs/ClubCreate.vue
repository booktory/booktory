<template>
  <div class="container">
    <div>
      <TopHeader />
      <h4 class="title">클럽 만들기</h4>
      <div class="input-div">
        <label for="name">클럽 이름</label>
        <div>
          <input
            v-model.trim="clubData.name"
            v-bind:class="{
              error: error.name,
              complete: !error.name && clubData.name.length !== 0,
            }"
            type="text"
            id="name"
            maxlength="10"
            placeholder="클럽 이름을 입력해주세요"
            autocapitalize="none"
            autocorrect="none"
            required
          />
        </div>
        <p v-if="error.name" class="message">{{ error.name }}</p>
      </div>
      <div class="input-div">
        <label for="info">클럽 소개</label>
        <div>
          <input
            v-model.trim="clubData.info"
            v-bind:class="{
              error: error.info,
              complete: !error.info && clubData.info.length !== 0,
            }"
            type="text"
            id="info"
            maxlength="30"
            placeholder="클럽 소개를 입력해주세요"
            autocapitalize="none"
            autocorrect="none"
            required
          />
        </div>
        <p v-if="error.info" class="message">{{ error.info }}</p>
      </div>
      <div class="input-div">
        <label for="genres">클럽에서 다룰 도서 분야</label>
        <div>
          <div v-if="clubData.genres.length > 0" class="genre-inner-div">
            <span
              @click="removeGenre"
              v-for="(genre, index) in clubData.genres"
              :key="index"
              :id="genre"
              class="input-tag"
              >{{ genreList[genre - 1].name }}</span
            >
          </div>
          <div v-else class="genre-inner-div">
            <span class="placeholder">도서 분야를 선택해주세요</span>
          </div>
        </div>
        <!-- 장르 키워드 선택 -->
        <div class="genre-keyword">
          <span v-for="(genre, idx) in genreList" :key="idx">
            <span @click="addGenre" :id="genre.id" class="tag">{{ genre.name }}</span>
          </span>
        </div>
        <p v-if="error.genres" class="message">{{ error.genres }}</p>
      </div>

      <!-- 운영 규칙 설정 -->
      <div class="rules">
        <div class="label">운영 규칙</div>
        <div>
          <span class="font-body-3">
            • &nbsp;&nbsp;한 달에
            <span id="v-model-select">
              <select v-model="clubData.volumeRule">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
              </select>
            </span>
            권 읽어요
          </span>
        </div>
        <!--  -->
        <div>
          <span class="font-body-3">
            •
            <span id="v-model-select">
              <select v-model="clubData.weekRule">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
              </select>
            </span>
            주에 한 번 만나요
          </span>
        </div>
        <!--  -->
        <div class="input-div">
          <span class="font-body-3">• </span>
          <div class="freeRule-div">
            <input
              v-model.trim="clubData.freeRule"
              type="text"
              id="freeRule"
              placeholder="만나서 독서토론을 진행해요"
            />
          </div>
        </div>
      </div>

      <div class="two-input">
        <!-- 클럽 인원 -->
        <div class="max-member">
          <div class="label">클럽 최대 인원수</div>
          <span class="font-body-3">
            <span id="v-model-select">
              <select v-model="clubData.maxMember">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
              </select>
            </span>
            명
          </span>
        </div>
        <!-- 공개 여부 -->
        <div class="is-open">
          <div class="label">공개 여부</div>
          <div class="wrap">
            <input type="checkbox" id="checkbox" v-model="clubData.isOpen" />
            <span class="font-body-3 is-open-text" v-if="clubData.isOpen">공개</span>
            <span class="font-body-3 is-open-text" v-else>비공개</span>
          </div>
        </div>
      </div>
      <!-- 클럽 대표사진 설정 -->
      <div class="input-div">
        <label for="img">클럽 대표사진</label>
        <span class="radio-wrapper">
          <div class="radio-wrapper-item">
            <input type="radio" v-model="clubData.img" value="1" checked />
            <div class="img-div">
              <img
                src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-1.jpg"
                alt=""
              />
            </div>
          </div>
          <div class="radio-wrapper-item">
            <input type="radio" v-model="clubData.img" value="2" />
            <div class="img-div">
              <img
                src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-2.jpg"
                alt=""
              />
            </div>
          </div>
          <div class="radio-wrapper-item">
            <input type="radio" v-model="clubData.img" value="3" />
            <div class="img-div">
              <img
                src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-3.jpg"
                alt=""
              />
            </div>
          </div>
          <div class="radio-wrapper-item">
            <input type="radio" v-model="clubData.img" value="4" />
            <div class="img-div">
              <img
                src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-4.jpg"
                alt=""
              />
            </div>
          </div>
          <div class="radio-wrapper-item">
            <input type="radio" v-model="clubData.img" value="5" />
            <div class="img-div">
              <img
                src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-5.jpg"
                alt=""
              />
            </div>
          </div>
        </span>
      </div>
      <button type="button" class="button-2 m-top-10" :disabled="!isSubmit" @click="clickNext">
        다음
      </button>
      <Navbar :selected="'home'" />
    </div>
  </div>
</template>

<script>
import TopHeader from "@/views/TopHeader.vue";
import Navbar from "@/views/Navbar.vue";
import Swal from "sweetalert2";
import { mapActions, mapState } from "vuex";

export default {
  name: "ClubCreate",
  components: {
    TopHeader,
    Navbar,
  },
  computed: {
    ...mapState("searchStore", ["genreList"]),
  },
  data() {
    return {
      clubData: {
        name: "",
        img: "1",
        info: "",
        maxMember: 8,
        isOpen: true,
        volumeRule: 1,
        weekRule: 2,
        freeRule: "",
        genres: [],
        books: [],
      },
      error: {
        name: false,
        info: false,
        genres: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    clubData: {
      deep: true,
      handler() {
        this.checkNameForm();
        this.checkInfoForm();
        this.checkGenreForm();
      },
    },
  },
  methods: {
    ...mapActions("clubStore", ["saveClubData"]),
    // 다음 버튼 클릭
    clickNext() {
      if (this.isSubmit) {
        this.saveClubData(this.clubData);
      }
    },
    // 도서 분야 추가
    addGenre: function (event) {
      let index = -1;
      for (var i = 0; i < this.clubData.genres.length; i++) {
        if (event.target.id == this.clubData.genres[i]) {
          index = i;
          break;
        }
      }
      if (index < 0) {
        if (this.clubData.genres.length < 5) {
          this.clubData.genres.push(event.target.id);
        } else {
          Swal.fire({
            icon: "warning",
            title: "최대 5개까지 선택 가능해요",
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: false,
          });
        }
      }
    },
    // 추가된 도서 분야 제거
    removeGenre(event) {
      console.log(event.target);
      for (var i = 0; i < this.clubData.genres.length; i++) {
        if (event.target.id == this.clubData.genres[i]) {
          this.clubData.genres.splice(i, 1);
          break;
        }
      }
    },
    // 클럽 이름 유효성 검사
    checkNameForm() {
      if (this.clubData.name.length > 0 && this.clubData.name.length > 10) {
        this.error.name = "클럽 이름이 너무 길어요! 10자 이하로 입력해주세요";
      } else this.error.name = false;
    },
    // 클럽 소개 유효성 검사
    checkInfoForm() {
      if (this.clubData.info.length > 0 && this.clubData.info.length > 30) {
        this.error.info = "클럽 소개가 너무 길어요! 30자 이하로 입력해주세요";
      } else this.error.info = false;
    },
    // 클럽 도서 분야 유효성 검사
    checkGenreForm() {
      if (this.clubData.genres.length <= 0) {
        this.error.genres = "선택한 도서 분야가 없어요! 도서 분야를 선택해주세요";
      } else this.error.genres = false;

      // 유효성 검사 통과 시 다음 버튼 활성화
      if (this.clubData.name.length > 0 && this.clubData.info.length > 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      } else {
        this.isSubmit = false;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.text-div * {
  color: var(--grey);
  margin: 0.6em auto;
}
.genre-keyword {
  background: transparent;
  box-shadow: none;
  margin: 1.5rem auto 0;
  text-align: center;
}
.genre-keyword span {
  margin: 0 0.2rem 0.3rem;
}
.genre-inner-div {
  padding: 0.85rem 0;
  margin-left: 1rem;
}
.tag,
.input-tag {
  display: inline-block;
  padding: 0.2rem 0.6rem;
  border: 0;
  border-radius: 1em;
  color: var(--white);
  background-color: var(--light-orange);
}
.input-tag {
  margin: 0 0.3rem;
  padding: 0.25rem 0.6rem;
  background-color: var(--orange);
}
.placeholder {
  font-size: 1.4rem;
  color: var(--light-grey);
  margin-left: 0.7rem;
}

.label {
  text-align: left;
  display: block;
  margin: 0 0 1rem 1.6rem;
  font-size: 1.2rem;
  color: var(--grey);
}

.freeRule-div {
  margin-left: -0.5rem;
  height: inherit;
}
#freeRule {
  padding-left: 1.2rem;
}

.rules * {
  text-align: left;
}

.rules {
  width: 30rem;
  display: inline-block;
  margin: 0 auto;
}

.rules .label {
  margin-bottom: 0.2em;
}

.rules .label div {
  width: 93%;
  margin-left: 0.4em;
}

.rules .input-div {
  margin: 0;
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.rules .input-div span {
  margin-right: 1em;
}

select {
  width: 3em;
  margin: 1% 2% 2%;
  padding: 0.6rem 0 0.6rem 0.7rem;
  font-size: 1.4rem;
  color: var(--grey);
  border: 0;
  border-radius: 2em;
  background-color: var(--white);
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}

.two-input {
  width: 30em;
  display: flex;
  margin: 2.5rem auto;
}

.two-input .max-member {
  text-align: left;
  width: 100%;
}

.two-input .max-member #v-model-select > select {
  margin: 0 0 0 2rem;
}

.two-input .is-open {
  text-align: left;
  width: 100%;
}

.two-input .is-open > div {
  text-align: left;
  display: flex;
  align-items: center;
}
.two-input .is-open .wrap {
  margin: 1.4rem 0 0 1.6rem;
}

#checkbox {
  width: 1.8rem;
  height: 1.8rem;
  transition: all linear 0.3s;
  display: inline-block;
  margin-right: 0.7em;
}

.radio-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  gap: 0.8rem;

  &-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;

    .img-div {
      width: 5rem;
      height: 100%;

      img {
        width: 5rem;
        height: 7.5rem;
        border-radius: 1rem;
      }
    }
  }
}
</style>
