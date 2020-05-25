package fr.laposte.sv.project.back.dto;

import fr.laposte.sv.project.back.model.WebService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultatDto {

String webService;
    LocalDate dateDebut;
    LocalDate dateFin;
    int nbConnexion;
    long tpsConnexion;
    int nbErreur;
    long tpsErreur;
    Set<LibelleDto> libelles = new HashSet<>();
    Set<StaturRetourDto> retours = new HashSet<>();
    Set<StatutHttpDto> https = new HashSet<>();


}
