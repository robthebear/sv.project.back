package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public interface SvErreurService {

    SvErreur saveSvErreur(SvErreur svErreur);

    Set<SvErreur> svErreurParDate(WebService webservice, LocalDate dateD, LocalDate dateF);
}
