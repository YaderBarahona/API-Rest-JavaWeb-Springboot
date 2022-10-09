// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registrarUsuarios(){
 let datos = {};
 //obtenemos el valor de las cajas de texto
 datos.nombre = document.getElementById('txtNombre').value;
 datos.apellido = document.getElementById('txtApellido').value;
 datos.telefono = document.getElementById('txtTelefono').value;
 datos.email = document.getElementById('txtEmail').value;
 datos.password = document.getElementById('txtPassword').value;

    let confirmarPassword = document.getElementById('txtConfirmPassword').value;

    if(confirmarPassword != datos.password){
    alert('Las contraseñas son diferentes');
    return;
    }

//url de los usuarios
  const request = await fetch('api/usuario', {
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

  alet("Cuenta creada con exito!");
  window.location.href = 'login.html';

}

