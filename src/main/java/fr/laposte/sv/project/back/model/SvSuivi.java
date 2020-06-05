package fr.laposte.sv.project.back.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import fr.laposte.sv.project.back.service.UtileService;
import fr.laposte.sv.project.back.service.impl.UtileServiceImpl;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
 * Model de la table SvSuivi
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SvSuivi implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate date;
    LocalTime heureDebut;
    long duree;
    String statutRetour;
    String statutHttp;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "web_service", referencedColumnName = "id")
    WebService webService;

    public SvSuivi(String dateDebut, String dateFin, String statutRetour, String statutHttp, String webservice) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String dateR[] = dateDebut.split(" ");


        this.date = LocalDate.parse(dateR[0], formatter);
        this.heureDebut = LocalTime.parse(dateR[1], formatTime);
        this.duree = UtileServiceImpl.duree(dateDebut, dateFin);
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.webService = new WebService(webservice);
    }



}
