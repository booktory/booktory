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

// Plugins
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";
Vue.use(DatePicker);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
