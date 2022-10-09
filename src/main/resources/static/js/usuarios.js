// Call the dataTables jQuery plugin
$(document).ready(function() {
cargarUsuarios();
  $('#usuarios').DataTable();
  //actualizamos el email del usuario
});

function actualizarEmailUsuario(){
document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
 }

async function cargarUsuarios(){
//url de los usuarios
  const request = await fetch('api/usuario', {
  //metodo get para consultar a los usuarios
    method: 'GET',
    //cabeceras
    headers: getHeaders()
    //para post
    //body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  //convertir el resultado a json
  const usuarios = await request.json();

  let listadoHTML = '';
  for(let usuario of usuarios){
  //llamamos a la funcion eliminarUsuario al hacer clic en el boton
  let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

  let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td><td>'+botonEliminar+'</td></tr>';
  listadoHTML += usuarioHTML;
  }

  //muestra el resultado en la consola
  //console.log(content);

  //let usuarioHTML = '<tr><td>1</td><td>Yader Barahona</td><td>yaderbarahona180@gmail.com</td><td>12345678</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>';
  document.querySelector('#usuarios tbody').outerHTML = listadoHTML;

}

//funcion eliminar usuario
async function eliminarUsuario(id){

if(confirm("¿Eliminar usuario?")){
 const request = await fetch('api/usuario/' + id, {
  //metodo delete para eliminar a los usuarios
    method: 'DELETE',
    //cabeceras
    headers:
    getHeaders()
    //para post
    //body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  } else {
  //detenemos la funcion
  return;
  }

  //recargamos
  location.reload();
}

//funcion para retornar los headers de la request
function getHeaders(){
return {
    //utilizando json
      return 'Accept': 'application/json',
      //contenido en json
      'Content-Type': 'application/json',
      //mandamos la informacion del token del localStorage a la hora de eliminar un usuario
      'Authorization': localStorage.token
}
}
