// const sign_in_btn = document.querySelector("#sign-in-btn");
// const sign_up_btn = document.querySelector("#sign-up-btn");
// const container = document.querySelector(".container");

// sign_up_btn.addEventListener('click', () => {
//     container.classList.add("sign-up-mode");
// });

// sign_in_btn.addEventListener('click', () => {
//     container.classList.remove("sign-up-mode");
// });





var firebaseConfig = {
    apiKey: "AIzaSyDJit-hZvXbfFyTjSRTOWWyIH0mHcRpwQw",
    authDomain: "contohlogin-e9e74.firebaseapp.com",
    projectId: "contohlogin-e9e74",
    storageBucket: "contohlogin-e9e74.appspot.com",
    messagingSenderId: "581250683251",
    appId: "1:581250683251:web:4979ec039d74137e57b506",
    measurementId: "G-6W24VSWJB6"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();


const auth = firebase.auth();

function signUp() {
    var email = document.getElementById("email");
    var password = document.getElementById("password");

    const promise = auth.createUserWithEmailAndPassword(email.value, password.value);
    promise.catch(e => alert(e.message));

    alert("Signed Up");
}

function signIn() {
    var email = document.getElementById("email");
    var password = document.getElementById("password");

    const promise = auth.signInWithEmailAndPassword(email.value, password.value);
    promise.catch(e => alert(e.message));

    alert("Signed In " + email.value);

    //Take user to a different page

}

function signOut() {
    auth.signOut();
    alert("Signed Out");
}

auth.onAuthStateChanged(function(user) {
    if (user) {
        //USER SIGNED IN
        var email = user.email;
        alert("Active User " + email);
    }else {
        //NO USER
        alert("No Active User")
    }
})