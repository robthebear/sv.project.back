package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface WebServiceRepository extends JpaRepository<WebService, Integer> {


   Optional <WebService> findByWebService(String webService);


    Set<WebService> findWebServiceByApplication(Application application);
}
