package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Set;

public interface SvSuiviRepository extends JpaRepository<SvSuivi, Integer> {

    /**
     * recherche une liste de connexions par le l'id du webservice
     * @param webService
     * @return une liste de connexions
     */

    Set<SvSuivi> findSvSuiviByWebService(WebService webService);


}
