package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_pago")
public class TipoPago {

    @Id
    @Column(name = "cod_tipopago", length = 1)
    private String codTipopago;

    @Column(name = "nom_tipopago", length = 30)
    private String nomTipopago;
}
