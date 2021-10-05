    // Your web app's Firebase configuration
    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
    var firebaseConfig = {
        apiKey: "AIzaSyCUC43HBGUBYoTl6913BDnEHLD-BwGHIFM",
        authDomain: "database-26a32.firebaseapp.com",
        databaseURL: "https://database-26a32-default-rtdb.firebaseio.com",
        projectId: "database-26a32",
        storageBucket: "database-26a32.appspot.com",
        messagingSenderId: "979435185818",
        appId: "1:979435185818:web:1ad2b3039c2395e0d82123",
        measurementId: "G-G1CYL01QQD"
    };
    

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
const auth = firebase.auth()
const database = firebase.database()

// Firebase Realtime Database Rules: https://firebasetutorials.com/firebase-realtime-database-rules/

function register () {
  email = document.getElementById('email').value
  password = document.getElementById('password').value
  full_name = document.getElementById('full_name').value
  favourite_song = document.getElementById('favourite_song').value
  milk_before_cereal = document.getElementById('milk_before_cereal').value

  // Validate email
  if (ValidateEmail(email) == false) {
    alert('Incorrect Email')
    return
  }

  // Validate password (Firebase auth insists on a password length of 6 characters)
  if (password.length < 6) {
    alert('Create a Longer Password')
    return
  }

  // Check if other values are empty
  if (full_name.length <= 0 || favourite_song.length <= 0 || milk_before_cereal.length <= 0) {
    alert('Missing Fields')
    return
  }

  auth.createUserWithEmailAndPassword(email, password)
  .then(() => {
    // Assign user
    var user = auth.currentUser
    // Save input data to firebase database
    var database_ref = database.ref()
    // Create user data to save to Firebase Realtime Database
    var user_data = {
      full_name: full_name,
      email: email,
      favourite_song: favourite_song,
      milk_before_cereal: milk_before_cereal,
      last_login: Date.now()
    }
    // Save user_data under the unique user.uid
    database_ref.child('users/' + user.uid).set(user_data)
    // Wait 1 second. You don't have to but why not.
    // Then alert the user
    setTimeout(function () {
      alert('User Created!')
    }, 1000)
  })
  .catch((error) => {
    // Handle Errors as they occur.
    var errorCode = error.code
    var errorMessage = error.message
    alert(errorMessage)
  })
}
function login () {
  email = document.getElementById('email').value
  password = document.getElementById('password').value

  auth.signInWithEmailAndPassword(email, password)
  .then(() => {
    // Assign user
    var user = auth.currentUser
    // Update user data in firebase database
    var database_ref = database.ref()
    // User data to be updated
    var user_data = {
      last_login: Date.now()
    }
    // Update the user_data parameters under the user.uid
    database_ref.child('users/' + user.uid).set(user_data)
    // Set timeout and log in
    setTimeout(function () {
      alert('User Logged In!')
    }, 1000)
  })
  .catch((error) => {
    // Handle Errors here.
    var errorCode = error.code
    var errorMessage = error.message
    alert(errorMessage)
  })
}
function ValidateEmail (email) {
  if (email.length <= 0) {
    return false
  }

  if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email)) {
    return true
  } else {
    return false
  }
}