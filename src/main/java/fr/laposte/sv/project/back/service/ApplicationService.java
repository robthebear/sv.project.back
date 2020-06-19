package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.Application;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ApplicationService {
    Set<Application> applicationFiltre(String id);

    Set<Application> applicationAMettreAJour();

    ResponseEntity<Application> mettreAjourApplication(String id, Application application);

    void nouvelleApplication(String id);
}
