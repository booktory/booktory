import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/email",
    name: "Email",
    component: () => import("@/views/accounts/Email.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import( "@/views/accounts/Login.vue"),
  },
  {
    path: "/find",
    name: "Find",
    component: () => import("@/views/accounts/Find.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
