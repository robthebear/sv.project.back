package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import fr.laposte.sv.project.back.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CorrespondantRepository correspondantRepository;

    /**
     * Permet d'afficher l'application si elle vient d'etre créée.
     * @return
     */
    @Override
    public Set<Application> applicationAMettreAJour() {
        List<Application> applications = applicationRepository.findAll();
        Set<Application> appVide = new HashSet<>();
        if (!applications.isEmpty()) {
            for (Application app : applications
            ) {
                if (app.getLibelle().equals(" ")) {
                    appVide.add(app);
                }
            }
        }return appVide;
    }

    @Override
    public ResponseEntity<Application> mettreAjourApplication(String id, Application application) {
Optional<Application> applicationEnBase = applicationRepository.findById(id);
        try {
            if (applicationEnBase.isPresent()) {
                application.setId(applicationEnBase.get().getId());
                application.getLibelle();
                application.getType();
                application.setWebService(applicationEnBase.get().getWebService());
                application.setCorrespondants(applicationEnBase.get().getCorrespondants());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } return new ResponseEntity<>(applicationRepository.save(application), HttpStatus.CREATED);
    }

    @Override
    public Set<Application> applicationFiltre(String id) {
        Optional<Correspondant> correspondantEnBase = correspondantRepository.findById(id);
        Set<Application> applicationCorrespondant = correspondantEnBase.get().getApplications();
        Collection<Application> applications = applicationRepository.findAll();

        Set<Application> applicationFiltre = new HashSet<>();
        if (correspondantEnBase.isPresent()) {
            for (Application app : applications) {
                applicationFiltre.add(app);
                for (Application appEnBase : applicationCorrespondant) {

                    if (appEnBase == app) {
                        applicationFiltre.remove(appEnBase);
                    }
                }
            }
        } else if (!correspondantEnBase.isPresent()){
            for (Application app : applications
            ) {
                applicationFiltre.add(app);

            }
        }
        return applicationFiltre;

    }
}
