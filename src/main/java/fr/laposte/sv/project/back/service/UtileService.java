package fr.laposte.sv.project.back.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public interface UtileService {
     Long duree(String dateDebut, String dateFin);

     LocalDate dateEvenement(String dateDebut);

     LocalTime heureDebut(String dateDebut);
}
