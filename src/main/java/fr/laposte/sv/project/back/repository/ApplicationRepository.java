package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {

    //Cherche une application par libelle de l'application, utilisation d'un Optionnal qui peut renvoyer un null
    Optional<Application> findByLibelle(String libelle);

}
