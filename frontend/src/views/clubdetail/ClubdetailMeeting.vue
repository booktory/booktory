<template>
  <div class="metting">
    <div class="next-meeting">
      <div class="next-meeting-head">
        <h5>다음 모임</h5>
        모임 만들기
      </div>
      <div class="club-card">
        <div class="club-card-text">
          <h5>{{ nextMetting.endDateTime }}</h5>
          <p class="font-body-3">{{ nextMetting.bookTitle }}</p>
          <p class="font-body-4">모임 시작 ..전</p>
        </div>
      </div>
    </div>
    <div class="pre-meeting">
      <div class="next-meeting-head">
        <h5>지난 모임</h5>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubdetailMeeting",
  computed: {
    nextMetting: function () {
      return this.$store.state.examples.meetingList[0];
    },
    preMetting: function () {
      return this.$store.state.examples.meetingList[1];
    },
  },
};
</script>

<style>
.meeting * {
  text-align: left;
}

.next-meeting-head {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 10em;
}

.club-card {
  width: 100%;
  height: 15%;
  padding: 5%;
  border-radius: 10%;
  background-color: #ffff;
  box-shadow: 0 0.4em 0.8em 0 rgba(142, 141, 208, 0.16);
}

h5 {
  margin: 0;
}

img {
  object-fit: cover;
}

.club-card-text * {
  text-align: left;
}
</style>
