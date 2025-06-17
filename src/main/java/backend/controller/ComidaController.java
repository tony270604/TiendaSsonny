package backend.controller;

import backend.modelo.Comida;
import backend.service.ComidaService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comida")
@CrossOrigin(origins = "http://http://localhost:4200")
public class ComidaController {
    
    @Autowired
    private ComidaService comidaService;
    //hola
    @PostMapping("/agregar")
    public ResponseEntity<Map<String, String>> guardarComida(@RequestBody Comida comida) {
        Map<String, String> response = new HashMap<>();
        try {
            comidaService.guardarComida(comida);
            response.put("mensaje", "Comida ingresada correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Map<String, String>> actualizarComida(@PathVariable String id, @RequestBody Comida comidaDetalles) {
        Map<String, String> response = new HashMap<>();
        try {
            comidaService.actualizarComida(id, comidaDetalles);
            response.put("mensaje", "Comida actualizada correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    
}
