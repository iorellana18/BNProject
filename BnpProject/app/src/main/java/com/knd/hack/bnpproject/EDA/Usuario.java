package com.knd.hack.bnpproject.EDA;

import java.io.Serializable;
import java.io.StringBufferInputStream;

/**
 * Created by Ian on 15-10-2017.
 */
@SuppressWarnings("serial")
public class Usuario implements Serializable{
    private String nombre;
    private String apellido;
    private String patente;
    private String rut;
    private String telefono;
    private String correo;
    private String pass;

    public Usuario(String nombre, String apellido, String patente, String rut, String telefono){
        setNombre(nombre);
        setApellido(apellido);
        setPatente(patente);
        setRut(rut);
        setTelefono(telefono);
    }

    public Usuario(){}

    public void setNombre(String nombre){this.nombre=nombre;}
    public String getNombre(){return nombre;}
    public void setApellido(String apellido){this.apellido=apellido;}
    public String getApellido(){return apellido;}
    public void setPatente(String patente){this.patente=patente;}
    public String getPatente(){return patente;}
    public void setRut(String rut){this.rut=rut;}
    public String getRut(){return rut;}
    public void setTelefono(String telefono){this.telefono=telefono;}
    public String getTelefono(){return telefono;}
    public void setCorreo(String correo){this.correo=correo;}
    public String getCorreo(){return correo;}
    public void setPass(String pass){this.pass=pass;}
    public String getPass(){return pass;}
}
