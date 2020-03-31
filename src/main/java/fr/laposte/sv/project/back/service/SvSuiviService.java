package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.SvSuivi;
import org.springframework.stereotype.Service;

@Service
public interface SvSuiviService {

    SvSuivi saveSvSuivi(SvSuivi svSuivi);
}
