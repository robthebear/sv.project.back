package fr.laposte.sv.project.back.batch;



import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvErreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

@Component
public class ExtractionDonnee {

@Autowired
SvErreurService svErreurService;

@Autowired
    WebServiceRepository webServiceRepository;
public void extraireErreur () {
    FileReader fr = null;
    FileReader fr2;
    int count = 0;

    try {
        fr = new FileReader("src/main/resources/erreurs_gtm-webservices.log");
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
    BufferedReader br = new BufferedReader(fr);
    String ligne;
    try {
        while ((ligne=br.readLine()) != null) {
            String  tabErreur [] = ligne.split(";", -1);
            SvErreur batchSvErreur = new SvErreur(
                    tabErreur[6],
                    tabErreur[7],
                    tabErreur[9],
                    tabErreur[10],
                    tabErreur[13],
                    tabErreur[0]);

                    svErreurService.saveSvErreur(batchSvErreur);

        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}

}
