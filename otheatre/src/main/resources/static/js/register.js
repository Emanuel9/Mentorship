function check(input) {
 if (input.value != document.getElementById('password').value) {
 input.setCustomValidity('Confirmation password is not the same as the password.');
 } else {
 // input is valid -- reset the error message
 input.setCustomValidity('');
 }
 }