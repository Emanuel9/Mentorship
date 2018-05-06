/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const registerButton = $("#register_button");
const password = $("#password");
const password_confirmation = $("#password_confirmation");

registerButton.click(function(event){
    event.preventDefault();
    if(password !== password_confirmation){
        window.alert("Passwords do not match!");
    }
    
});

