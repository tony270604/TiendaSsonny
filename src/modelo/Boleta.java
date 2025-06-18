package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Boleta {
    private int num_bol;        
    private Date fec_bol;
    private BigDecimal total_bol;
    private String dni_cli;
    private String cod_ven;
    
    public Boleta() {
        
    }

    public Boleta(Date fec_bol, BigDecimal total_bol, String dni_cli, String cod_ven) {     
        this.fec_bol = fec_bol;
        this.total_bol = total_bol;
        this.dni_cli = dni_cli;
        this.cod_ven = cod_ven;
    }

    public int getNum_bol() {
        return num_bol;
    }

    public void setNum_bol(int num_bol) {
        this.num_bol = num_bol;
    }

    public Date getFec_bol() {
        return fec_bol;
    }

    public void setFec_bol(Date fec_bol) {
        this.fec_bol = fec_bol;
    }

    public BigDecimal getTotal_bol() {
        return total_bol;
    }

    public void setTotal_bol(BigDecimal total_bol) {
        this.total_bol = total_bol;
    }

    public String getDni_cli() {
        return dni_cli;
    }

    public void setDni_cli(String dni_cli) {
        this.dni_cli = dni_cli;
    }

    public String getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(String cod_ven) {
        this.cod_ven = cod_ven;
    }

    
    
    
}
