
    function admLogin() {
      var username = document.getElementById("email").value;
      var password = document.getElementById("password").value;
        if (username == "adminTravelix@gmail.com" && password == "travelixAdmin") {
            
          window.location.href = "booking_user.html";
        }
        else {
          alert("Invalid email or password");
        }
      }
