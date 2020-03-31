package fr.laposte.sv.project.back.batch;


import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvErreurService;
import fr.laposte.sv.project.back.service.WebServiceService;
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

    @Autowired
    WebServiceService webServiceService;
    @Autowired
    ApplicationRepository applicationRepository;

public void extraireApplication() {
    FileReader fr = null;

    try {
        fr = new FileReader("src/main/resources/Applications.csv");
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
    BufferedReader br = new BufferedReader(fr);
    String ligne;
    try {
        while ((ligne = br.readLine()) != null) {
            String tabApplication[] = ligne.split(";");
            try { Application batchApplication = new Application(
                    tabApplication[1],
                    tabApplication[2],
                    tabApplication[3]);
                if (applicationRepository.findById(tabApplication[0]).isPresent()) {
                    System.out.println("Application presente");
                } else {
                    applicationRepository.saveAndFlush(batchApplication);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void extraireErreur() {
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
            while ((ligne = br.readLine()) != null) {
                String tabErreur[] = ligne.split(";", -1);
                try {
                    Application batchApplication = new Application(
                            tabErreur[0],
                            "GTM",
                            "Conges");
                    if (applicationRepository.findById(tabErreur[0]).isPresent()) {
                        System.out.println("Application presente");
                    } else {
                        applicationRepository.saveAndFlush(batchApplication);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    WebService batchWebService = new WebService(
                            tabErreur[6],
                            tabErreur[5],
                            tabErreur[4],
                            tabErreur[0]);
                    if ((webServiceRepository.findByWebService(tabErreur[4])).isPresent()) {
                        System.out.println("WebService présent en base");
                    } else {
                        webServiceService.saveWebService(batchWebService);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                SvErreur batchSvErreur = new SvErreur(
                        tabErreur[6],
                        tabErreur[7],
                        tabErreur[9],
                        tabErreur[10],
                        tabErreur[13],
                        tabErreur[4]);

                svErreurService.saveSvErreur(batchSvErreur);

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
