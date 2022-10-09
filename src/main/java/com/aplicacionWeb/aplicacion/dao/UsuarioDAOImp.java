package com.aplicacionWeb.aplicacion.dao;

import com.aplicacionWeb.aplicacion.models.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

//anotacion Repository hace referencia al acceso del repositorio de la db
@Repository
//anoracion Transactional da la funcionalidad a la clase de poder armar las consultas sql a la db en fragmentos de transaccion
@Transactional
public class UsuarioDAOImp implements UsuarioDAO{

    //anotacion para hacer referencia a un contexto en el lugar de la db que tiene que utilizar
    @PersistenceContext
    //variable para hacer de puente con la db
    private EntityManager entityManager;

    @Override
    public List<Usuarios> getUsuarios() {
        //consulta sobre hibernate
        String query = "FROM Usuarios";
        //creando la consulta y obteniendo un listado de usuarios
        List<Usuarios> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    public void eliminarUsuario(Long id) {
        //entityManager.find(Usuarios.class, id) para buscar el usuario
        Usuarios usuario = entityManager.find(Usuarios.class, id);
        //eliminamos el usuario
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(Usuarios usuario) {
        //entityManager.detach(usuario);
        //entityManager.merge(usuario) para guardarlo en la db
    entityManager.merge(usuario);
    }

    @Override
    public Usuarios obtenerUsuarioCredenciales(Usuarios usuario) {
        //para inyeccion sql (utilizando +usuario.getEmail+)
        //String email = " ' OR 1 = 1 --";

        //:email para evitar inyeccion sql
        String query = "FROM Usuarios WHERE email = :email ";
        //creando la consulta y obteniendo un listado de usuarios
        List<Usuarios> resultado = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

        //control de un posible nullpointerexception al no encontrar el correo
        if (resultado.isEmpty()){
        return null;
        }

        //guardamos la lista en el primer elemento y obtenemos la contraseña
        String passwordHashed = resultado.get(0).getPassword();

        //verificamos la contraseña encriptada
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        //guardamos y comparamos el hash con la contraseña
        //devolvemos el usuario
         if(argon2.verify(passwordHashed, usuario.getPassword())){
            return resultado.get(0);
         }

         //si las credenciales no son correctas devolvemos null
         return null;


    }


}
