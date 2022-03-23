//Load before HTML
$(document).ready(function(){

    const form = document.getElementById("form");
    const inputsForm = document.querySelectorAll("#form input");

    //add to inputs
    inputsForm.forEach((input) => {

        //When keyup:
        input.addEventListener('keyup', addEvent);

    });

 });

function addEvent(e){

    //Save expresions (form)
    const expresions = {

        name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
        surname: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
        email: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/, // Email
        phone: /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{3}$/, //Format: 000-000-000
        password: /^(?=(?:.*\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})\S{8}$/ // 1 capital letter, 2 letters, 2 numbers, 8 caracteres
        address: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,

    }

    document.getElementById("nameFail").innerHTML="";
    document.getElementById("surnameFail").innerHTML="";
    document.getElementById("emailFail").innerHTML="";
    document.getElementById("phoneFail").innerHTML="";
    document.getElementById("passwordFail").innerHTML="";
    document.getElementById("addressFail").innerHTML="";

    switch(e.target.name){

        case "name":
            if(!expresions.name.test(e.target.value)){

            document.getElementById("nameFail").innerHTML="El nombre no es valido, posee caracteres no permitidos (numeros, caracteres especiales...)";

            }
        break;

        case "surname":
            if(!expresions.surname.test(e.target.value)){

            document.getElementById("surnameFail").innerHTML="La descripción es demasiado extensa, debe tener como máximo de 800 caracteres..";

            }
        break;

        case "email":
            if(!expresions.email.test(e.target.value)){

            document.getElementById("emailFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";

            }
        break;
        
        case "phone":
             if(!expresions.phone.test(e.target.value)){
        
             document.getElementById("phoneFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";
        
             }
        break;
        
        case "password":
             if(!expresions.password.test(e.target.value)){
              
             document.getElementById("passwordFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";
              
             }
        break;
          
        case "address":
             if(!expresions.address.test(e.target.value)){
                  
             document.getElementById("addressFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";
                  
             }
        break;
                             
    }
 }
