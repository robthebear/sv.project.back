package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.Correspondant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CorrespondantService {
    ResponseEntity<Correspondant> updateCorrespondant(String id, Correspondant correspondant);
}
