/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author MGamero
 */
public class ProductoDTO {
     private String codigo;
    private String nombre;
    private float precioUnitario;
    private int descuento;
    private float precioConDescuento;

    public ProductoDTO(String codigo, String nombre, float precioUnitario, int descuento, float precioConDescuento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.precioConDescuento = precioConDescuento;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public float getPrecioUnitario() { return precioUnitario; }
    public int getDescuento() { return descuento; }
    public float getPrecioConDescuento() { return precioConDescuento; }

    @Override
    public String toString() {
        return nombre;
    }
}
