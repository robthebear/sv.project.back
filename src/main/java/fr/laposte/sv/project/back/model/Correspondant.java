package fr.laposte.sv.project.back.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Correspondant implements Serializable {
    @Id
     String id;
     String nom;
     String prenom;
     String fonction;
     String email;
     int telephone;


}
