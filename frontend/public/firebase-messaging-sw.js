importScripts("https://www.gstatic.com/firebasejs/8.7.1/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.7.1/firebase-messaging.js");

firebase.initializeApp({
  apiKey: "AIzaSyDqaPuWTSRcT5qgLNWWX1HMz0jWnF40rAo",
  projectId: "booktory-48a94",
  messagingSenderId: "304013338948",
  appId: "1:304013338948:web:5a29508fb36bd65aa2cf11",
});

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log("[firebase-messaging-sw.js] Received background message ", payload);
  // Customize notification here
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: "/firebase-logo.png",
  };

  return self.registration.showNotification(notificationTitle, notificationOptions);
});
