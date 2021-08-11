<template>
  <div class="clubdetail-container">
    <div class="bg-image">
      <div class="icon" @click="$router.push({ name: 'ClubHome' })">
        <icon-base><icon-x /></icon-base>
      </div>
      <div class="card">
        <div class="main">
          <div class="main-head">
            <h4>다음 모임</h4>
            <button @click="$router.push({ name: 'ClubdetailMettingCreate' })">모임 만들기</button>
          </div>
          <div class="next-meeting">
            <div class="next-metting-card">
              <div class="next-metting-card-head">
                <h5>{{ nextMetting.endDateTime }} <span>오후 9:00</span></h5>
              </div>
              <div class="next-metting-card-body font-body-3">
                {{ nextMetting.bookTitle }}
              </div>
              <div class="next-metting-card-footer font-body-4">모임 시작 ..전</div>
            </div>
          </div>
          <div class="pre-meeting">
            <h4>지난 모임</h4>
            <div class="pre-metting-card" v-for="(preMetting, idx) in preMettings" :key="idx">
              <div class="pre-metting-card-head">
                <h5>{{ preMetting.endDateTime }} <span>오후 9:00</span></h5>
              </div>
              <div class="pre-metting-card-body font-body-3">
                {{ preMetting.bookTitle }}
              </div>
              <div class="pre-metting-card-footer font-body-4">
                <p>{{ preMetting.userList.length }}명 참석</p>
                <span v-for="(user, idx) in preMetting.userList" :key="idx">
                  {{ user.profileImg }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Navbar class="footer" />
  </div>
</template>

<script>
import Navbar from "@/views/clubdetail/Navbar.vue";

export default {
  name: "ClubdetailMeeting",
  components: {
    Navbar,
  },
  computed: {
    nextMetting: function () {
      return this.$store.state.examples.meetingList[0];
    },
    preMettings: function () {
      return this.$store.state.examples.meetingList;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "./ClubdetailMeeting.scss";
</style>
