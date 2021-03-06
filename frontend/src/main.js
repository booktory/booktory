import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// SVG Icon
import IconBase from "@/components/IconBase.vue";
Vue.component("icon-base", IconBase);
import IconArrowLeft from "@/components/icons/IconArrowLeft.vue";
Vue.component("icon-arrow-left", IconArrowLeft);
import IconBell from "@/components/icons/IconBell.vue";
Vue.component("icon-bell", IconBell);
import IconChevronLeft from "@/components/icons/IconChevronLeft.vue";
Vue.component("icon-chevron-left", IconChevronLeft);
import IconChevronRight from "@/components/icons/IconChevronRight.vue";
Vue.component("icon-chevron-right", IconChevronRight);
import IconX from "@/components/icons/IconX.vue";
Vue.component("icon-x", IconX);
import IconUsers from "@/components/icons/IconUsers.vue";
Vue.component("icon-users", IconUsers);

// Plugins
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";
Vue.use(DatePicker);
// vue-moment
Vue.use(require("vue-moment"));

Vue.config.productionTip = false;

// firebase
import fire from "@/firebase.js";

const firebaseModule = (function () {
  async function init() {
    // Your web app's Firebase configuration
    if ("serviceWorker" in navigator) {
      window.addEventListener("load", function () {
        navigator.serviceWorker.register("/firebase-messaging-sw.js").then(
          (
            registration // eslint-disable-line no-unused-vars
          ) => {
            // Show Notificaiton Dialog
            const messaging = fire.messaging();
            messaging
              .requestPermission()
              .then(function () {
                return messaging.getToken();
              })
              .then(async function () {
                messaging.onMessage((payload) => {
                  const title = payload.notification.title;
                  const options = {
                    body: payload.notification.body,
                  };
                  navigator.serviceWorker.ready.then((registration) => {
                    registration.showNotification(title, options);
                  });
                });
              })
              .catch(function (err) {
                console.log(err);
              });
          }
        );
      });
    }
  }

  return {
    init: function () {
      init();
    },
  };
})();

firebaseModule.init();

// kakao login
window.Kakao.init("2d28ddad0da9ae11feff8fbe006682af");

// google login
import GAuth from "vue-google-oauth2";

const gauthOption = {
  clientId: "160792286358-2kffcdol1v9o8phfbponpp9g5bc9ld03.apps.googleusercontent.com",
  scope: "email profile",
  prompt: "consent",
  fetch_basic_profile: true,
};

Vue.use(GAuth, gauthOption);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
