let loginButton = $("#login_button");
//let password = $("#password").val();
//let password_confirmation = $("#password_confirmation").val();
//let message = $("#form_output");

function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

loginButton.click(function(event){

    if(!validateEmail($("#email").val())){
        document.getElementById("form_output").innerHTML="Incorrect email format";
                event.preventDefault();
    }else if($("#password").val() !== $("#password_confirmation").val()){
        document.getElementById("form_output").innerHTML="Passwords don't match!";
        event.preventDefault();
    }else{
        document.getElementById("form_output").innerHTML="Utilizatorul cu adresa de email " + $("#email").val() + " a fost creat cu succes!"
    }
    
});



