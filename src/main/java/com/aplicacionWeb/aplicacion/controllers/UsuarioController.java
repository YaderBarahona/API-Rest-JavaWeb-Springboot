package com.aplicacionWeb.aplicacion.controllers;

import com.aplicacionWeb.aplicacion.dao.UsuarioDAO;
import com.aplicacionWeb.aplicacion.models.Usuarios;
import com.aplicacionWeb.aplicacion.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//controlador (clase que controla las URLS)
//anotacion para indicar que la clase es un controlador
@RestController
public class UsuarioController {

    //anotacion Autowired para la inyeccion de dependencias para que de forma automatica la clase UsuarioDAOImp se cree un objeto y la guarda en al variable usuarioDAO
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    JWTUtil jwtUtil;

    //anotacion para indicar la URL para que devuelva el contenido
    //para hacerlo dinamico indicamos despues de una barra y entre parentesis el id
    @RequestMapping(value = "api/usuario", method = RequestMethod.GET)
    //anotacion para verificar que traiga la cabecera de autorizacion con la informacion del token
    //guardamos el token en la variable token
    public List<Usuarios> listaUsuarios (@RequestHeader(value="Authorization") String token){
        //verificamos si el token es null
        if(!validarToken(token)){
            return null;
        }

        //retornamos los usuarios
        return usuarioDAO.getUsuarios();

    }

    //
    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    //anotacion RequestBody para convertir el json que recibe a un usuario automaticamente
    public void registrarUsuario (@RequestBody Usuarios usuario){
        //objeto Argon2 para la encriptacion de la contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //convertir contraseña
        //primer valor 1 (cantidad de iteraciones), segundo valor 1024 (uso de memoria)
        //tercer valor 1 (cantidad de hilos en paralelo), cuarto valor (valor a cual aplicarle el hash)
        String hash = argon2.hash(1,  1024, 1, usuario.getPassword());

        //cambiamos la contraseña encriptada
        usuario.setPassword(hash);

       usuarioDAO.registrarUsuario(usuario);
    }


    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario (@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        usuarioDAO.eliminarUsuario(id);
    }

    public boolean validarToken(String token){
        //verificacion del token
        //devolviendo el id del usuario (key)
        //pasamos el token
        String usuarioID = jwtUtil.getKey(token);
        //retornamos si el usuario no es null
        return usuarioID != null;
    }


}
