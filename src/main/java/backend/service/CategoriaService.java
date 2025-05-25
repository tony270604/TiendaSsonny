package backend.service;

import backend.dao.CategoriaRepository;
import backend.dto.CategoriaNombreDTO;
import backend.dto.ComidaNombreDTO;
import backend.modelo.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaNombreDTO> listarNombres() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new CategoriaNombreDTO(c.getNom_cat()))
                .collect(Collectors.toList());
    }

    public List<ComidaNombreDTO> listarComidasPorCategoria(String codCat) {
        Categoria categoria = categoriaRepository.findById(codCat).orElse(null);
        if (categoria == null) return List.of();

        return categoria.getComidas().stream()
                .map(comida -> new ComidaNombreDTO(comida.getNomCom()))
                .collect(Collectors.toList());
    }
}
