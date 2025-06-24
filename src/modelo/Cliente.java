/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author MGamero
 */
public class Cliente extends Persona{
   /* private String dni_cli;
    private String nom_cli;
    private int num_cli;
    private String correo_cli;
    
    

    public String getDni_cli() {
        return dni_cli;
    }

    public void setDni_cli(String dni_cli) {
        this.dni_cli = dni_cli;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public int getNum_cli() {
        return num_cli;
    }

    public void setNum_cli(int num_cli) {
        this.num_cli = num_cli;
    }

    public String getCorreo_cli() {
        return correo_cli;
    }

    public void setCorreo_cli(String correo_cli) {
        this.correo_cli = correo_cli;
    }
    
    @Override
    public String toString() {
        return nom_cli + " - " + dni_cli;
    }*/
    private String dni_cli;
    private int num_cli;

    public Cliente() {}

    public Cliente(String dni_cli, String nombre, int num_cli, String correo) {
        super(nombre, correo);
        this.dni_cli = dni_cli;
        this.num_cli = num_cli;
    }
    
    public String getNom_cli() {
    return this.nombre;
}
public void setNom_cli(String nom_cli) {
    this.nombre = nom_cli;
}

public String getCorreo_cli() {
    return this.correo;
}
public void setCorreo_cli(String correo_cli) {
    this.correo = correo_cli;
}

    public String getDni_cli() {
        return dni_cli;
    }

    public void setDni_cli(String dni_cli) {
        this.dni_cli = dni_cli;
    }

    public int getNum_cli() {
        return num_cli;
    }

    public void setNum_cli(int num_cli) {
        this.num_cli = num_cli;
    }

    @Override
    public String toString() {
        return nombre + " - " + dni_cli;
    }
}
