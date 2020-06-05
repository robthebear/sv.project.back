package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Correspondant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorrespondantRepository extends JpaRepository<Correspondant, String> {

    // Permet de retourner un correspondant avec un des 3 strings

    Optional<Correspondant> findByNom(String nom);

    Optional<Correspondant> findByFonction(String fonction);

    Optional<Correspondant> findByEmail(String email);
}
