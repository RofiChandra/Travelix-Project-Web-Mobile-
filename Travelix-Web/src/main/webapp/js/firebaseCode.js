  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyDJit-hZvXbfFyTjSRTOWWyIH0mHcRpwQw",
    authDomain: "contohlogin-e9e74.firebaseapp.com",
    databaseURL: "https://contohlogin-e9e74-default-rtdb.firebaseio.com",
    projectId: "contohlogin-e9e74",
    storageBucket: "contohlogin-e9e74.appspot.com",
    messagingSenderId: "581250683251",
    appId: "1:581250683251:web:1454bd7c5412d9ee57b506",
    measurementId: "G-JGKHDL51LY"
};
  // Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.auth.Auth.Persistence.LOCAL
const auth = firebase.auth()
const database = firebase.database()

firebase.analytics();