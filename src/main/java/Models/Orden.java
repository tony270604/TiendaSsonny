package Models;

import Models.Boleta;
import Models.DetalleOrden;
import Models.Mozo;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @Column(name = "cod_or", length = 10)
    private String codOr;

    @Column(name = "mesa")
    private int mesa;

    @Column(name = "hora")
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "cod_moz", nullable = false)
    private Mozo mozo;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalles;

    @OneToMany(mappedBy = "orden")
    private List<Boleta> boletas;


    }