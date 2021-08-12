<template>
  <div class="container">
    <div class="navbar meeting-navbar">
      <div class="icon" @click="leaveSession">
        <icon-base><icon-arrow-left /></icon-base>
      </div>
      <h5 class="meeting-navbar-title">{{ clubName }}</h5>
      <div class="icon meeting-navbar-book" @click="showBookInfo">
        <icon-base><icon-book /></icon-base>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="video-container">
        <user-video class="video-wrapper" :stream-manager="publisher" :isPublisher="isPublisher" />
        <user-video
          class="video-wrapper"
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import { OpenVidu } from "openvidu-browser";
import IconBook from "@/components/icons/IconBook.vue";
import UserVideo from "@/views/meeting/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i5a607.p.ssafy.io:4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "App",

  components: {
    UserVideo,
    IconBook,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      isPublisher: true,
      subscribers: [],

      clubName: "클럽명이다아",
      mySessionId: "SessionA",
      nickname: "MyNickname" + Math.floor(Math.random() * 100),
    };
  },
  created() {
    this.joinSession();
  },
  methods: {
    showBookInfo() {
      Swal.fire({
        html: `<div class="bookcard-background" style="width: 100%;margin: 5% auto;background-color: #f3f3f3;border-radius: 10px;">
        <div class="bookcard-box" style="display: flex;flex-direction: row;justify-content: flex-start;align-items: flex-start;gap: 10px;padding: 8%;">
          <img src="http://image.yes24.com/momo/TopCate60/MidCate05/5948182.jpg" alt="bookThumbnail" class="bookcard-image" 
          style="width: 8rem;height: 12rem;border-radius: 1rem;box-shadow: 0 3px 3px 0 var(--bg-black), inset 0 0 3px 0 var(--bg-black);"/>
          <div class="bookcard-info" style="display: flex;flex-direction: column;justify-content: flex-start;align-items: flex-start;margin-top:2rem;">
            <span class="bookcard-info-now font-body-4">읽고 있는 책</span>
            <h5 class="bookcard-info-title" style="margin-top:0.5rem;margin-bottom:0.8rem">해리포터 : 죽음의 성물1</h5>
            <div class="bookcard-info-subtitle font-body-4" style="margin-left: 0.2rem;margin-bottom: 0.5rem;">J.K. 롤링</div>
            <div class="bookcard-info-subtitle font-body-5" style="margin-left: 0.2rem;margin-bottom: 0.5rem;">문학수첩</div>
          </div>
        </div>`,
        cancelButtonText: "닫기",
      });
    },
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        console.log("subscriber : " + subscriber);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.nickname })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      Swal.fire({
        showCancelButton: true,
        title: "미팅 퇴장",
        text: "미팅을 나가시겠습니까?",
        confirmButtonText: "네, 나갈래요",
        cancelButtonText: "취소",
      }).then((result) => {
        if (result.isConfirmed) {
          if (this.session) this.session.disconnect();

          this.session = undefined;
          this.mainStreamManager = undefined;
          this.publisher = undefined;
          this.subscribers = [];
          this.OV = undefined;

          window.removeEventListener("beforeunload", this.leaveSession);
        }
      });
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>
<style scoped>
.meeting-navbar {
  background-color: var(--light-orange);
}

.meeting-navbar-title {
  justify-self: center;
  margin: 0;
}

.meeting-navbar-book {
  justify-self: left;
}

.video-wrapper {
  position: relative;
}

.bookcard-background {
  width: 100%;
  margin: 5% auto;
  padding-bottom: 20px;
  background-color: #f3f3f3;
  border-radius: 10px;
}
</style>
