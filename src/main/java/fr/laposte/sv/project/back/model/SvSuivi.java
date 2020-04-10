package fr.laposte.sv.project.back.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss:SSS")
    LocalTime heureDebut;
    @JsonFormat(pattern = "HH:mm:ss:SSS")
    LocalTime heureFin;
    String statutRetour;
    String statutHttp;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "web_service", referencedColumnName = "id")
    WebService webService;


    public SvSuivi(String dateHeureDebut, String heureFin, String statutRetour, String statutHttp, String webservice) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String dateR[] = dateHeureDebut.split(" ");
        String heure[] = heureFin.split(" ");

        this.date = LocalDate.parse(dateR[0], formatter);
        this.heureDebut = LocalTime.parse(dateR[1], formatTime);
        this.heureFin = LocalTime.parse(heure[1], formatTime);
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.webService = new WebService(webservice);
    }
}
