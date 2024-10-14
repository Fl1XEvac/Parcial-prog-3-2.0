package org.example.mutant.Repositories;

import org.example.mutant.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    // Contar personas que son mutantes
    long countByIsMutantTrue();

    // Contar personas que no son mutantes (humanos)
    long countByIsMutantFalse();


}
