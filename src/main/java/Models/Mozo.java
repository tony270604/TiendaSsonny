package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mozo")
public class Mozo {

    @Id
    @Column(name = "cod_moz", length = 4)
    private String codMoz;

    @Column(name = "nom_moz", length = 50, nullable = false)
    private String nomMoz;

    @Column(name = "correo_moz", length = 50, nullable = false)
    private String correoMoz;

    @Column(name = "contra_moz", length = 50, nullable = false)
    private String contraMoz;

    @Lob
    @Column(name = "img1_moz")
    private byte[] img1Moz;

    @ManyToOne
    @JoinColumn(name = "cod_adm", nullable = false)
    private Administrador administrador;


    @OneToMany(mappedBy = "mozo")
    private List<Boleta> boletas;
}