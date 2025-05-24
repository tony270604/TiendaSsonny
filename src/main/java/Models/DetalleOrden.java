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
    @JoinColumn(name = "cod_com", insertable = false, updatable = false)
    private Comida comida;

    @Id
    @ManyToOne
    @JoinColumn(name = "cod_or", insertable = false, updatable = false)
    private Orden orden;

    @Column(name = "can_dor")
    private int cantidad;


}
