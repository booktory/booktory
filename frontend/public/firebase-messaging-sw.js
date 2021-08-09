importScripts("https://www.gstatic.com/firebasejs/8.7.1/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.7.1/firebase-messaging.js");

firebase.initializeApp({
  apiKey: "AIzaSyDqaPuWTSRcT5qgLNWWX1HMz0jWnF40rAo",
  authDomain: "booktory-48a94.firebaseapp.com",
  databaseURL: "https://booktory-48a94-default-rtdb.firebaseio.com",
  projectId: "booktory-48a94",
  storageBucket: "booktory-48a94.appspot.com",
  messagingSenderId: "304013338948",
  appId: "1:304013338948:web:5a29508fb36bd65aa2cf11",
  measurementId: "G-FSB84LYMXF",
});

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log("[firebase-messaging-sw.js] Received background message ", payload);
  // Customize notification here
  const notificationTitle = "Background Message Title";
  const notificationOptions = {
    body: "Background Message body.",
    icon: "/firebase-logo.png",
  };

  return self.registration.showNotification(notificationTitle, notificationOptions);
});
