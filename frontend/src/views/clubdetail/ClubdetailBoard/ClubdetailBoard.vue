<template>
  <div class="container bg-img">
    <div>
      <TopHeader />
      <div class="card">
        <div class="main">
          <h4 class="title">담벼락</h4>
          <div class="club-board">
            <ClubdetailBoardList v-if="boardList && boardList.length > 0" :boardList="boardList" />
            <div class="empty" v-else>
              <span class="font-body-3">작성된 글이 없습니다</span>
            </div>
          </div>
          <button class="button-3" @click="clickRegister" :disabled="!boardData">등록</button>
          <div class="file-upload" @click="clickFileUpload">
            <icon-base :width="'1.4rem'" :height="'1.4rem'" :iconColor="'var(--grey)'"
              ><icon-file
            /></icon-base>
            <span class="font-body-4">{{ fileName }}</span>
          </div>
          <input class="hidden-item" ref="file" id="boardFile" type="file" @change="uploadFile()" />
          <h5>새로운 글</h5>
          <div class="new-input">
            <textarea
              v-model="boardData.contents"
              style="font-size: 1.2rem; text-align: justify"
              class="input-content"
              type="text"
              id="content"
              rows="5"
              placeholder="내용을 입력해주세요"
            />
          </div>
        </div>
      </div>
      <Navbar :selected="'board'" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import TopHeader from "@/views/clubdetail/TopHeader.vue";
import Navbar from "@/views/clubdetail/Navbar.vue";
import ClubdetailBoardList from "@/views/clubdetail/ClubdetailBoard/ClubdetailBoardList.vue";
import IconFile from "@/components/icons/IconFile.vue";
import axios from "axios";
import SERVER from "@/api/api";

export default {
  name: "ClubdetailBoard",
  components: {
    TopHeader,
    Navbar,
    ClubdetailBoardList,
    IconFile,
  },
  computed: {
    ...mapState("boardStore", ["boardList"]),
    ...mapState("clubStore", ["clubId", "clubImage"]),
  },
  data() {
    return {
      boardData: {
        contents: "",
        fileUrl: "",
      },
      fileName: "",
    };
  },
  methods: {
    ...mapActions("boardStore", ["findBoardList", "registerBoard"]),
    // 등록 버튼 클릭
    clickRegister() {
      this.boardData.contents = this.boardData.contents.replaceAll("\n", "<br/>");
      this.registerBoard(this.boardData).then(() => {
        this.boardData.contents = "";
        this.boardData.fileUrl = "";
      });
    },
    // 파일 첨부 버튼 클릭
    clickFileUpload() {
      this.$refs["file"].click();
    },
    // 담벼락 파일 업로드
    uploadFile() {
      let form = new FormData();
      let file = this.$refs["file"].files[0];
      form.append("file", file);
      axios
        .post(SERVER.URL + SERVER.ROUTES.uploadBoardFile, form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          this.boardData.fileUrl = data;
          let name = file.name.substring(0, file.name.indexOf("."));
          let format = file.name.substring(file.name.indexOf("."));
          if (name.length > 15) name = name.substring(0, 15) + "・・・";
          this.fileName = name + format;
        })
        .catch((err) => console.log(err));
    },
    // 배경 이미지 설정
    setBackgroundImage() {
      document.getElementsByClassName("bg-img")[0].style.backgroundImage =
        "var(--clubdetail-bg-" + this.clubImage + ")";
    },
  },
  created() {
    this.findBoardList(this.clubId);
  },
  async mounted() {
    await this.setBackgroundImage();
  },
};
</script>

<style lang="scss" scoped>
.bg-img {
  width: 100vw;
  height: 100%;
  min-height: 100vh;
  background-size: contain;
  z-index: -1;
  padding: 0;

  .card {
    width: inherit;
    height: inherit;
    min-height: 90vh;
    border-radius: 5rem 5rem 0 0;
    padding-bottom: 5rem;
    background-color: var(--beige);

    .main * {
      text-align: left;
    }

    // 여기서부터 메인 시작!
    .main {
      height: 100%;
      padding: 3rem 3rem 0;

      .title {
        text-align: center;
      }
      .club-board {
        width: inherit;
        min-height: 20rem;
        margin-top: 2rem;
        background-color: var(--white);
        border-radius: 1rem;
        box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
      }
      .empty {
        text-align: left;
        padding: 1.8rem;
        color: var(--grey);
      }
      h5 {
        display: flex;
        margin: 2rem 0 0 1rem;
      }
      .new-input {
        width: inherit;
        margin-top: 1rem;
        background-color: var(--white);
        border-radius: 1rem;
        box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
        text-align: center;
      }
      .input-content {
        color: var(--dark-grey);
        width: 90%;
        padding: 1.3rem 1rem;
        border: 0;
        background-color: transparent;
        text-align: left;
        font-size: 1.4rem;
      }
      textarea:focus {
        outline: none;
      }
      .file-upload {
        float: right;
        margin: 2.5rem 1rem 0 0;
        display: flex;
        align-items: center;
        gap: 0.5rem;
      }
      .file-upload > div {
        display: flex;
        align-items: center;
      }
      .check {
        width: 1.2rem;
        height: 1.2rem;
        margin-right: 0.5rem;
      }
    }
  }
}
</style>
