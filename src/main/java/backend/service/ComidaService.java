package backend.service;

import backend.dao.CategoriaRepository;
import backend.dao.ComidaRepository;
import backend.modelo.Categoria;
import backend.modelo.Comida;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Comida guardarComida(Comida comida) {
        if (comidaRepository.existsById(comida.getCodCom())) {
            throw new RuntimeException("Ya existe la comida con el ID " + comida.getCodCom());
        }
        
        return comidaRepository.save(comida);
    }

    public Comida actualizarComida(String id, Comida comidaDetalles) {
        if (!comidaRepository.existsById(id)) {
            throw new RuntimeException("La comida con el ID " + id + " no existe");
        }

        return comidaRepository.findById(id)
                .map(comida -> {
                    comida.setNomCom(comidaDetalles.getNomCom());
                    comida.setPrecNom(comidaDetalles.getPrecNom());
                    comida.setDescCom(comidaDetalles.getDescCom());

                    List<Categoria> nuevasCategorias = categoriaRepository.findAllById(
                            comidaDetalles.getCategorias()
                                    .stream()
                                    .map(Categoria::getCod_cat) // Obtener lista de códigos de categoría
                                    .collect(Collectors.toList())
                    );

                    comida.setCategorias(nuevasCategorias);

                    return comidaRepository.save(comida);
                })
                .orElseThrow(() -> new RuntimeException("Comida no encontrada"));
    }

}
