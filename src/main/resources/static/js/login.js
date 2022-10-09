// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function inicioSesion(){
 let datos = {};
 //obtenemos el valor de las cajas de texto
 datos.email = document.getElementById('txtEmail').value;
 datos.password = document.getElementById('txtPassword').value;

//url de los usuarios
  const request = await fetch('api/login', {
  //metodo POST para registrar a los usuarios
    method: 'POST',
    //cabeceras
    headers: {
    //utilizando json
      'Accept': 'application/json',
      //contenido en json
      'Content-Type': 'application/json'
    },
    //para post
    body: JSON.stringify(datos)
  });
  //convertir el resultado a texto
  const respuesta = await request.text();

  if(respuesta != 'Error'){
  //guardamos el token de la sesion en el localStorage
  localStorage.token = respuesta;
  //guardamos el email del usuario en localStorage
  localStorage.email = datos.email;
  //redirigimos a la pagina usuarios
  window.location.href = 'usuarios.html';
  } else {
  alert("Credenciales incorrectas, intente nuevamente..");
  }

}

