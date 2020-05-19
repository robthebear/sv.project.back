package fr.laposte.sv.project.back.dto;

import fr.laposte.sv.project.back.model.WebService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor
@Getter
@Setter
public class SvSuiviDto {

    int id;
    LocalDate date;
    LocalTime heureDebut;
    long duree;
    String statutRetour;
    String statutHttp;
    WebService webService;

    public SvSuiviDto(String dateHeureDebut, String heureFin, String statutRetour, String statutHttp, String webservice) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

        String dateR[] = dateHeureDebut.split(" ");
        String heure[] = heureFin.split(" ");
        String d[] = dateR[1].split(":");
        String f[] = heure[1].split(":");
        int debut = Integer.parseInt(d[2]);
        int fin = Integer.parseInt(f[2]);

        this.date = LocalDate.parse(dateR[0], formatter);
        this.heureDebut = LocalTime.parse(dateR[1], formatTime);
        this.duree = fin - debut;
        this.statutRetour = statutRetour;
        this.statutHttp = statutHttp;
        this.webService = new WebService(webservice);
    }

}
