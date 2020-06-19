package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fr.laposte.sv.project.back.service.UtileService;
import fr.laposte.sv.project.back.service.impl.UtileServiceImpl;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * Model de la table SvErreur
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SvErreur implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate date;
    LocalTime heureDebut;
    long duree;
    String statutRetour;
    String statutHttp;
    String libelleErreur;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "web_service", referencedColumnName = "id")
    WebService webService;

    public SvErreur(LocalDate date, LocalTime heureDebut, Long duree, String statutRetour, String statutHttp, String libelleErreur , String webservice) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.libelleErreur = libelleErreur;
        this.webService = new WebService(webservice);
    }








}
