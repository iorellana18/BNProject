package com.knd.hack.bnpproject.EDA;

/**
 * Created by Ian on 14-10-2017.
 */
@SuppressWarnings("serial")
public class Seguro {
    private String titulo;

    public Seguro(String titulo){
        setTitulo(titulo);
    }

    public void setTitulo(String titulo){this.titulo = titulo;}
    public String getTitulo(){return titulo;}
}
