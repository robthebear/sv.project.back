package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.dto.ResultatDto;
import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public interface ResultatService {
    Set<ResultatDto> resultat(Application application, Optional<WebService> webservices, LocalDate dateDebut, LocalDate dateFin);

}
