package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "detalle_orden")
public class DetalleOrden {

    @Id
    @ManyToOne
    @JoinColumn(name = "cod_com")
    private Comida comida;

    @Id
    @ManyToOne
    @JoinColumn(name = "cod_or")
    private models.Orden orden;

    @Column(name = "can_dor")
    private int cantidad;


}
