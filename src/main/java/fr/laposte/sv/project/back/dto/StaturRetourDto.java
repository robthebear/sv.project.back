package fr.laposte.sv.project.back.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Modele retour resultats
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaturRetourDto {
    String retour;
    int nbRetour;
}
