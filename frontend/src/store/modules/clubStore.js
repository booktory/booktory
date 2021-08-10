import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";

const clubStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    delete({ rootGetters }, clubId) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.deleteClub + clubId, rootGetters.config)
        .then((res) => {
          console.log(res);
          Swal.fire({
            icon: "success",
            title: "클럽 삭제 성공",
            showConfirmButton: false,
            timer: 1000,
            timerProgressBar: true,
          });
          router.push({ name: "ClubHome" });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            title: "클럽 삭제 실패",
            text: err.response.data.message,
            showConfirmButton: false,
            timer: 1500,
            timerProgressBar: false,
          });
        });
    },
  },
};

export default clubStore;
