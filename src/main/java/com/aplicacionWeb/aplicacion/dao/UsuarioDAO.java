package com.aplicacionWeb.aplicacion.dao;

import com.aplicacionWeb.aplicacion.models.Usuarios;

import java.util.List;

public interface UsuarioDAO {

    List<Usuarios> getUsuarios();

    void eliminarUsuario(Long id);

    void registrarUsuario(Usuarios usuario);

    Usuarios obtenerUsuarioCredenciales(Usuarios usuario);
}
