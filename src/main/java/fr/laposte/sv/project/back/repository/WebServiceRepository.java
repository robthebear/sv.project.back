package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface WebServiceRepository extends JpaRepository<WebService, Integer> {


    /**
     * Recherche webservice par son nom
     * @param webService
     * @return un webservice
     */
    Optional<WebService> findByWebService(String webService);

    /**
     * recherche une liste de webservice par son application
     * @param application
     * @return une liste de webservice
     */
    Set<WebService> findWebServiceByApplication(Application application);
}
