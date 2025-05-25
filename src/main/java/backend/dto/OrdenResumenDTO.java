package backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrdenResumenDTO {
    private String codOr;
    private int mesa;
    private String hora;
    private float total;
    private List<DetalleDTO> detalles;

    @Data
    @AllArgsConstructor
    public static class DetalleDTO {
        private String codCom;
        private String nombre;
        private float precio;
        private int cantidad;
        private float subtotal;
    }
}
