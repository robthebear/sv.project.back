package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Optional<Application> findByLibelle(String libelle);

}
