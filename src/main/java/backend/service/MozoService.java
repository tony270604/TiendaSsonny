package backend.service;

import backend.dao.MozoRepository;
import java.util.List;
import backend.modelo.Mozo;
import ch.qos.logback.core.model.Model;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MozoService {

    @Autowired
    private MozoRepository mozoRepository;

    public List<Mozo> findMozos(Model model) {
        return mozoRepository.findAll().stream().map(mozo -> {
            if (mozo.getImg1Moz() != null) {
                String img1Base64 = Base64.getEncoder().encodeToString(mozo.getImg1Moz());
                mozo.setImg1Moz_base64(img1Base64);
            }
            return mozo;
        }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> findMozosCodNom(Model model) {
        return mozoRepository.findAll().stream().map(mozo -> {
            Map<String, Object> datosMozo = new LinkedHashMap<>();
                datosMozo.put("cod_moz", mozo.getCodMoz());
                datosMozo.put("nom_moz", mozo.getNomMoz());
            return datosMozo;
        }) .collect(Collectors.toList());
    }
}
