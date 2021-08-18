import SERVER from "@/api/api";
import axios from "axios";
import router from "@/router";
import Swal from "sweetalert2";


const rwmStore = {
  namespaced: true,
  state: {
    rwmRoomInfo: null,
    rwmList: null,
    rwmParticipant: null,
    rwmPlayList: [
      {
        rwmId: 0,
        videoId:"",
        timeStamp: [],
      },
      {
        rwmId: 1,
        videoId:"JkTM9Q5DfgI",
        timeStamp: [
          {
            startTime : 0,
            artist : "aimless",
            title : "Cocoa",
          },
          {
            startTime : 88,
            artist : "Sergeant Jay",
            title : "Nobody's Heart",
          },
          {
            startTime : 247,
            artist : "Crazyjazz",
            title : "It Works",
          },
          {
            startTime : 465,
            artist : "iwamizu",
            title : "残夢 (Zanmu)",
          },
          {
            startTime : 642,
            artist : "Jazzinuf",
            title : "Strawberry Bubble Gum",
          },
          {
            startTime : 772,
            artist : "BROCKBEATS",
            title : "September",
          },
          {
            startTime : 897,
            artist : "Kavv",
            title : "Adorable",
          },
          {
            startTime : 1013,
            artist : "jinsang",
            title : "Portland",
          },
          {
            startTime : 1124,
            artist : "Halberd",
            title : "she doesnt know me",
          },
          {
            startTime : 1257,
            artist : "Sergeant Jay",
            title : "Daydreaming",
          },
          {
            startTime : 1427,
            artist : "Peachy!",
            title : "Amore",
          },
          {
            startTime : 1544,
            artist : "Jazzinuf",
            title : "Hugging You (Quietly)",
          },
          {
            startTime : 1662,
            artist : "flavors",
            title : "Honey (feat. Two Sleepy)",
          },
          {
            startTime : 1722,
            artist : "Fragile.",
            title : "Is This What You Wanted?",
          },
          {
            startTime : 1823,
            artist : "Normal",
            title : "Cielo",
          },
          {
            startTime : 1973,
            artist : "Minthaze",
            title : "Divinity",
          },
          {
            startTime : 2078,
            artist : "Wun Two",
            title : "Vìo",
          },
          {
            startTime : 2183,
            artist : "Dominant",
            title : "Change In Colour",
          },
          {
            startTime : 2338,
            artist : "Flughand",
            title : "Arli",
          },
          {
            startTime : 2452,
            artist : "garba9",
            title : "Cozy Sofa",
          },          
        ]
      },
      {
        rwmId:2,
        videoId: "iqXF38SjRdc",
        timeStamp: [
          {
            startTime : 1,
            artist : "Rook1e",
            title : "Have Yourself A Merry Little Christmas (feat. J'san)",
          },
          {
            startTime : 115,
            artist : "Majime",
            title : "Og Christmas Swing",
          },
          {
            startTime : 283,
            artist : "lofi",
            title : "While You Were Sleeping",
          },
          {
            startTime : 385,
            artist : "Soft Eyez",
            title : "Winter Wonderland",
          },
          {
            startTime : 497,
            artist : "Limes",
            title : "Wonderland",
          },
          {
            startTime : 624,
            artist : "softy",
            title : "Last Snow (Feat. Elijah Lee)",
          },
          {
            startTime : 808,
            artist : "Abe M Beats",
            title : "Walk in the snow",
          },
          {
            startTime : 930,
            artist : "Dominant",
            title : "Snow Walk",
          },
          {
            startTime : 1143,
            artist : "Wun Two",
            title : "Snowing",
          },
          {
            startTime : 1268,
            artist : "Aso",
            title : "Snug",
          },
          {
            startTime : 1434,
            artist : "moedoisnice",
            title : "Snowfall",
          },
          {
            startTime : 1546,
            artist : "jinsang",
            title : "Smile From U.",
          },
          {
            startTime : 1749,
            artist : "Byte",
            title : "christmas",
          },
          {
            startTime : 1833,
            artist : "saib.",
            title : "Snow",
          },
          {
            startTime : 1938,
            artist : "Rook1e",
            title : "White Christmas (feat. Jobe & Ibrahim)",
          },
          {
            startTime : 2018,
            artist : "Simon Jefferis",
            title : "Bella",
          },
          {
            startTime : 2164,
            artist : "invention_",
            title : "snowluv",
          },
          {
            startTime : 2325,
            artist : "softy",
            title : "when it snows (Feat. smile for me.)",
          },
          {
            startTime : 2516,
            artist : "Mittensさん",
            title : "sharing a blanket with you.",
          },
          {
            startTime : 2630,
            artist : "Idealism",
            title : "Snowfall",
          },
          {
            startTime : 2807,
            artist : "lofi",
            title : "Coffee And Cigarettes, 2003",
          },
          {
            startTime : 2955,
            artist : "Rook1e",
            title : "O Tannebaum (feat. Jobe)",
          },
          {
            startTime : 3031,
            artist : "Abe M Beats",
            title : "Log Cabin",
          },
          {
            startTime : 3129,
            artist : "Wishkid",
            title : "Santa Is Coming",
          },
          {
            startTime : 3297,
            artist : "Middle School, Aso",
            title : "warm for the winter",
          },
          {
            startTime : 3460,
            artist : "Mittensさん",
            title : "as the snow falls",
          },
          {
            startTime : 3642,
            artist : "Luv.Ly",
            title : "December.",
          },
          {
            startTime : 3765,
            artist : "Dominant",
            title : "Winter's Here",
          },
          {
            startTime : 3844,
            artist : "Jazzinuf",
            title : "Fire Place",
          },
          {
            startTime : 3983,
            artist : "Tomppabeats",
            title : "The Way She",
          },
          {
            startTime : 4031,
            artist : "Juan RIOS",
            title : "Pine Trees",
          },
          {
            startTime : 4198,
            artist : "Oui lele",
            title : "mellow night",
          },
          {
            startTime : 4318,
            artist : "Kavv",
            title : "Coffee Shop Date",
          },
          {
            startTime : 4438,
            artist : "Nohidea",
            title : "Apple_Tree",
          },
          {
            startTime : 4513,
            artist : "Lame Kids",
            title : "Official Tea Taster",
          },
          {
            startTime : 4603,
            artist : "chuckee.",
            title : "Trippin'",
          },
          {
            startTime : 4767,
            artist : "Jazzinuf",
            title : "Be Mine",
          },
          {
            startTime : 4974,
            artist : "moow",
            title : "Wake Up",
          },
          {
            startTime : 5057,
            artist : "Sergeant Jay",
            title : "Nobody's Heart",
          },
          {
            startTime : 5216,
            artist : "aimless",
            title : "Cocoa",
          },
          {
            startTime : 5306,
            artist : "baaskaT",
            title : "Grass",
          },
          {
            startTime : 5403,
            artist : "Philanthrope",
            title : "Aspiration",
          },
          {
            startTime : 5526,
            artist : "Samiyam",
            title : "Italy",
          },
          {
            startTime : 5669,
            artist : "Philanthrope",
            title : "Melt",
          },
          {
            startTime : 5775,
            artist : "Louk",
            title : "Captain Calvin",
          },
          {
            startTime : 5935,
            artist : "9o To Museum",
            title : "Dance With Ma Mood",
          },
        ]
      },
      {
        rwmId:3,
        videoId: "w9TfavGsQWI",
        timeStamp: [
          {
            startTime : 1,
            artist : "Chelsea Cutler",
            title : "better off",
          },
          {
            startTime : 194,
            artist : "Andrew & Veda",
            title : "Where Did You Come From (Acoustic)",
          },
          {
            startTime : 409,
            artist : "Imaginary Future",
            title : "Graceland",
          },
          {
            startTime : 590,
            artist : "Anna Leone",
            title : "Into The Cold",
          },
          {
            startTime : 818,
            artist : "Angus & Julia Stone",
            title : "Wherever You Are",
          },
          {
            startTime : 1040,
            artist : "John Mayer",
            title : "Stop This Train",
          },
          {
            startTime : 1326,
            artist : "SYML",
            title : "Girl (Acoustic)",
          },
          {
            startTime : 1538,
            artist : "Toby Johnson",
            title : "Tiny Fires",
          },
          {
            startTime : 1795,
            artist : "Angus & Julia Stone",
            title : "Bella",
          },
          {
            startTime : 2044,
            artist : "A Great Big World",
            title : "Where Does the Time Go",
          },
          {
            startTime : 2273,
            artist : "Kina Grannis",
            title : "Magic",
          },
          {
            startTime : 2505,
            artist : "Angelo De Augustine",
            title : "Time",
          },
          {
            startTime : 2742,
            artist : "Dylan LeBlanc",
            title : "Jack",
          },
          {
            startTime : 2906,
            artist : "Ray LaMontagne",
            title : "A Falling Through",
          },
          {
            startTime : 3171,
            artist : "Laura Marling",
            title : "For You",
          },
        ]
      },
      {
        rwmId:4,
        videoId: "TNkM5_nf01U",
        timeStamp: [
          {
            startTime : 1,
            artist : "keshi",
            title : "2 soon",
          },
          {
            startTime : 206,
            artist : "slchld",
            title : "she likes spring, I prefer winter.",
          },
          {
            startTime : 414,
            artist : "heyden",
            title : "miller",
          },
          {
            startTime : 583,
            artist : "Authentic",
            title : "Solioquy (Feat. Slchld)",
          },
          {
            startTime : 718,
            artist : "Authentic",
            title : "Take 1",
          },
          {
            startTime : 828,
            artist : "keshi",
            title : "like i need u",
          },
          {
            startTime : 1011,
            artist : "RINI",
            title : "My Favourite Clothes",
          },
          {
            startTime : 1268,
            artist : "eli.",
            title : "say those things",
          },
          {
            startTime : 1447,
            artist : "Ant Saunders",
            title : "I Had A Love Song (feat. VanJess)",
          },
          {
            startTime : 1603,
            artist : "Dept",
            title : "Autumn breeze (Feat. Milky Day)",
          },
          {
            startTime : 1778,
            artist : "Sarah Kang",
            title : "Drive (Feat. Kevin Chung)",
          },
          {
            startTime : 1980,
            artist : "Niia",
            title : "Black Dress (CODE KUNST Remix)",
          },
          {
            startTime : 2151,
            artist : "Paige",
            title : "Cold Blooded",
          },
          {
            startTime : 2321,
            artist : "Ok2222",
            title : "Someone New",
          },
          {
            startTime : 2481,
            artist : "UMI",
            title : "Frequently",
          },
          {
            startTime : 2626,
            artist : "Conan Gray",
            title : "Comfort Crowd",
          },
          {
            startTime : 2801,
            artist : "Pink Sweat$",
            title : "At My Worst",
          },
          {
            startTime : 2972,
            artist : "Jesse Barrera",
            title : "Unaware",
          },
          {
            startTime : 3201,
            artist : "Bruno Major",
            title : "Regent’s Park",
          },
          {
            startTime : 3379,
            artist : "sakehands",
            title : "LUCK (feat. Jessica Domingo)",
          },
          {
            startTime : 3550,
            artist : "HONNE",
            title : "Day 1 ◑",
          },
          {
            startTime : 3784,
            artist : "brb.",
            title : "whoops",
          },
          {
            startTime : 3932,
            artist : "Of Methodist",
            title : "Bayfront Stn",
          },
          {
            startTime : 4159,
            artist : "Peach Tree Rascals",
            title : "Plus",
          },
          {
            startTime : 4432,
            artist : "heyden",
            title : "Parking lot",
          },
          {
            startTime : 4662,
            artist : "guardin",
            title : "alone in the attic",
          },
          {
            startTime : 4775,
            artist : "Powfu",
            title : "no promises",
          },
          {
            startTime : 4964,
            artist : "Powfu",
            title : "death bed (coffee for your head)",
          },
          {
            startTime : 5138,
            artist : "Hans.",
            title : "Empties",
          },
          {
            startTime : 5271,
            artist : "Hiyo",
            title : "Don't Go",
          },
        ]
      },
      {
        rwmId:5,
        videoId: "itXDhRsf9WA",
        timeStamp: [
          {
            startTime : 1,
            artist : "Absofacto",
            title : "Rewind",
          },
          {
            startTime : 144,
            artist : "Sista Prod",
            title : "Eyes Blue Like The Atlantic, Pt. 2 (feat. Powfu, Alec Benjamin & Rxseboy)",
          },
          {
            startTime : 301,
            artist : "Disclosure",
            title : "Birthday",
          },
          {
            startTime : 520,
            artist : "Maeta",
            title : "Teen Scene (feat. Buddy)",
          },
          {
            startTime : 771,
            artist : "HAIM",
            title : "Now I'm In It",
          },
          {
            startTime : 976,
            artist : "Johnny Stimson",
            title : "Flower",
          },
          {
            startTime : 1149,
            artist : "Otis Kane",
            title : "Do Or Die",
          },
          {
            startTime : 1316,
            artist : "Snowk",
            title : "This Is Good (feat. Miraa May)",
          },
          {
            startTime : 1520,
            artist : "NIKI",
            title : "Nightcrawlers",
          },
          {
            startTime : 1728,
            artist : "Alycia Bella",
            title : "Cue The Sun",
          },
          {
            startTime : 1947,
            artist : "JUNG",
            title : "Call Me Up",
          },
          {
            startTime : 2111,
            artist : "James Righton",
            title : "Edie",
          },
          {
            startTime : 2345,
            artist : "Summer Heart",
            title : "Buckle Up",
          },
          {
            startTime : 2491,
            artist : "Jemme",
            title : "Shape Of My Heart",
          },
          {
            startTime : 2665,
            artist : "Talitha.",
            title : "On You",
          },
          {
            startTime : 2820,
            artist : "India Shawn",
            title : "Water Me (feat. Alex Isley, Ré Lxuise)",
          },
          {
            startTime : 3065,
            artist : "Favor",
            title : "Girl (You Got Me Good)",
          },
          {
            startTime : 3221,
            artist : "Blxst",
            title : "Overrated",
          },
          {
            startTime : 3369,
            artist : "Kemi Ade",
            title : "5ADAY",
          },
          {
            startTime : 3566,
            artist : "Dune",
            title : "Save Yourself First",
          },
          {
            startTime : 3815,
            artist : "Goss",
            title : "Fighting For The Gospel (feat. Selma Judith)",
          },
          {
            startTime : 4015,
            artist : "Omar Apollo",
            title : "Algo (feat. Drayco McCoy)",
          },
          {
            startTime : 4184,
            artist : "Leah Senior",
            title : "Dreary Day",
          },
          {
            startTime : 4344,
            artist : "AUDREY NUNA",
            title : "Long Night",
          },
          {
            startTime : 4496,
            artist : "MARKET",
            title : "Inner Smile (feat. Lauren Abineri)",
          },
        ]
      },
      {
        rwmId:6,
        videoId: "cyF9W8ZjbR0",
        timeStamp: [
          {
            startTime : 1,
            artist : "Charlotte Sands",
            title : "Dress",
          },
          {
            startTime : 143,
            artist : "OPO",
            title : "See You Through",
          },
          {
            startTime : 339,
            artist : "Maori",
            title : "Did It for You",
          },
          {
            startTime : 501,
            artist : "Foley",
            title : "Rendezvous",
          },
          {
            startTime : 694,
            artist : "Disco Despair",
            title : "Keep Hiding (Feat. Sheare)",
          },
          {
            startTime : 902,
            artist : "Asher Angel",
            title : "Getaway",
          },
          {
            startTime : 1132,
            artist : "Surfaces",
            title : "Wave of You",
          },
          {
            startTime : 1346,
            artist : "John K",
            title : "cheap sunglasses",
          },
          {
            startTime : 1546,
            artist : "Finn Askew",
            title : "Cherry Bomb",
          },
          {
            startTime : 1743,
            artist : "Mark Mendy",
            title : "Talk To Me",
          },
          {
            startTime : 1891,
            artist : "Johnny Orlando",
            title : "I Don't",
          },
          {
            startTime : 2066,
            artist : "Nova Miller",
            title : "Girls Like Us",
          },
          {
            startTime : 2218,
            artist : "Ezra Jordan",
            title : "Low",
          },
          {
            startTime : 2413,
            artist : "Austin Mahone",
            title : "Anxious",
          },
          {
            startTime : 2575,
            artist : "Reiley",
            title : "Let It Ring",
          },
          {
            startTime : 2744,
            artist : "Vince Harder",
            title : "Signs",
          },
          {
            startTime : 2932,
            artist : "12AM",
            title : "come over",
          },
          {
            startTime : 3058,
            artist : "JUNG",
            title : "Nobody Knows",
          },
          {
            startTime : 3213,
            artist : "Yultron",
            title : "Us2",
          },
          {
            startTime : 3413,
            artist : "5 After Midnight",
            title : "Talk",
          },
          {
            startTime : 3609,
            artist : "ØZI",
            title : "JUST DO YOU (feat. sunkis)",
          },
          {
            startTime : 3794,
            artist : "Anthony Ramos",
            title : "Stop",
          },
          {
            startTime : 3985,
            artist : "Boy In Space",
            title : "On A Prayer (feat. SHY Martin)",
          },
          {
            startTime : 4139,
            artist : "JESSIA",
            title : "I'm not Pretty (feat. Bebe Rexha) (Remix)",
          },
          {
            startTime : 4258,
            artist : "Conan Gray",
            title : "Overdrive",
          },
          {
            startTime : 4441,
            artist : "Mike Posner",
            title : "Jealousy (feat. blackbear)",
          },
          {
            startTime : 4653,
            artist : "Anthony Ramos",
            title : "Come Back Home",
          },
        ]
      },
    ]
  },
  getters: {
    rwmRoomInfo(state) {
      return state.rwmRoomInfo;
    },
    rwmList(state) {
      return state.rwmList;
    },
    rwmParticipant(state) {
      return state.rwmParticipant;
    }
  },
  mutations: {
    SET_RWMROOMINFO(state, data) {
      state.rwmRoomInfo = data;
    },
    SET_RWMLIST(state, data) {
      state.rwmList = data;
    },
    SET_PARTICIPANTLIST(state, data) {
      state.rwmParticipant = data;
    },
  },
  actions: {
    findRwmRoomInfo({ commit }, rwmId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmRoomInfo + rwmId)
        .then((res) => {
          commit("SET_RWMROOMINFO", res.data);
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        })
    },
    findRwmList({ commit }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmList)
        .then((res) => {
          commit("SET_RWMLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    findRwmParticipant({ commit }, rwmId) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getRwmParticipant + rwmId + "/users")
        .then((res) => {
          console.log(res.data);
          commit("SET_PARTICIPANTLIST", res.data);
          let swalHtml = `<div style="display:flex; flex-direction: column; justify-content:flex-start; align-items:flex-start;">`;
          for (var participant of res.data.userList) {
            swalHtml += `
                        <div style="margin: 0.3rem; display:flex; justify-content:flex-start; align-items:center;">
                          <img src="` + participant.profileImg + `" style="display: inline-block; width: 4em; height: 4em; border-radius: 10em;"/>
                          <div style="margin-left: 1rem; display:flex; flex-direction: column; justify-content:flex-start; align-items:flex-start;">
                            <div><b>` + participant.userNickName + `</b></div>
                            <div> ` + participant.bookName + ` 읽는중 </div>
                          </div>
                        </div>
                        `
          }
          swalHtml += `</div>`;
          Swal.fire({
            showCancelButton: false,
            showCloseButton: true,
            showConfirmButton: false,
            title: "참가자("+res.data.userList.length+")",
            html: swalHtml,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    enterRwmRoom({ rootGetters }, rwmEnterData) {
      let rwmBookTitle = {
        title: rwmEnterData.bookTitle,
      };
      axios
        .post(SERVER.URL + SERVER.ROUTES.enterRwmRoom + rwmEnterData.id, rwmBookTitle, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          router.push({ name: "RwmRoom" , query: {id: rwmEnterData.id} });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    exitRwmRoom({ rootGetters }, rwmId) {

      axios
        .delete(SERVER.URL + SERVER.ROUTES.exitRwmRoom + rwmId, rootGetters.config)
        .then((res) => {
          console.log(res.data);
          router.push({ name: 'RwmMain' });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
};

export default rwmStore;