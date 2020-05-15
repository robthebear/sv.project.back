package fr.laposte.sv.project.back.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "correspondant_application",
            joinColumns = @JoinColumn(name = "correspondant"),
            inverseJoinColumns = @JoinColumn(name = "application"))

//    @JsonManagedReference
            Set<Application> applications;


}
