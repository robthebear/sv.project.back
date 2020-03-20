package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Application;

import fr.laposte.sv.project.back.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/tout")
    public Collection<Application> findAll() {

        return applicationRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Application> findById(@PathVariable String id) {
        return applicationRepository.findById(id);
    }

    @GetMapping("/libelle/{libelle}")
    public Optional<Application> findByLibelle(@PathVariable String libelle) {
        return applicationRepository.findByLibelle(libelle);
    }
    @PostMapping("/ajout")
    public Application ajoutApplication(@RequestBody Application application) {
        return applicationRepository.saveAndFlush(application);
    }
    @PutMapping("/update")
    public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
        Optional<Application> applicationEnBase = applicationRepository.findById(application.getCodeApplication());
        if (applicationEnBase.isPresent()) {
            if (application.getLibelle()== null) {
                application.setLibelle(applicationEnBase.get().getLibelle());
            }
            if (application.getType()== null) {
                application.setType(applicationEnBase.get().getType());
            }
            if (application.getWebService()== null) {
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
//            System.out.println("Action supprimée");
//
//        } else {
//            System.out.println("Pas d'action à supprimer");

        }
    }

}
