const HotelName = document.getElementById('HotelName');
const roomType = document.getElementById('RoomType');
const guestName = document.getElementById('GuestName');
const guestPhone = document.getElementById('PhoneNumber');
const guestEmail = document.getElementById('email');
const checkIn = document.getElementById('CheckIn');
const checkOut = document.getElementById('CheckOut');
const tripType = document.getElementById('TripType');
const bookButton = document.getElementById('bookButton');
const HotelUpdateBtn = document.getElementById('HotelUpdateBtn');

// const database = firebase.database();
//const db = firebase.firestore();
const usersRef = database.ref('Booking');

bookButton.addEventListener('click', e => {
	 e.preventDefault();
     usersRef.child(GuestName.value).set({
         roomType: roomType.value,
         guestName: guestName.value,
         guestPhone: guestPhone.value,
         tripType: tripType.value,
         checkIn: checkIn.value,
         checkOut: checkOut.value,
         guestEmail: guestEmail.value
     });
    
 });

HotelUpdateBtn.addEventListener('click', e => {
  e.preventDefault();
  const newData = {
    roomType: roomType.value,
    guestName: guestName.value,
    guestPhone: guestPhone.value,
    tripType: tripType.value,
    checkIn: checkIn.value,
    checkOut: checkOut.value,
    guestEmail: guestEmail.value
  };
  usersRef.child(guestName.value).update(newData);
  
});

HotelRemoveBtn.addEventListener('click', e => {
  e.preventDefault();
  usersRef.child(guestName.value).remove()
      .then(() => {
          console.log('User Deleted!');
      })
      
      .catch(error => {
          console.error(error);
      });
});



// Show Data to Table -------------------------------------------------//
usersRef.on('value' , dataBerhasil , dataGagal);
var isi_tabel = document.getElementById('TblBooking');

function dataBerhasil(data) {

var tampilan_tabel = '';
  data.forEach(function(konten) {
    tampilan_tabel += '<tr>'+
                          '<td>'+konten.val().guestName+'</td>'+
                          '<td>'+konten.val().guestEmail+'</td>'+
                          '<td>'+konten.val().roomType+'</td>'+
                          '<td>'+konten.val().guestPhone+'</td>'+
                          '<td>'+konten.val().checkIn+'</td>'+
                          '<td>'+konten.val().checkOut+'</td>'+
                          '<td>'+konten.val().tripType+'</td>'+
                      '</tr>';
  });
  isi_tabel.innerHTML = tampilan_tabel;
}
function dataGagal(err) {
  console.log(err);
}
//---------------------------------------------------------------------//
