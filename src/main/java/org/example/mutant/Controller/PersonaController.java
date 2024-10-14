package org.example.mutant.Controller;

import org.example.mutant.Entities.Persona;
import org.example.mutant.Service.PersonaService;
import org.example.mutant.Service.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Metodo para detectar si es mutante
    @PostMapping("/")
    public ResponseEntity<String> detectarMutante(@RequestBody Map<String, String[]> dnaRequest) {
        String[] dna = dnaRequest.get("dna");  // Obtener la secuencia de ADN desde el JSON

    // Crear la persona con el ADN proporcionado
        Persona persona = new Persona();
        persona.setAdn(Arrays.asList(dna));

        // Verificar si es mutante
        boolean esMutante = personaService.isMutant(dna);
        persona.setMutant(esMutante); // Establecer si la persona es mutante o no

        // Guardar la persona en la base de datos con el estado isMutant correcto
        personaService.save(persona);
        if (personaService.isMutant(dna)) {
            return ResponseEntity.ok("El individuo es mutante");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El individuo no es mutante");
        }

    }
    // Nuevo endpoint para obtener las estadísticas
    @GetMapping("/stats")
    public ResponseEntity<Stats> obtenerEstadisticas() {
        Stats stats = personaService.getStats();
        return ResponseEntity.ok(stats);
    }
}
