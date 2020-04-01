package fr.laposte.sv.project.back.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    Date dateDebut;
    Date dateFin;
    String statutRetour;
    String statutHttp;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "web_service", referencedColumnName = "id")
    WebService webService;


    public SvSuivi(String dateDebut, String dateFin, String statutRetour, String statutHttp, String webservice) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");

        this.dateDebut = formatter.parse(dateDebut);
        this.dateFin = formatter.parse(dateFin);
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.webService = new WebService(webservice);
    }
}
