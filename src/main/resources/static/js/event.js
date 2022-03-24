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

        name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
        description: /^[a-zA-ZÀ-ÿ0-9\s]{1,300}$/, // Letras y espacios y números, pueden llevar acentos.
        price: /^\d*(\.\d{1})?\d{0,1}$/, //Valida número decimal con dos dígitos de precisión.

    }

    document.getElementById("nameFail").innerHTML="";
    document.getElementById("descriptionFail").innerHTML="";
    document.getElementById("priceFail").innerHTML="";

    switch(e.target.name){

        case "name":
            if(!expresions.name.test(e.target.value)){

            document.getElementById("nameFail").innerHTML="Campo inorrecto, posee caracteres no permitidos (numeros, caracteres especiales...)";

            }
        break;

        case "description":
            if(!expresions.description.test(e.target.value)){

            document.getElementById("descriptionFail").innerHTML="Campo incorrecto. La descripción es demasiado extensa, debe tener como máximo 300 caracteres..";

            }
        break;

        case "price":
            if(!expresions.price.test(e.target.value)){

            document.getElementById("priceFail").innerHTML="Campo incorrecto. El precio sólo puede ser un número y puede contener hasta dos decimales.";

            }
        break;
    }
 }
