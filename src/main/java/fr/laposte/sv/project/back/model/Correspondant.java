package fr.laposte.sv.project.back.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@FieldDefaults (level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondant implements Serializable {
    @Id
    String id_RH;
    String nom;
    String prenom;
    String fonction;
    String email;
    String telephone;


}
