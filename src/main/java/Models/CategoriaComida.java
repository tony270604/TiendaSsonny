package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class CategoriaComida {

    @Id
    @Column(name = "cod_cat", length = 3)
    private String codCat;

    @Column(name = "nom_cat", length = 50, nullable = false)
    private String nomCat;

    @ManyToMany(mappedBy = "categorias")
    private List<Comida> comidas;
}