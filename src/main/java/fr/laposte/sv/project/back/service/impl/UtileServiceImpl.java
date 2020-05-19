package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.service.UtileService;
import org.springframework.stereotype.Service;

@Service
public class UtileServiceImpl implements UtileService {

    public static Long duree(String debut, String fin) {


        String debut1[] = debut.split(" ");
        String fin1[] = fin.split(" ");
        String d[] = debut1[1].split(":");
        String f[] = fin1[1].split(":");
        long debut2[] = {Long.parseLong(d[0]), Long.parseLong(d[1]), Long.parseLong(d[2]), Long.parseLong(d[3])};
        long fin2 [] = {Long.parseLong(f[0]), Long.parseLong(f[1]), Long.parseLong(f[2]), Long.parseLong(f[3])};
        long debutEvenement = (debut2[0]*3600000) + (debut2[1]*60000) + (debut2[2]*1000) + (debut2[3]*1);
        long finEvenement = (fin2[0]*3600000) + (fin2[1]*60000)+ (fin2[2]*1000) + (fin2[3]*1);
        long temps = finEvenement - debutEvenement;
        return temps;

    }


}
