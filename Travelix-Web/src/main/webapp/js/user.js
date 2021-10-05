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

function admin() {
    window.location.href = "login-admin.html";
}

function addUser(){
    var fullnameText = document.getElementById("reg_full_name").value;
    var phonenumberText = document.getElementById("reg_phone_number").value;
    var passwordText = document.getElementById("reg_password").value;
    var emailText = document.getElementById("reg_email").value;
    // var addressText = document.getElementById("address").value;

    firebase.auth().createUserWithEmailAndPassword(emailText, passwordText)
    .then((success)=>{
        var user = firebase.auth().currentUser;
        var uid;
        if(user!= null){
            uid = user.uid;
            var userRef = firebase.database().ref('/User');
        var userData = {
            // userid : uid,
            name : fullnameText,
            phone : phonenumberText,
            email : emailText
            // address : addressText
        }
        userRef.child(uid).set(userData);
        alert("Account registered!");
        window.location.href = "login2.html";
        }

        
    }).catch((error)=>{
        alert(error.code + " " + error.message);
    })
};

function logIn(){
    var passwordText = document.getElementById("password").value;
    var emailText = document.getElementById("email").value;

    firebase.auth().signInWithEmailAndPassword(emailText, passwordText)
    .then((success)=>{        
        alert("Account logged in!");
        window.location.href= "index.html";
    }).catch((error)=>{
        alert(error.code + " " + error.message);
    })
};

firebase.auth().onAuthStateChanged((user) => {
    if(user){
        let user = firebase.auth().currentUser;
        let uid;
        if(user != null){
            uid = user.uid;
        }
        
        let firebaseRefKey = firebase.database().ref('/User').child(uid);
        firebaseRefKey.on("value", (dataSnapshot)=>{
		
            
            document.getElementById("profile_fullName").value = dataSnapshot.val().name;            
            document.getElementById("profile_email").value = dataSnapshot.val().email;
            document.getElementById("profile_phoneNumber").value = dataSnapshot.val().phone;
            // document.getElementById("profile_address").value = dataSnapshot.val().address;
            
        })
    }else{
        alert("No Active user");
    }
});

function updateProfile(){
    var fullnameText = document.getElementById("profile_fullName").value;
    var emailText = document.getElementById("profile_email").value;
    var phonenumberText = document.getElementById("profile_phoneNumber").value;
    // var addressText = document.getElementById("profile_address").value;

    let user = firebase.auth().currentUser;
    let uid;
    if(user != null){
        uid = user.uid;
    }
    let usersRef = firebase.database().ref('/User');
    const newData ={        
        name : fullnameText,
        phone : phonenumberText,
        email : emailText
        // address : addressText
    };
    usersRef.child(uid).update(newData);
    alert("Profile Data Updated!");
    window.location.href ="biodata.html";
}

function signOut(){
    var ans = confirm("Are you sure want to log out?");
    if (ans == true){
        firebase.auth().signOut().then(function(){
            alert("Logged out. See you!");
            window.location.href = "login2.html"
        }).catch(function(error){
            alert(error.message)
        })
    }
    
}

const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener('click', () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener('click', () => {
    container.classList.remove("sign-up-mode");
});