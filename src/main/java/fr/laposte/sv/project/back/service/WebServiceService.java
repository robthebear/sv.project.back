package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public interface WebServiceService {

    WebService saveWebService(WebService webService);

    Optional<WebService> findByWebService(String webService);

    WebService updateWebService(WebService webService, LocalDate date);

    LocalDate findDateCreation(String webService);
}
