//Load before HTML
var nameP = false;
var descriptionP = false;
var priceP = false;

$(document).ready(function(){

    const form = document.getElementById("form");
    const inputsForm = document.querySelectorAll("#form input");


    //add to inputs
    inputsForm.forEach((input) => {

        //When keyup:
        input.addEventListener('keyup', addEvent);

        //When blur:
        input.addEventListener('blur', addEvent);

    });
    /*
    document.getElementById("btn-primary").addEventListener('click',function(){

           if(codigoP && nombreP && precioP && unidadesP){

                formulario.reset();
                var nameP = false;
                var descriptionP = false;
                var priceP = false;
           }

        });
        */

 });

function addEvent(e){

    //Save expresions (form)
    const expresions = {

        userName: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/, // Email
        message: /^[a-zA-ZÀ-ÿ0-9\s]{1,10}$/, // Letras y espacios, pueden llevar acentos.

    }

    document.getElementById("userNameFail").innerHTML="";
    document.getElementById("messageFail").innerHTML="";

    switch(e.target.name){

        case "userName":
            if(!expresions.userName.test(e.target.value)){

            document.getElementById("userNameFail").innerHTML="Campo incorrecto. Formato válido: xxxxxxx@domain.xxx";

            }
        break;

        case "message":
            if(!expresions.message.test(e.target.value)){

            document.getElementById("messageFail").innerHTML="Campo incorrecto. La review es demasiado extensa, debe tener como máximo 10 caracteres..";

            }
        break;
    }
 }