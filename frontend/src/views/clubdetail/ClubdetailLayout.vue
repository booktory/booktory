<template>
  <div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailLayout",
  components: {
    Navbar,
  },
};
</script>

<style>
.footer {
  width: 100%;
  left: 0;
  bottom: 0;
  position: fixed;
  text-align: center;
}
</style>
