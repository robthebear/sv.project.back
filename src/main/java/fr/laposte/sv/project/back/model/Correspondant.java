package fr.laposte.sv.project.back.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondant implements Serializable {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String fonction;
    private String email;
    private int telephone;


}
