<template>
  <div class="question-item-wrapper">
    <div class="quetion-frame">
      <img
        class="profile-image"
        :src="profileImg ? profileImg : 'https://via.placeholder.com/50'"
        alt="프로필 사진"
      />
      <div class="question-info">
        <span class="font-body-3 nickname">{{ nickname }}</span>
        <span class="font-body-5">2021-08-11 17:16:15</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubdetailBoardItem",
  props: {
    boardId: {
      type: Number,
    },
    contents: {
      type: String,
    },
    date: {
      type: Date,
    },
    fileUrl: {
      type: String,
    },
    userId: {
      type: Number,
    },
    nickname: {
      type: String,
    },
    profileImg: {
      type: String,
    },
  },
};
</script>

<style scoped>
* {
  text-align: left;
}
.question-item-wrapper {
  padding: 0;
}
.quetion-frame {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  align-items: center;
  gap: 0.7rem;
  padding: 0;
}
.profile-image {
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
}
.question-info {
  position: relative;
  display: grid;
  grid-template-rows: 3fr 2fr;
  justify-content: start;
  align-items: end;
  gap: 0.2rem;
  padding: 0;
}
.sub-info {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0.2rem 0.5rem;
}
.lock {
  float: right;
  margin: -0.2rem 0.5rem 0 0;
  padding: 0;
}
.reply {
  float: right;
  color: var(--grey);
}
.nickname {
  font-weight: bold;
  color: var(--orange);
}
.question-content {
  margin: 1rem 0.7rem;
  text-align: justify;
}
</style>
