package fr.laposte.sv.project.back.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application implements Serializable {
    @Id
    String codeApplication;
    String libelle;
    String type;
    @OneToMany
    Collection<WebService> webService;


}
