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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CorrespondantServiceImpl implements CorrespondantService {


    @Autowired
    CorrespondantRepository correspondantRepository;

    @Autowired
    ApplicationRepository applicationRepository;

//    @Override
//    public Correspondant deleteApplication(String idCorrespondant, String idApplication) {
//        String  relation = correspondantRepository.getOne(idCorrespondant).getApplications(idApplication);
//    }

@Override
    public ResponseEntity<Correspondant> updateCorrespondant(Correspondant correspondant) {
    Optional<Correspondant> correspondantEnBase = correspondantRepository.findById(correspondant.getId());
    Set<Application> applicationsEnBase = correspondantEnBase.get().getApplications();

    if (correspondantEnBase.isPresent()) {
        if (correspondant.getNom().isEmpty()) {
            correspondant.setNom(correspondantEnBase.get().getNom());
        }
        if (correspondant.getPrenom().isEmpty()) {
            correspondant.setPrenom(correspondantEnBase.get().getPrenom());
        }
        if (correspondant.getFonction().isEmpty()) {
            correspondant.setFonction(correspondantEnBase.get().getFonction());
        }
        if (correspondant.getEmail().isEmpty()) {
            correspondant.setEmail(correspondantEnBase.get().getEmail());
        }
        if (correspondant.getTelephone().isEmpty()) {
            correspondant.setTelephone(correspondantEnBase.get().getTelephone());
        }
        if (correspondant.getApplications().isEmpty()) {
                correspondant.setApplications(applicationsEnBase);

        }
        return new ResponseEntity<>(correspondantRepository.saveAndFlush(correspondant), HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(correspondant, HttpStatus.NOT_FOUND);
    }
}
}
