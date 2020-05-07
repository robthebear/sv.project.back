package fr.laposte.sv.project.back.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults (level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondant implements Serializable {
    @Id
    String id;
    String nom;
    String prenom;
    String fonction;
    String email;
    String telephone;
    @ManyToMany(mappedBy = "correspondants")
//    @JsonManagedReference
    Set<Application> applications;



}
