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
        description: /^[a-zA-ZÀ-ÿ0-9\s]{1,10}$/, // Letras y espacios y números, pueden llevar acentos.
        price: /^\d*(\.\d{1})?\d{0,1}$/, //Valida número decimal con dos dígitos de precisión.

    }

    document.getElementById("nameFail").innerHTML="";
    document.getElementById("descriptionFail").innerHTML="";
    document.getElementById("priceFail").innerHTML="";

    switch(e.target.name){

        case "name":
            if(!expresions.name.test(e.target.value)){

            document.getElementById("nameFail").innerHTML="El nombre no es valido, posee caracteres no permitidos (numeros, caracteres especiales...)";

            }
        break;

        case "description":
            if(!expresions.description.test(e.target.value)){

            document.getElementById("descriptionFail").innerHTML="La descripción es demasiado extensa, debe tener como máximo de 800 caracteres..";

            }
        break;

        case "price":
            if(!expresions.price.test(e.target.value)){

            document.getElementById("priceFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";

            }
        break;
    }
 }
