package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.service.UtileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class UtileServiceImpl implements UtileService {



@Override
    public Long duree(String debut, String fin) {


        String debut1[] = debut.split(" ");
        String fin1[] = fin.split(" ");
        String d[] = debut1[1].split(":");
        String f[] = fin1[1].split(":");
        long debut2[] = {Long.parseLong(d[0]), Long.parseLong(d[1]), Long.parseLong(d[2]), Long.parseLong(d[3])};
        long fin2 [] = {Long.parseLong(f[0]), Long.parseLong(f[1]), Long.parseLong(f[2]), Long.parseLong(f[3])};
        long debutEvenement = (debut2[0]*3600000) + (debut2[1]*60000) + (debut2[2]*1000) + (debut2[3]);
        long finEvenement = (fin2[0]*3600000) + (fin2[1]*60000)+ (fin2[2]*1000) + (fin2[3]);
        long temps = finEvenement - debutEvenement;
        return temps;

    }

    @Override
    public LocalDate dateEvenement (String dateDebut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dateR[] = dateDebut.split(" ");
        LocalDate date = LocalDate.parse(dateR[0], formatter);
        return date;

    }
    @Override
    public LocalTime heureDebut (String dateDebut) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
        String dateR[] = dateDebut.split(" ");
        LocalTime heureDebut = LocalTime.parse(dateR[1], formatTime);
    return heureDebut;
    }

}
