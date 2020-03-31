package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.SvErreur;
import org.springframework.stereotype.Service;

@Service
public interface SvErreurService {

    SvErreur saveSvErreur(SvErreur svErreur);
}
