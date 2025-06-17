
package modelo;

import java.math.BigDecimal;


public class DetalleBoleta {
    private int num_bol;        // número de boleta para vincular
    private String cod_pro;     // código del producto
    private String nombreProducto; // nombre del producto
    private BigDecimal precioUnitario; // precio unitario del producto
    private int can;            // cantidad
    private double descuento;    // descuento aplicado
    private BigDecimal subtotal;

    public DetalleBoleta(int num_bol, String cod_pro, String nombreProducto, BigDecimal precioUnitario, int can, double descuento, BigDecimal subtotal) {
        this.num_bol = num_bol;
        this.cod_pro = cod_pro;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.can = can;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public int getNum_bol() {
        return num_bol;
    }

    public void setNum_bol(int num_bol) {
        this.num_bol = num_bol;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    
    
    
}
