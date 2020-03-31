package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "application")
public class Application implements Serializable {
    @Id
    String id;
    String libelle;
    String type;
    @OneToMany(mappedBy = "application", orphanRemoval = true)
    @JsonManagedReference
    List<WebService> webService;


    public Application(String application) {
        this.id = application;
    }

    public Application(String id, String libelle, String type) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
    }
}
