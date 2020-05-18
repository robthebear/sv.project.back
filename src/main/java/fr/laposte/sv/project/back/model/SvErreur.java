package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    LocalTime heureFin;
    String statutRetour;
    String statutHttp;
    String libelleErreur;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "web_service", referencedColumnName = "id")
    WebService webService;

    public SvErreur(String dateHeureDebut, String heureFin, String statutRetour, String statutHttp, String libelleErreur, String webservice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String dateR[] = dateHeureDebut.split(" ");
        String heure[] = heureFin.split(" ");

        this.date = LocalDate.parse(dateR[0], formatter);
        this.heureDebut = LocalTime.parse(dateR[1], formatTime);
        this.heureFin = LocalTime.parse(heure[1], formatTime);
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.libelleErreur = libelleErreur;
        this.webService = new WebService(webservice);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        return LocalDate.parse(date, formatter);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        return LocalTime.parse(time, formatter);

    }


}
