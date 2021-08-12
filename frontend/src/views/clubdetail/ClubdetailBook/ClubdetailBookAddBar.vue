<template>
  <div class="input-div m-top-10">
    <label>읽을 책 찾기</label>
    <div>
      <input type="text" placeholder="책 이름을 입력해주세요" @input="onInputKeyworkd" />
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubdetailBookAddBar",
  methods: {
    onInputKeyworkd: function (event) {
      this.$emit("input-keyword", event.target.value);
    },
  },
};
</script>

<style></style>
