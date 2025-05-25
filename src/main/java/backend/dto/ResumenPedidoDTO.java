package backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumenPedidoDTO {
    private String codCom;
    private String nombre;
    private float precio;
    private int cantidad;
    private float subtotal;
}
