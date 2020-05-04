package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public interface WebServiceService {

    WebService saveWebService(WebService webService);

    Optional<WebService> findByWebService(String webService);

    WebService modifierWebService(WebService webService, int id);

    LocalDate findDateCreation(String webService);

    int findIdByWebService(String webService);

    Set<WebService> findWebServiceByApplication(Application codeApplication);
}
