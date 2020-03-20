package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    String codeApplication;
    String libelle;
    String type;
    @OneToMany (targetEntity = WebService.class, mappedBy = "application")
@JsonIgnoreProperties
//    @JoinTable(name = "appartenir",
//                joinColumns = @JoinColumn(name = "code_application"),
//                 inverseJoinColumns = @JoinColumn(name = "id_web_service"))
    List<WebService> webService;


}
