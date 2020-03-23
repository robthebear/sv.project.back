package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Optional<Application> findByLibelle(String libelle);

}
