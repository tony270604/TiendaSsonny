package backend.modelo;

import jakarta.persistence.*;
import java.util.List;

import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(length = 3)
    private String cod_cat;

    @Column(length = 50)
    private String nom_cat;

    @ManyToMany
    @JoinTable(
            name = "categoria_comida",
            joinColumns = @JoinColumn(name = "cod_cat"),
            inverseJoinColumns = @JoinColumn(name = "cod_com")
    )
    private List<Comida> comidas;
}
