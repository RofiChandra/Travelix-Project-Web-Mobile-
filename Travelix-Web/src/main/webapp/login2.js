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






// Firebase Realtime Database Rules: https://firebasetutorials.com/firebase-realtime-database-rules/

function register () {
    email = document.getElementById('reg_email').value
    password = document.getElementById('reg_password').value
    confirm_password = document.getElementById('confirm_password').value

    if (email != "" && password != "" && confirm_password != "") {
      if(password == confirm_password) {
        var result = firebase.auth().createUserWithEmailAndPassword(email, password);

      result.catch(function(error) {
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(errorCode);
        console.log(errorMessage);
        window.alert("Message: " + errorMessage);
      });
      // window.location.href = "index.html"
      }
      else {
        window.alert("Password don't match with Confirm Password!");
      }
      
    } else {
      window.alert("Please fill all fields!");
    }
    
  
    // // Validate email
    // if (ValidateEmail(email) == false) {
    //   alert('Incorrect Email')
    //   return
    // }
  
    // // Validate password (Firebase auth insists on a password length of 6 characters)
    // if (password.length < 6) {
    //   alert('Create a Longer Password')
    //   return
    // }
  
    // // Check if other values are empty
    // if (full_name.length <= 0 || phone_number.length <= 0) {
    //   alert('Missing Fields')
    //   return
    // }
  
    // auth.createUserWithEmailAndPassword(email, password)
    // .then(() => {
    //   // Assign user
    //   var user = auth.currentUser
    //   // Save input data to firebase database
    //   var database_ref = database.ref()
    //   // Create user data to save to Firebase Realtime Database
    //   var user_data = {
    //     full_name: full_name,
    //     email: email,
    //     phone_number: phone_number,
    //     user_created: Date.now()
    //   }
    //   // Save user_data under the unique user.uid
    //   database_ref.child('/users' + user.uid).set(user_data)
    //   // Wait 1 second. You don't have to but why not.
    //   // Then alert the user
    //   setTimeout(function () {
    //     alert('User Created!')
    //   }, 1000)
    // })
    // .catch((error) => {
    //   // Handle Errors as they occur.
    //   var errorCode = error.code
    //   var errorMessage = error.message
    //   alert(errorMessage)
    // })
}
function login () {
    email = document.getElementById('email').value
    password = document.getElementById('password').value
    
    if (email != "" && password != "") {
      var result = firebase.auth().signInWithEmailAndPassword(email, password);

      result.catch(function(error) {
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(errorCode);
        console.log(errorMessage);
        window.alert("Message: " + errorMessage);
      })
      // window.location.href = "index.html"
    } else {
      window.alert("Please fill all fields!");
    }
    // auth.signInWithEmailAndPassword(email, password)
    // .then(() => {
    //   // Assign user
    //   var user = auth.currentUser
    //   // Update user data in firebase database
    //   var database_ref = database.ref()
    //   // Update the user_data parameters under the user.uid
    //   database_ref.child('users/' + user.uid)
    //   // Set timeout and log in
    //   setTimeout(function () {
    //     alert('User Logged In!')
    //   }, 1000)
    //   window.location = "index.html"
    // })
    // .catch((error) => {
    //   // Handle Errors here.
    //   var errorCode = error.code
    //   var errorMessage = error.message
    //   alert(errorMessage)
    // })
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

  function signOut() {
        firebase.auth().signOut();
    }

  // function reset() {
  //   var auth = firebase.auth();
  //   var email = document.getElementById('password').value

  //   if (email != "") {
  //     auth.sendPasswordResetEmail(email).then(function() 
  //     {
  //       window.alert("Email has been sent to you, Please check and verify.")
  //     })
  //     .catch(function(error) 
  //     {
  //       var errorCode = error.code;
  //       var errorMessage = error.message;
        
  //       console.log(errorCode);
  //       console.log(errorMessage);

  //       window.alert("Message: " + errorMessage);
  //     });
  //   }
  //   else {
  //     window.alert("Fill ur email please.");
  //   }
  // }

    function changePassword() {
      var auth = firebase.auth()
      var email = document.getElementById('resetEmail').value

      if(email != "") {
        auth.sendPasswordResetEmail(email).then(function() {
          window.alert("Email has been sent to you, Please check and verify.")
          window.location.href = "login2.html"
        })
        .catch(function(error) {
          var errorCode = error.code;
          var errorMessage = error.message;

          console.log(errorCode);
          console.log(errorMessage);

          window.alert("Message: " + errorMessage);
        });
        
      }
      else {
        window.alert("Please write your email");
      }
  }

function addProfile() {
  

  full_name = document.getElementById('full_name').value
  phone_number = document.getElementById('phone_number').value
  gender = document.getElementById('gender').value
  address = document.getElementById('address').value
  
  var rootRef = firebase.database().ref().child("Users");
  var userID = firebase.auth().currentUser.uid;
  var usersRef = rootRef.child(userID);
  // var bootRef = firebase.database().ref().child("Profile");
  // var profileRef = rootRef.child(userID);

  if (full_name != "" && phone_number != "" && gender != "" && address != "") {
    var userData = {
      "fullName": full_name,
      "phoneNumber": phone_number,
      "Gender": gender,
      "Address": address,
      "uid": userID
    };

    usersRef.set(userData, function(error) 
    {
      if (error) {
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(errorCode);
        console.log(errorMessage);

        window.alert("Message: " + errorMessage);
      }
      else {
        window.location.href = "index.html";
      }
    });
  }
  else {
    window.alert("Please fill all field.");
  }
}

function admin() {
  window.location.href = "login-admin.html";
}

function addBooking() {
  
  hotel_name = document.getElementById('HotelName').value
  room_type = document.getElementById('RoomType').value
  guest_name = document.getElementById('GuestName').value
  phone_number = document.getElementById('PhoneNumber').value
  email = document.getElementById('book_email').value
  check_in = document.getElementById('CheckIn').value
  check_out = document.getElementById('CheckOut').value
  trip_type = document.getElementById('TripType').value
  
  
  var rootRef = firebase.database().ref().child("Booking");
  
  var bookRef = rootRef;
  // var bootRef = firebase.database().ref().child("Profile");
  // var profileRef = rootRef.child(userID);

  if (trip_type != "" && check_out != "" && check_in != "" && email != "" && hotel_name != "" && phone_number != "" && room_type != "" && guest_name != "") {
    var bookData = {
      "hotelName": hotel_name,
      "phoneNumber": phone_number,
      "roomType": room_type,
      "guestName": guest_name,
      "email": email,
      "checkIn": check_in,
      "checkOut": check_out,
      "tripType": trip_type,
      "uid": userID
    };

    bookRef.set(bookData, function(error) 
    {
      if (error) {
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(errorCode);
        console.log(errorMessage);

        window.alert("Message: " + errorMessage);
      }
      else {
        window.location.href = "index.html";
        window.alert("Thank You for Your Booking.")
      }
    });
  }
  else {
    window.alert("Please fill all field.");
  }
}

//   // Initialize Firebase
// firebase.initializeApp(firebaseConfig);
// firebase.analytics();

// const auth = firebase.auth();

// function signUp() {
//     var email = document.getElementById("reg_email");
//     var password = document.getElementById("reg_password");

//     const promise = auth.createUserWithEmailAndPassword(email.value, password.value);
//     promise.catch(e => alert(e.message));

//     alert("Signed Up");
// }

// function signIn() {
    
//         var email = document.getElementById("email");
//         var password = document.getElementById("password");

//         const promise = auth.signInWithEmailAndPassword(email.value, password.value);
//         promise.catch(e => alert(e.message));

//         alert("Signed In " + email.value);
//         window.location = "index.html"
    
    

//     //Take user to a different page

// }

// function signOut() {
//     auth.signOut();
//     window.location = "login2.html"
// }

// auth.onAuthStateChanged(function(user) {
//     if (user) {
//         //USER SIGNED IN
//         var email = user.email;
//         alert("Active User " + email);
//     }else {
//         //NO USER
//         alert("No Active User")
//     }
// })

const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener('click', () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener('click', () => {
    container.classList.remove("sign-up-mode");
});