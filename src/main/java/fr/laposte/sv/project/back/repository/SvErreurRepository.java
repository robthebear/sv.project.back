package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SvErreurRepository extends JpaRepository<SvErreur, Integer> {

    /**
     * recherche une liste d'erreurs par le l'id du webservice
     * @param webService
     * @return une liste d'erreurs
     */

    Set<SvErreur> findSvErreurByWebService(WebService webService);
}
