package Models;


import jakarta.persistence.*;
import java.sql.Time;
import java.util.List;
import lombok.Data;

@Entity
@Data
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
    @JoinColumn(name = "cod_moz", insertable = false, updatable = false)
    private Mozo mozo;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<Boleta> boletas;


    }