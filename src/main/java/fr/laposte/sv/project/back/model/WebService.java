package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WebService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String webService;
    String libelleWebService;
    Date dateCreation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "code_application", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "application"))
    Application application;

    @OneToMany(targetEntity = SvErreur.class, mappedBy = "webService", cascade = CascadeType.ALL, orphanRemoval = true)
            @JsonManagedReference
    List<SvErreur> svErreur;


}
