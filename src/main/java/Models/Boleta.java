package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "boleta")
public class Boleta {

    @Id
    @Column(name = "cod_bol", length = 11)
    private String codBol;

    @Temporal(TemporalType.DATE)
    @Column(name = "fec_bol")
    private Date fecha;

    private Float propina;

    @Column(name = "total_bol")
    private Float total;

    @Column(name = "dni_cli", length = 8)
    private String dniCli;

    @Column(name = "ruc_cli", length = 20)
    private String rucCli;

    @Column(name = "nom_cli", length = 50)
    private String nomCli;

    @Column(name = "num_cli", length = 9)
    private String numCli;

    @Column(name = "correo_cli", length = 50)
    private String correoCli;

    @ManyToOne
    @JoinColumn(name = "cod_moz")
    private Mozo mozo;

    @ManyToOne
    @JoinColumn(name = "cod_or")
    private models.Orden orden;

    @ManyToOne
    @JoinColumn(name = "comprobante")
    private Comprobante comprobante;

    @ManyToOne
    @JoinColumn(name = "tipopago")
    private TipoPago tipoPago;



}
