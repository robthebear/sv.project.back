package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateCreation;

    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "application", referencedColumnName = "id")
    Application application;

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    Set<SvErreur> svErreur = new HashSet<>();

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    Set<SvSuivi> svSuivi = new HashSet<>();

    @OneToMany(mappedBy = "webService", orphanRemoval = true)
    @JsonManagedReference
    Set<SvStatistique> svStatistique = new HashSet<>();


    public WebService(String webservice) {
        this.webService = webservice;
    }

    public WebService(String dateCreation, String libelleWebService, String webService, String application) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateR[] = dateCreation.split(" ");
        this.dateCreation = LocalDate.parse(dateR[0], formatter);
        this.libelleWebService = libelleWebService;
        this.webService = webService;
        this.application = new Application(application);

    }

    public WebService(String webService, String libelleWebService, LocalDate dateCreation) {

        this.webService = webService;
        this.libelleWebService = libelleWebService;
        this.dateCreation = dateCreation;

    }
}
