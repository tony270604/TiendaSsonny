package modelo;

public class Vendedor extends Persona{
  /*  public int cod_ven;
    public String nom_ven;
    public String correo_ven;
    public String contra_ven;

    public int getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(int cod_ven) {
        this.cod_ven = cod_ven;
    }

    public String getNom_ven() {
        return nom_ven;
    }

    public void setNom_ven(String nom_ven) {
        this.nom_ven = nom_ven;
    }

    public String getCorreo_ven() {
        return correo_ven;
    }

    public void setCorreo_ven(String correo_ven) {
        this.correo_ven = correo_ven;
    }

    public String getContra_ven() {
        return contra_ven;
    }

    public void setContra_ven(String contra_ven) {
        this.contra_ven = contra_ven;
    }*/
    
    private int cod_ven;
    private String contra_ven;

    public Vendedor() {}

    public Vendedor(int cod_ven, String nombre, String correo, String contra_ven) {
        super(nombre, correo);
        this.cod_ven = cod_ven;
        this.contra_ven = contra_ven;
    }

    public int getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(int cod_ven) {
        this.cod_ven = cod_ven;
    }

    public String getContra_ven() {
        return contra_ven;
    }

    public void setContra_ven(String contra_ven) {
        this.contra_ven = contra_ven;
    }

    // Alias opcionales para mantener compatibilidad
    public String getNom_ven() {
        return this.nombre;
    }

    public void setNom_ven(String nom_ven) {
        this.nombre = nom_ven;
    }

    public String getCorreo_ven() {
        return this.correo;
    }

    public void setCorreo_ven(String correo_ven) {
        this.correo = correo_ven;
    }
}
