package backend.service;

import backend.dao.MozoDAO;
import java.util.List;
import backend.modelo.Mozo;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MozoService {

    @Autowired
    private MozoDAO mozoDAO;

    public List<Map<String, String>> findMozos() {
    return mozoDAO.findAll().stream()
        .map(mozo -> {
            Map<String, String> orderedMap = new LinkedHashMap<>();
            orderedMap.put("cod_moz", mozo.getCodMoz());
            orderedMap.put("nom_moz", mozo.getNomMoz());
            orderedMap.put("correo_moz", mozo.getCorreoMoz());
            orderedMap.put("contra_moz", mozo.getContraMoz());
            return orderedMap;
        })
        .collect(Collectors.toList());
}
}
