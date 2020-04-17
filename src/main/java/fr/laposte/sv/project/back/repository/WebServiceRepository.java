package fr.laposte.sv.project.back.repository;

import fr.laposte.sv.project.back.model.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface WebServiceRepository extends JpaRepository<WebService, Integer> {


   Optional <WebService> findByWebService(String webService);

}
