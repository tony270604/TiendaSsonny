package Models;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(length = 3)
    private String cod_cat;

    @Column(length = 50)
    private String nom_cat;
    @OneToMany(mappedBy = "categoria")
    private List<Comida> comidas;

}