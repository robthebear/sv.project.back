package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "application", referencedColumnName = "id")
    Application application;

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    List<SvErreur> svErreur;

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    List<SvSuivi> svSuivi;

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    List<SvStatistique> svStatistique;


    public WebService(String webservice) {
        this.webService = webservice;
    }

    public WebService(String dateCreation, String libelleWebService, String webService, String application) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
        this.dateCreation = formatter.parse(dateCreation);
        this.libelleWebService = libelleWebService;
        this.webService = webService;
        this.application = new Application(application);

    }
}
