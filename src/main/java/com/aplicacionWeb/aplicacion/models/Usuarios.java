package com.aplicacionWeb.aplicacion.models;

//anotacion para indicar la tabla que va a utilizar

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//anotacion para indicar que hace referencia a la db
@Entity
//anotacion para indicar la tabla que se va a usar
@Table(name="usuarios")
//anotacion para el ToString de la clase
@ToString
public class Usuarios {

    //anotacion Getter y Setter de lombock reemplazando y haciendo la funbcion del get y set de la propiedad
    //anotacion Column para indicar la columna de la tabla
    @Getter @Setter @Column(name="id")
    //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //anotacion para la clave primaria o identificador
    @Id
    private long id;

    @Getter @Setter @Column(name="nombre")
    private String nombre;

    @Getter @Setter @Column(name="apellido")
    private String apellido;

    @Getter @Setter @Column(name="email")
    private String email;

    @Getter @Setter @Column(name="telefono")
    private int telefono;

    @Getter @Setter @Column(name="password")
    private String password;

    /*
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     */
}
