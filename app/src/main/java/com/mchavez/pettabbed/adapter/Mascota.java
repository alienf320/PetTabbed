package com.mchavez.pettabbed.adapter;

public class Mascota {

    private int foto;
    private String nombre;
    private int rating;
    private int reciente;


    private int id;


    public Mascota() {

    }

    public Mascota(int id, int foto, String nombre, int rating) {
        this.foto = foto;
        this.nombre = nombre;
        this.rating = rating;
        this.id = id;
    }

    public Mascota(int foto, String nombre, int rating) {
        this.foto = foto;
        this.nombre = nombre;
        this.rating = rating;
    }

    public Mascota(int foto, String nombre, int rating, int reciente) {
        this.foto = foto;
        this.nombre = nombre;
        this.rating = rating;
        this.reciente = 0;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getReciente() {
        return reciente;
    }

    public void setReciente(int reciente) {
        this.reciente = reciente;
    }
}
