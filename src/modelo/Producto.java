package modelo;

public class Producto {

    public String cod_pro;
    public String nom_pro;
    public int stock_pro;
    public float prec_pro;

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }

    public int getStock_pro() {
        return stock_pro;
    }

    public void setStock_pro(int stock_pro) {
        this.stock_pro = stock_pro;
    }

    public float getPrec_pro() {
        return prec_pro;
    }

    public void setPrec_pro(float prec_pro) {
        this.prec_pro = prec_pro;
    }

    @Override
    public String toString() {
        return nom_pro + " - S/ " + prec_pro;
    }
}
