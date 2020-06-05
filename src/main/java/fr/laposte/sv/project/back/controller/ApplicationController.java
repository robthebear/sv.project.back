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
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    /**
     * Appelle le back
     * @return toutes les applications
     */
    @GetMapping
    public Collection<Application> findAll() {

        return applicationRepository.findAll();
    }

    /**
     * Appelle le back en recherchant si une application n'a pas de libelle et de type
     * @return les nouvelles applications qui n'ont pas de nom
     */
    @GetMapping("/applicationAMettreAJour")
    public Set<Application> applicationAMettreAJour(){
        return applicationService.applicationAMettreAJour();
    }

    /**
     * Mise à jour d'une application sans libelle et type avec 2 parametres
     * @param id
     * @param application
     * @return la sauvegarde de cette mise a jour de l'application
     */
    @PutMapping("/mettreAjourApplication/{id}")
    public ResponseEntity<Application> mettreAjourApplication(@PathVariable String id, @RequestBody Application application) {
        return applicationService.mettreAjourApplication(id, application);
    }

    /**
     * Recherche d'une application par l' id
     * @param id
     * @return l'application recherche
     */
    @GetMapping("/id/{id}")
    public Optional<Application> findById(@PathVariable String id) {
        return applicationRepository.findById(id);
    }

    /**
     * Filtre les application en fonction de l'id du correspondant
     * @param id
     * @return les applications auquelles le correspondant est habilité
     */
    @GetMapping("/filtre/{id}")
    public Set<Application> applicationFiltre(@PathVariable String id) {
        return applicationService.applicationFiltre(id);
    }

    /**
     * Recherche une application par son libelle
     * @param libelle
     * @returnl'application qui correspont au libéllé
     */
    @GetMapping("/libelle/{libelle}")
    public Optional<Application> findByLibelle(@PathVariable String libelle) {
        return applicationRepository.findByLibelle(libelle);
    }

    /**
     * Ajout d'une application
     * @param application
     * @return enregistre une nouvelle application en base
     */
    @PostMapping
    public Application ajoutApplication(@RequestBody Application application) {
        //TODO gérer les erreurs
        return applicationRepository.saveAndFlush(application);
    }


//    @PutMapping
//    public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
//        Optional<Application> applicationEnBase = applicationRepository.findById(application.getId());
//        if (applicationEnBase.isPresent()) {
//            if (application.getLibelle() == null) {
//                application.setLibelle(applicationEnBase.get().getLibelle());
//            }
//            if (application.getType() == null) {
//                application.setType(applicationEnBase.get().getType());
//            }
//            if (application.getWebService() == null) {
//                application.setWebService(applicationEnBase.get().getWebService());
//            }
//            return new ResponseEntity<>(applicationRepository.saveAndFlush(application), HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(application, HttpStatus.NOT_FOUND);
//        }
//    }

    /**
     * Permet de supprimer une application grace à son id
     * @param id
     */
    @DeleteMapping("/supprimer/{id}")
    public void delApplication(@PathVariable String id) {
        //TODO Transformer cette méthode en update pour désactiver et non supprimer l'application
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication
                .isPresent()) {
            applicationRepository.deleteById(id);

        }
    }

}
