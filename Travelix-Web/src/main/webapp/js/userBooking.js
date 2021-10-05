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
// firebase.initializeApp(firebaseConfig);
firebase.auth.Auth.Persistence.LOCAL
// const auth = firebase.auth()
// const database = firebase.database()

firebase.analytics();

firebase.auth().onAuthStateChanged((user) => {
    if(user){
        let user = firebase.auth().currentUser;
        let uid;
        if(user != null){
            uid = user.uid;
        }
        var path = window.location.pathname;
        var page = path.split("/").pop();

        console.log(page);
        console.log(uid);

        // let firebaseRefKey = firebase.database().ref('/pengguna').child(uid);
        // firebaseRefKey.on("value", (dataSnapshot)=>{
		
            
        //     document.getElementById("fullName").value = dataSnapshot.val().fullname;            
        //     document.getElementById("email").value = dataSnapshot.val().email;
        //     document.getElementById("phoneNumber").value = dataSnapshot.val().phone;
        //     document.getElementById("address").value = dataSnapshot.val().address;
            
        // })

        booksRef = firebase.database().ref('/Books');
        // if (booksRef!= null){
        //     document.getElementById("listBooking").style.display = "none";            
        //     viewMyStories(uid);            
        // }else{
        //     document.getElementById("bookNull").style.display = "block";
            
        // }
        
    }else{
        alert("No Active user");
    }
});

function addBooking(){
    var roomType = document.getElementById('RoomType').value
    var guestName = document.getElementById('fullName').value
    var guestPhone = document.getElementById('phoneNumber').value
    var guestEmail = document.getElementById('email').value
    var checkIn = document.getElementById('CheckIn').value
    var checkOut = document.getElementById('CheckOut').value
    var tripType = document.getElementById('TripType').value
    
    // var currDate = new Date().toISOString().slice(0,10);
    let user = firebase.auth().currentUser;
    let uid;
    let uname;
    if(user != null){
        uid = user.uid;
        let firebaseRefKey = firebase.database().ref('/User').child(uid);
        firebaseRefKey.on("value", (dataSnapshot)=>{            
            // uname = dataSnapshot.val().username;
            // console.log(uname);
            var booksRef = firebase.database().ref('/Books');
            var bookData = {
                roomType: roomType.value,
                guestName: guestName.value,
                guestPhone: guestPhone.value,
                tripType: tripType.value,
                checkIn: checkIn.value,
                checkOut: checkOut.value,
                guestEmail: guestEmail.value,
                userid : uid
                // date : currDate
            }.catch(function(error) {
                var errorCode = error.code;
                var errorMessage = error.message;
      
                console.log(errorCode);
                console.log(errorMessage);
      
                window.alert("Message: " + errorMessage);
              });

            const autoId = booksRef.push().key;
            booksRef.child(autoId).set(bookData);
            // document.getElementById("content").value = "";
            // document.getElementById('RoomType').value = "";
            // document.getElementById('fullName').value = "";
            // document.getElementById('phoneNumber').value = "";
            // document.getElementById('email').value = "";
            // document.getElementById('CheckIn').value = "";
            // document.getElementById('CheckOut').value = "";
            // document.getElementById('TripType').value = "";
            alert("Book Success!");
            var path = window.location.pathname;
        	var page = path.split("/").pop();

 		    if(page == "booking_form_jakarta.html"){
            
                window.location.href = "index.html";
            }
        });
    }
}

// function viewMyStories(uid){
//     const today = new Date().toISOString();
//     let bookRef = firebase.database().ref('/booking').orderByChild('userid').equalTo(uid);
//     bookRef.once('value',
//     function(rec){
//         rec.forEach(
//             function(currentrec){    
//             	var userRef = firebase.database().ref('/users/'+ currentrec.val().userid);
// 			    var username;
// 			    userRef.on('value', (snapshot)=>{
// 			        username = snapshot.val().username;
// 			        console.log(username);   
			        
// 			        document.getElementById("bookingContainer").innerHTML += `
//                     <table class="table">
//                     <thead>
//                         <tr>
//                          <th>S.No</th>
//                          <th>Name</th>
//                          <th>Age</th>
//                          <th>Marks%</th>
//                          <th>Status</th>
//                         </tr>
//                     </thead>
//                     <tbody>
//                           <tr>
//                                 <td data-label="S.No">1</td>
//                                 <td data-label="Name">Dinesh</td>
//                                 <td data-label="Age">34</td>
//                                 <td data-label="Marks%">50%</td>
//                                 <td data-label="Staus">Passed</td>
//                           </tr>
               
//                           <tr>
//                                 <td data-label="S.No">2</td>
//                                 <td data-label="Name">Kamal</td>
//                                 <td data-label="Age">23</td>
//                                 <td data-label="Marks%">70%</td>
//                                 <td data-label="Staus">Passed</td>
//                           </tr>
               
//                           <tr>
//                                 <td data-label="S.No">3</td>
//                                 <td data-label="Name">Neha</td>
//                                 <td data-label="Age">20</td>
//                                 <td data-label="Marks%">90%</td>
//                                 <td data-label="Staus">Passed</td>
//                           </tr>
               
//                           <tr>
//                                 <td data-label="S.No">4</td>
//                                 <td data-label="Name">Priya</td>
//                                 <td data-label="Age">30</td>
//                                 <td data-label="Marks%">30%</td>
//                                 <td data-label="Staus">Failed</td>
//                           </tr>
//                     </tbody>
//                   </table>
			            
// 			            `

			        
// 			    })
                
//             }
//         )
//     })
// }