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
const HotelName = document.getElementById('HotelName');
const roomType = document.getElementById('RoomType');
const guestName = document.getElementById('GuestName');
const guestPhone = document.getElementById('PhoneNumber');
const guestEmail = document.getElementById('email');
const checkIn = document.getElementById('CheckIn');
const checkOut = document.getElementById('CheckOut');
const tripType = document.getElementById('TripType')
const bookButton = document.getElementById('bookButton');

// const database = firebase.database();
//const db = firebase.firestore();
// const usersRef = database.ref('Booking');

// bookButton.addEventListener('click', e => {
//     e.preventDefault();
//     usersRef.child(GuestName.value).set({
//         RoomType: RoomType.value,
//         GuestName: GuestName.value,
//         PhoneNumber: PhoneNumber.value,
//         TripType: TripType.value,
//         CheckIn: CheckIn.value,
//         CheckOut: CheckOut.value,
//         email: email.value
//     });
//     window.alert("Your Booking")
// });

function addBook() {
    
    roomType = document.getElementById('RoomType').value
    guestName = document.getElementById('GuestName').value
    guestPhone = document.getElementById('PhoneNumber').value
    guestEmail = document.getElementById('email').value
    checkIn = document.getElementById('CheckIn').value
    checkOut = document.getElementById('CheckOut').value
    tripType = document.getElementById('TripType').value

    var rootRef = firebase.database().ref().child("Booking");
    // var userID = firebase.auth().currentUser.uid;
    // var bookRef = rootRef.child(userID);

    if (roomType != "" && guestName != "" && guestPhone != "" && guestEmail != "" && checkIn != "" && checkOut != "" && tripType != "") {
        var bookData = {            
            roomType: roomType.value,
            guestName: guestName.value,
            guestPhone: guestPhone.value,
            tripType: tripType.value,
            checkIn: checkIn.value,
            checkOut: checkOut.value,
            guestEmail: guestEmail.value
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
            window.alert("Your Booking is Success! Thank You :)");
            window.location.href = "index.html";
        }
        });
    }
    else {
        window.alert("Please fill all field.");
    }    
}