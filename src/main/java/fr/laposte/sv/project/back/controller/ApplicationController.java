package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;


    @GetMapping
    public Collection<Application> findAll() {

        return applicationRepository.findAll();
    }
    @GetMapping("/applicationAMettreAJour")
    public Set<Application> applicationAMettreAJour(){
        return applicationService.applicationAMettreAJour();
    }

    @PutMapping("/mettreAjourApplication/{id}")
    public ResponseEntity<Application> mettreAjourApplication(@PathVariable String id, @RequestBody Application application) {
        return applicationService.mettreAjourApplication(id, application);
    }

    @GetMapping("/id/{id}")
    public Optional<Application> findById(@PathVariable String id) {
        return applicationRepository.findById(id);
    }

    @GetMapping("/filtre/{id}")
    public Set<Application> applicationFiltre(@PathVariable String id) {
        return applicationService.applicationFiltre(id);
    }

    @GetMapping("/libelle/{libelle}")
    public Optional<Application> findByLibelle(@PathVariable String libelle) {
        return applicationRepository.findByLibelle(libelle);
    }

    @PostMapping
    public Application ajoutApplication(@RequestBody Application application) {
        return applicationRepository.saveAndFlush(application);
    }

    @PutMapping
    public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
        Optional<Application> applicationEnBase = applicationRepository.findById(application.getId());
        if (applicationEnBase.isPresent()) {
            if (application.getLibelle() == null) {
                application.setLibelle(applicationEnBase.get().getLibelle());
            }
            if (application.getType() == null) {
                application.setType(applicationEnBase.get().getType());
            }
            if (application.getWebService() == null) {
                application.setWebService(applicationEnBase.get().getWebService());
            }
            return new ResponseEntity<>(applicationRepository.saveAndFlush(application), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(application, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public void delApplication(@PathVariable String id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication
                .isPresent()) {
            applicationRepository.deleteById(id);

        }
    }

}
