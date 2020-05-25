package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application implements Serializable {
    @Id
    String id;
    String libelle;
    String type;
    @OneToMany(mappedBy = "application", orphanRemoval = true)
    @JsonManagedReference
    Set<WebService> webService = new HashSet<>();
    @ManyToMany(mappedBy = "applications")
    @JsonIgnore
    Set<Correspondant> correspondants;


    public Application(String application) {
        this.id = application;
    }

    public Application(String id, String libelle, String type) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
    }
}
