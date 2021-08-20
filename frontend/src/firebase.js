import firebase from "firebase/app";
import "firebase/messaging";
import "firebase/database";

var firebaseConfig = {
  apiKey: "AIzaSyDqaPuWTSRcT5qgLNWWX1HMz0jWnF40rAo",
  authDomain: "booktory-48a94.firebaseapp.com",
  databaseURL: "https://booktory-48a94-default-rtdb.firebaseio.com",
  projectId: "booktory-48a94",
  storageBucket: "booktory-48a94.appspot.com",
  messagingSenderId: "304013338948",
  appId: "1:304013338948:web:5a29508fb36bd65aa2cf11",
  measurementId: "G-FSB84LYMXF",
};
var fire = firebase.initializeApp(firebaseConfig);

export default fire;
