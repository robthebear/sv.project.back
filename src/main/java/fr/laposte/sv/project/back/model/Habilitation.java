package fr.laposte.sv.project.back.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Habilitation implements Serializable {
    @Id
    String idRh;
    @Enumerated(EnumType.STRING)
            Role role;
    String motDePasse;
    
}
