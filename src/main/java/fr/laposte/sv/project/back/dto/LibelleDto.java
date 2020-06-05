package fr.laposte.sv.project.back.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * Model pour la liste des libelles
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibelleDto {
    String libelle;
    int nbLibelle;
}
