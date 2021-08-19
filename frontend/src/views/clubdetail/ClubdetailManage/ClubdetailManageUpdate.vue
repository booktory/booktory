<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div v-if="clubInfo" class="main">
          <h4 class="title">클럽 정보 수정</h4>
          <div class="input-div">
            <label for="name">클럽 이름</label>
            <div>
              <input
                v-model.trim="clubInfo.name"
                v-bind:class="{
                  error: error.name,
                  complete: !error.name && clubInfo.name.length !== 0,
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
                v-model.trim="clubInfo.info"
                v-bind:class="{
                  error: error.info,
                  complete: !error.info && clubInfo.info.length !== 0,
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
              <div v-if="clubInfo.genres.length > 0" class="genre-inner-div">
                <span
                  @click="removeGenre"
                  v-for="(genre, idx) in clubInfo.genres"
                  :key="idx"
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
            <!--  -->
            <div>
              <span class="font-body-3">
                • &nbsp;&nbsp;한 달에
                <span id="v-model-select">
                  <select v-model="clubInfo.volumeRule">
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
                  <select v-model="clubInfo.weekRule">
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
                  v-model.trim="clubInfo.freeRule"
                  type="text"
                  id="freeRule"
                  placeholder="만나서 독서토론을 진행해요"
                />
              </div>
            </div>
          </div>

          <!-- 클럽 인원 -->
          <div class="max-member m-top-1">
            <div class="label">클럽 최대 인원수</div>
            <span class="font-body-3">
              <span id="v-model-select">
                <select v-model="clubInfo.maxMember">
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

          <!-- 클럽 대표사진 설정 -->
          <div class="input-div">
            <label for="img">클럽 대표사진</label>
            <span class="radio-wrapper">
              <div class="radio-wrapper-item">
                <input type="radio" v-model="clubInfo.img" value="1" checked />
                <div class="img-div">
                  <img
                    src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-1.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="radio-wrapper-item">
                <input type="radio" v-model="clubInfo.img" value="2" />
                <div class="img-div">
                  <img
                    src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-2.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="radio-wrapper-item">
                <input type="radio" v-model="clubInfo.img" value="3" />
                <div class="img-div">
                  <img
                    src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-3.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="radio-wrapper-item">
                <input type="radio" v-model="clubInfo.img" value="4" />
                <div class="img-div">
                  <img
                    src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-4.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="radio-wrapper-item">
                <input type="radio" v-model="clubInfo.img" value="5" />
                <div class="img-div">
                  <img
                    src="https://booktory.s3.ap-northeast-2.amazonaws.com/static/default/clubthum-5.jpg"
                    alt=""
                  />
                </div>
              </div>
            </span>
          </div>
          <button type="button" class="button-2 update-btn" @click="clickUpdate">수정하기</button>
        </div>
      </div>
      <Navbar :selected="'manage'" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import Swal from "sweetalert2";

export default {
  name: "ClubdetailManageUpdate",
  components: {
    TopHeader,
    Navbar,
  },
  computed: {
    ...mapState("searchStore", ["genreList"]),
    ...mapState("clubStore", ["clubInfo", "clubImage", "clubId"]),
  },
  data() {
    return {
      error: {
        name: false,
        info: false,
        genres: false,
      },
      isSubmit: false,
    };
  },
  watch: {
    clubInfo: {
      deep: true,
      handler() {
        this.checkNameForm();
        this.checkInfoForm();
        this.checkGenreForm();
      },
    },
  },
  methods: {
    ...mapActions("clubStore", ["findClubInfo", "updateClub"]),
    // 수정하기 버튼 클릭
    clickUpdate() {
      if (this.isSubmit) {
        this.updateClub(this.clubInfo);
      }
    },
    // 도서 분야 추가
    addGenre: function (event) {
      let index = -1;
      for (var i = 0; i < this.clubInfo.genres.length; i++) {
        if (event.target.id == this.clubInfo.genres[i]) {
          index = i;
          break;
        }
      }
      if (index < 0) {
        if (this.clubInfo.genres.length < 5) {
          this.clubInfo.genres.push(event.target.id);
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
      for (var i = 0; i < this.clubInfo.genres.length; i++) {
        if (event.target.id == this.clubInfo.genres[i]) {
          this.clubInfo.genres.splice(i, 1);
          break;
        }
      }
    },
    // 클럽 이름 유효성 검사
    checkNameForm() {
      if (this.clubInfo.name.length > 0 && this.clubInfo.name.length > 10) {
        this.error.name = "클럽 이름이 너무 길어요! 10자 이하로 입력해주세요";
      } else this.error.name = false;
    },
    // 클럽 소개 유효성 검사
    checkInfoForm() {
      if (this.clubInfo.info.length > 0 && this.clubInfo.info.length > 30) {
        this.error.info = "클럽 소개가 너무 길어요! 30자 이하로 입력해주세요";
      } else this.error.info = false;
    },
    // 클럽 도서 분야 유효성 검사
    checkGenreForm() {
      if (this.clubInfo.genres.length <= 0) {
        this.error.genres = "선택한 도서 분야가 없어요! 도서 분야를 선택해주세요";
      } else this.error.genres = false;

      // 유효성 검사 통과 시 다음 버튼 활성화
      if (this.clubInfo.name.length > 0 && this.clubInfo.info.length > 0) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      } else {
        this.isSubmit = false;
      }
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  created() {
    this.findClubInfo(this.clubId);
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailManageUpdate.scss";
.input-div {
  margin: 2rem auto 2rem;
}
</style>
