<template>
  <div class="container">
    <div>
      <div class="navbar meeting-navbar">
        <div class="icon" @click="leaveSession">
          <icon-base><icon-arrow-left /></icon-base>
        </div>
        <h5 class="meeting-navbar-title">{{ this.clubInfo.name }}</h5>
        <div class="icon meeting-navbar-book" @click="showBookInfo">
          <icon-base><icon-book /></icon-base>
        </div>
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
        />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";
// import router from "@/router";
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
    };
  },
  computed: {
    ...mapState(["userNickname"]),
    ...mapState("clubStore", ["clubInfo", "meetingInfo"]),
  },
  created() {
    this.mySessionId = `Session${this.meetingInfo.bookclubId}`;
    // this.joinSession();
  },
  mounted() {
    this.joinSession();
  },
  methods: {
    ...mapActions("bookclubStore", ["leaveMeeting"]),
    showBookInfo() {
      Swal.fire({
        html: `
        <div
          style="
            width: auto;
            padding: 1rem 0 0.4rem;
            border-radius: 10px;
            display: flex;
            align-items: center;
            gap: 1.6rem;
          "
        >
          <img
            src="${this.clubInfo.thumbnail}"
            alt="bookThumbnail"
            style="
              width: 7rem;
              height: 10rem;
              border-radius: 1rem;
              box-shadow: 0 0.2rem 0.4rem 0 var(--bg-black);
            "
          />
          <div
            style="
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: flex-start;
            "
          >
            <span class="font-body-4" style="color: var(--grey)">읽고 있는 책</span>
            <h5 
              class="title" 
              style="
                margin: 0.7rem 0 0.8rem;
                text-align: left;
                ">${
                  this.clubInfo.title.length > 25
                    ? this.clubInfo.title.substring(0, 25) + "・・・"
                    : this.clubInfo.title
                }</h5>
            <span class="font-body-5">${
              this.clubInfo.author.length > 9
                ? this.clubInfo.author.substr(0, 9) + "・・・"
                : this.clubInfo.author
            }&nbsp;|&nbsp;${
          this.clubInfo.publisher.length > 9
            ? this.clubInfo.publisher.substr(0, 9) + "・・・"
            : this.clubInfo.publisher
        }
            </span>
          </div>
        </div>
        `,
        showConfirmButton: false,
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
        // console.log("token : " + token);
        this.session
          .connect(token, { clientData: this.userNickname })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let pc_device = "win16|win32|win64|mac|macintel";
            let this_device = navigator.platform;
            let video_size = "";
            if (this_device) {
              if (pc_device.indexOf(navigator.platform.toLowerCase()) < 0) {
                video_size = "640x480";
              } else {
                video_size = "480x640";
              }
            }

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: video_size, // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: true, // Whether to mirror your local video or not
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
        title: "모임에서 나가시겠습니까?",
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

          this.leaveMeeting(this.meetingInfo.bookclubId);
          // router.go(-1);
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
  background-color: var(--medium-orange);
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
</style>
