package com.example.interventions;


import java.io.Serializable;

public class Intervention  implements Serializable {

    public Intervention(String numero, String nom, String type,String date) {
        this.nom = nom;
        this.numero = numero;
        this.type = type;
        this.date=date;
    }

    private String nom;

    private String numero;

    private String type;
    private String date;

    /*
     * Getters and Setters
     *
     * */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

