package backend.controller;

import java.util.List;
import backend.modelo.Mozo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.service.MozoService;
import java.util.Map;

@RestController
public class MozoController {

    @Autowired
    private MozoService mozoService;

    @GetMapping("/mozos")
    public List<Map<String, String>> findMozos() {
        return mozoService.findMozos(); 
    }
}
