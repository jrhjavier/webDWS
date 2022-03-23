//Hace que cargue cuando ya están renderizado html
$(document).ready(function(){

    const formulario = document.getElementById("form");
    const inputsFormulario = document.querySelectorAll("#form input");

    //Añadimos un escuchador de eventos a cada input del formulario.
    inputsFormulario.forEach((input) => {

        //Se ejecuta cuando se levanta una tecla.
        input.addEventListener('keyup', validarProducto);

        //Se ejecuta cuando se  quita el foco de los inputs del formulario.
        input.addEventListener('blur', validarProducto);
    });
 });

function validarProducto(e){

    //Guardamos la expresiones regulares para validar los elementos del formulario.
    const expresiones = {

        name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
        description: /^[a-zA-ZÀ-ÿ0-9\s]{1,10}$/, // Letras y espacios y números, pueden llevar acentos.
        price: /^\d*(\.\d{1})?\d{0,1}$/, //Valida número decimal con dos dígitos de precisión.

    }

    switch(e.target.name){

        case "name":
            //Testeamos si se cumple la condición del nombre
            if(expresiones.name.test(e.target.value)){

                document.getElementById("nameFail").innerHTML="";


            }else{

                document.getElementById("nameFail").innerHTML="El nombre no es valido, posee caracteresno permitidos (numeros, caracteres especiales...)";

            }
        break;
        case "description":
            if(expresiones.description.test(e.target.value)){

                document.getElementById("descriptionFail").innerHTML="";


            }else{

                document.getElementById("descriptionFail").innerHTML="La descripción es demasiado extensa, debe tener como máximo de 800 caracteres..";

            }
        break;
        case "price":
            if(expresiones.price.test(e.target.value)){

                document.getElementById("priceFail").innerHTML="";


            }else{

                document.getElementById("priceFail").innerHTML="El precio sólo pueden ser números y pueden contener hasta dos decimales.";

            }
        break;
    }
 }
