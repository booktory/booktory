<template>
  <div class="register">
    <div class="container">
      <div class="navbar">
        <div class="icon" @click="$router.go(-1)">
          <icon-base><icon-arrow-left /></icon-base>
        </div>
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
          <span @click="addGerne" class="tag">{{ gerne }}</span>
        </span>
      </div>

      <!-- 운영 규칙 설정 -->
      <div class="rules">
        <div class="label">운영 규칙</div>
        <!--  -->
        <div>
          <span class="font-body-2">
            - 한 달에
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
          </span>
        </div>
        <!--  -->
        <div>
          <span class="font-body-2">
            -
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
          <span class="font-body-2">- </span>
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
        <div>
          <input type="checkbox" id="checkbox" v-model="clubData.isOpen" />
          <span class="font-body-3 is-open-text" v-if="clubData.isOpen">공개</span>
          <span class="font-body-3 is-open-text" v-else>비공개</span>
        </div>
      </div>
      <button
        type="button"
        class="button-2 m-top-10"
        @click="$router.push({ name: 'ClubCreateBook' })"
      >
        다음
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubCreate",
  data() {
    return {
      clubData: {
        name: "",
        img: "https://picsum.photos/200/300",
        info: "",
        maxMember: 8,
        isOpen: true,
        volumRule: 1,
        weekRule: 2,
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

.tag {
  margin: 0% 0.2%;
  padding: 0.5% 1.6%;
  border: 0;
  border-radius: 10px;
  color: white;
  background-color: #a4c0f3;
}

.label {
  text-align: left;
  display: block;
  margin: 5.5% 0 3% 5%;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--grey);
}

.rules * {
  text-align: left;
}

.rules {
  display: inline-block;
  width: 80%;
  margin: 0 auto;
}

select {
  width: 15%;
  margin: 1% 2% 2%;
  padding: 2%;
  font-size: 1.4rem;
  color: #8a8894;
  border: 0;
  border-radius: 24px;
  background-color: #fff;
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}

.max-member {
  display: inline-block;
  text-align: left;
  width: 80%;
  margin: 0 auto;
}

.max-member #v-model-select > select {
  width: 30%;
  padding: 2% 10% 2%;
}

.is-open {
  text-align: left;
  width: 80%;
  margin: 0 auto;
}

.is-open > div {
  text-align: left;
  display: flex;
  align-items: center;
}

#checkbox {
  width: 2rem;
  height: 2rem;
  border: 1px solid #cccccc;
  transition: all linear 0.3s;
  display: inline-block;
  margin: 5%;
}
</style>
