package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import fr.laposte.sv.project.back.service.CorrespondantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CorrespondantServiceImpl implements CorrespondantService {


    @Autowired
    CorrespondantRepository correspondantRepository;

    @Autowired
    ApplicationRepository applicationRepository;



    @Override
    public ResponseEntity<Correspondant> updateCorrespondant(String id, Correspondant correspondant) {
        Optional<Correspondant> correspondantEnBase = correspondantRepository.findById(id);
        Set<Application> applicationsAModifier = correspondant.getApplications();
        Optional<Application> ajoutappli;
        Set<Application> appliAModifier = new HashSet<>();

        if (correspondantEnBase.isPresent()) {
            for (Application appli : applicationsAModifier) {
                ajoutappli = applicationRepository.findById(appli.getId());
                appliAModifier.add(ajoutappli.get());
            }
            correspondantEnBase.get().setApplications(appliAModifier);
            System.out.println(correspondantEnBase.get());


            return new ResponseEntity<>(correspondantRepository.save(correspondantEnBase.get()), HttpStatus.CREATED);


        } else {
            //TODO build notFound reponse sans body
            return new ResponseEntity<>(correspondantEnBase.get(), HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void delCorrespondant(String id){
        Optional<Correspondant> optionalCorrespondant = correspondantRepository.findById(id.toUpperCase());
        if (optionalCorrespondant.isPresent()) {
            correspondantRepository.deleteById(id.toUpperCase());
            System.out.print("Correspondant supprimé");

        }
    }
}
