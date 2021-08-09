<template>
  <div class="container">
    <div class="navbar">
      <div class="icon" @click="$router.go(-1)">
        ​ <icon-base><icon-arrow-left /></icon-base> ​
      </div>
      ​
    </div>
    ​
    <h4 class="title">알림</h4>
    <p class="sub-title">가입하신 이메일을 입력해주세요</p>
  </div>
</template>
<script>
import { mapState } from "vuex";
import fire from "@/firebase.js";

export default {
  name: "Notification",
  components: {},
  computed: {
​    ...mapState(["user"]),
  },
  created() {
​    console.log(this.user.nickname);
​    var unreadList = [];
​    const usersref = fire.database().ref(`users/${this.user.nickname}`);
​    usersref.on("value", (list) => {
​      const data = list.val();
​      // console.log(data);
​      for (let key in data) {
​        // console.log(key);
​        unreadList.push(key);
​      }
​    });
​    for (var a of unreadList) {
​      console.log("sfs" + a);
​      fire
​        .database()
​        .ref("users/" + this.user.nickname + "/" + a)
​        .update({
​          message: "수정된 메시지~",
​        });
​    }
​    // fire
​    //   .database()
​    //   .ref(`users/${this.user.nickname}`)
​    //   .on("value", (list) => {
​    //     const data = list.val();
​    //     for (let key in data) {
​    //       console.log(data[key].message);
​    //     }
​    //   });
  },
};
</script>
