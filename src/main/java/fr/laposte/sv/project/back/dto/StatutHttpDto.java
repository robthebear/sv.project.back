package fr.laposte.sv.project.back.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * model statut
 * 
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatutHttpDto {
String http;
int nbHttp;

}
