<template>
  <div class="register">
    <div class="container">
      <div class="navbar">
        <img
          class="icon"
          alt="arrow-left"
          src="@/assets/icons/arrow-left.svg"
          @click="$router.go(-1)"
        />
      </div>
      <h4 class="title">클럽 만들기</h4>

      <div class="input-div m-top-10">
        <label for="name">클럽 이름</label>
        <div>
          <input
            v-model="clubData.name"
            type="text"
            id="name"
            placeholder="클럽 이름을 입력해주세요"
          />
        </div>
      </div>
      <div class="input-div">
        <label for="info">클럽 소개</label>
        <div>
          <input v-model="clubData.info" type="text" id="info" placeholder="클럽을 소개해주세요" />
        </div>
        <p class="message">30자 이내로 입력해주세요.</p>
      </div>
      <div class="input-div">
        <label for="genres">클럽에서 다룰 도서 분야</label>
        <div>
          <input
            v-model="clubData.genres"
            type="text"
            id="genres"
            placeholder="최대 5개까지 선택 가능합니다."
          />
        </div>
      </div>

      <!-- 장르 키워드 선택 -->
      <div class="gerne-keyword">
        <span v-for="(gerne, idx) in genresList" :key="idx" :value="gerne">
          <span @click="addGerne">{{ gerne }}</span>
        </span>
      </div>

      <!-- 운영 규칙 설정 -->
      <div class="rules">
        <div class="label">운영 규칙</div>
        <!--  -->
        <div>
          <img src="@/assets/icons/check.svg" alt="check" />
          한 달에
          <span id="v-model-select">
            <select v-model="clubData.volumRule">
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
        </div>
        <!--  -->
        <div>
          <img src="@/assets/icons/check.svg" alt="check" />
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
        </div>
        <!--  -->
        <div class="input-div">
          <img src="@/assets/icons/check.svg" alt="check" />
          <div>
            <input
              v-model="clubData.freeRule"
              type="text"
              id="freeRule"
              placeholder="만나서 독서토론을 진행해요"
            />
          </div>
        </div>
      </div>

      <!-- 클럽 인원 -->
      <div class="max-member">
        <div class="label">클럽 최대 인원수</div>
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
      </div>
      <!-- 공개 여부 -->
      <div class="is-open">
        <div class="label">공개 여부</div>
        <input type="checkbox" id="checkbox" v-model="clubData.isOpen" />
        <label for="checkbox">{{ clubData.isOpen }}</label>
      </div>

      <button type="button" class="button-2 m-top-10">다음</button>
    </div>
  </div>
</template>

<script>
// import axios from "axios";
// import Swal from "sweetalert2";

export default {
  name: "CreateClub",
  data() {
    return {
      clubData: {
        name: "",
        img: "https://picsum.photos/200/300",
        info: "",
        maxMember: 8,
        isOpen: true,
        volumRule: "",
        weekRule: "",
        freeRule: "",
        genres: [],
        books: [],
      },
    };
  },
  methods: {
    addGerne: function (event) {
      let gerne = event.target.textContent;
      return this.clubData.genres.length >= 5
        ? alert("최대 5개까지 추가가 가능해요!")
        : this.clubData.genres.push(gerne);
    },
  },
  computed: {
    genresList: function () {
      return this.$store.state.examples.genresList;
    },
  },
};
</script>

<style scoped>
.text-div * {
  color: var(--grey);
  margin: 0.6em auto;
}
.gerne-keyword {
  width: 80%;
  margin: 5% auto 0;
}

.rules * {
  text-align: left;
}

.rules {
  display: inline-block;
  width: 80%;
  margin: 0 auto;
}

.label {
  text-align: left;
  display: block;
  margin: 5.5% 0 3% 5%;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--grey);
}

.max-member * {
  text-align: left;
}

.max-member {
  display: inline-block;
  width: 80%;
  margin: 0 auto;
}

.is-open * {
  text-align: left;
}

.is-open {
  display: inline-block;
  width: 80%;
  margin: 0 auto;
}
</style>
