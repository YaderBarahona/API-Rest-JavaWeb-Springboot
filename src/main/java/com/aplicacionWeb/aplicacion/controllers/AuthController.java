package com.aplicacionWeb.aplicacion.controllers;

import com.aplicacionWeb.aplicacion.dao.UsuarioDAO;
import com.aplicacionWeb.aplicacion.models.Usuarios;
import com.aplicacionWeb.aplicacion.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//controlador para la autenticacion del usuario
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    //anotacion RequestBody para convertir el json que recibe a un usuario automaticamente
    public String login (@RequestBody Usuarios usuario){
        Usuarios usuarioLogeado = usuarioDAO.obtenerUsuarioCredenciales(usuario);
        if(usuarioLogeado!=null){
        //jwtUtil.create() genera el token recibiendo id
        String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
        return tokenJWT;
        } else {
            return "Error";
        }

    }
}
