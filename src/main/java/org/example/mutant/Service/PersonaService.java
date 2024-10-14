package org.example.mutant.Service;

import org.example.mutant.Repositories.PersonaRepository;
import org.example.mutant.Entities.Persona;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int countDnaEquals = 0;

        // Verificaciones horizontales
        for (int fil = 0; fil < n; fil++) {
            for (int i = 0; i <= n - 4; i++) { // n - 4 para evitar overflow
                if (dna[fil].charAt(i) == dna[fil].charAt(i + 1) &&
                        dna[fil].charAt(i + 1) == dna[fil].charAt(i + 2) &&
                        dna[fil].charAt(i + 2) == dna[fil].charAt(i + 3)) {
                    countDnaEquals++;
                }
            }
        }

        // Verificaciones verticales
        for (int col = 0; col < n; col++) {
            for (int i = 0; i <= n - 4; i++) {
                if (dna[i].charAt(col) == dna[i + 1].charAt(col) &&
                        dna[i + 1].charAt(col) == dna[i + 2].charAt(col) &&
                        dna[i + 2].charAt(col) == dna[i + 3].charAt(col)) {
                    countDnaEquals++;
                }
            }
        }

        // Verificaciones diagonales (principales de izquierda a derecha)
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                        dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                    countDnaEquals++;
                }
            }
        }

        // Verificaciones diagonales (desde abajo hacia arriba, derecha a izquierda)
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 3; j < n; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j - 1) &&
                        dna[i + 1].charAt(j - 1) == dna[i + 2].charAt(j - 2) &&
                        dna[i + 2].charAt(j - 2) == dna[i + 3].charAt(j - 3)) {
                    countDnaEquals++;
                }
            }
        }

        // Si se encuentran más de una secuencia mutante, devolver true
        return countDnaEquals > 1;
    }


    public  void verificarYGuardarMutante(Persona persona) {
        List<String> dna = persona.getAdn(); // Obtener las secuencias de ADN

        personaRepository.save(persona);

        String [] dnaArray = dna.toArray(new String[0]);

        if (isMutant(dnaArray)) {
            System.out.println("El sujeto es mutante");
            // Aquí podrías agregar la lógica para guardar en la base de datos si es mutante
        } else {
            System.out.println("El sujeto no es mutante");
        }
    }

    public Persona AddPersona(String[] dna){

        Persona persona = new Persona();
        persona.setAdn(Arrays.asList(dna));
        return personaRepository.save(persona);

    }
    // Metodo para contar mutantes
    public long countMutants() {
        return personaRepository.countByIsMutantTrue();
    }

    // Metodo para contar humanos (no mutantes)
    public long countHumans() {
        return personaRepository.countByIsMutantFalse();
    }

    // Metodo para obtener las estadísticas
    public Stats getStats() {
        long countMutants = countMutants();
        long countHumans = countHumans();

        // Calculamos el ratio
        double ratio = countHumans > 0 ? (double) countMutants / countHumans : 0.0;

        // Retornamos las estadísticas
        return new Stats(countMutants, countHumans, ratio);
    }
    public Persona save(Persona persona) {
        personaRepository.save(persona);  // Guardar la persona en la base de datos
        return persona;
    }

}
