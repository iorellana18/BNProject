package com.knd.hack.bnpproject.EDA;

import java.io.Serializable;

/**
 * Created by Ian on 14-10-2017.
 */
@SuppressWarnings("serial")
public class Seguro implements Serializable{
    private String titulo;
    private int imageSource;

    public Seguro(String titulo){
        setTitulo(titulo);
    }

    public Seguro(int imageSource, String name) {
        this.titulo = name;
        this.imageSource = imageSource;
    }

    public void setTitulo(String titulo){this.titulo = titulo;}
    public String getTitulo(){return titulo;}

    public int getImageSource() {
        return imageSource;
    }
}
