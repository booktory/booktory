<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">클럽 정보 수정</h4>
          <div class="input-div m-top-5">
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
              <input
                v-model="clubData.info"
                type="text"
                id="info"
                placeholder="클럽을 소개해주세요"
              />
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
                readonly
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
              <span class="font-body-3">
                • 한 달에
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
          <button
            type="button"
            class="button-2 m-top-10"
            @click="$router.push({ name: 'ClubCreateBook' })"
          >
            다음
          </button>
        </div>
      </div>
    </div>
    <Navbar :selected="'manage'" class="footer" />
  </div>
</template>

<script>
import { mapActions } from "vuex";
import "./ClubdetailManageUpdate.scss";
import Navbar from "@/views/clubdetail/Navbar.vue";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Swal from "sweetalert2";

export default {
  name: "ClubdetailManageUpdate",
  components: {
    Navbar,
    TopHeader,
  },
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
  computed: {
    genresList: function () {
      return this.$store.state.examples.genresList;
    },
  },
  methods: {
    addGerne: function (event) {
      let gerne = event.target.textContent;
      return this.clubData.genres.length >= 5
        ? alert("최대 5개까지 추가가 가능해요!")
        : this.clubData.genres.push(gerne);
    },
    ...mapActions("clubStore", ["delete"]),
    clickDelete() {
      Swal.fire({
        showCancelButton: true,
        title: "클럽 삭제",
        html: "클럽 정보가 모두 삭제되고 다시 되돌릴 수 없습니다.<br>정말 클럽을 삭제 하시겠습니까?",
        confirmButtonText: "삭제하기",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          // 나중에 수정할 것!
          // this.delete(clubId);
          this.delete();
        }
      });
    },
  },
};
</script>

<style scoped>
.rules * {
  text-align: left;
}
.rules {
  display: inline-block;
  width: 100%;
  margin: 0 auto;
}
.rules .label {
  margin-bottom: 0.2em;
}
.rules .input-div > div {
  width: 93%;
  margin-left: 0.4em;
}
.rules .input-div {
  margin: 0;
}

select {
  width: 3em;
  margin: 1% 2% 2%;
  padding: 2%;
  font-size: 1.4rem;
  color: var(--grey);
  border: 0;
  border-radius: 2em;
  background-color: #fff;
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}

.max-member,
.is-open {
  text-align: left;
  width: 100%;
}

.max-member #v-model-select > select {
  width: 4em;
  margin: 0 0 0 1.2rem;
}

.is-open > div {
  text-align: left;
  display: flex;
  align-items: center;
}
.is-open .wrap {
  margin-left: 1rem;
}

#checkbox {
  width: 2rem;
  height: 2rem;
  border: 1px solid #cccccc;
  transition: all linear 0.3s;
  display: inline-block;
  margin-right: 0.7em;
}

.two-input {
  display: flex;
  margin-top: 0.7em;
}
</style>
