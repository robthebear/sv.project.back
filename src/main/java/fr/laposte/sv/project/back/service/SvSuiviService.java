package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.dto.SvSuiviDto;
import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Set;

@Service
public interface SvSuiviService {

    SvSuivi saveSvSuivi(SvSuivi svSuivi);

    Set<SvSuivi> findSvSuiviByWebService(WebService webService);



    Set<SvSuivi> svSuiviParDate(WebService webService, LocalDate dateDebut, LocalDate dateFin);



}
