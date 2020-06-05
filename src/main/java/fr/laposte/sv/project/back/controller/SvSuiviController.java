package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvSuiviRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvSuiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;


@RestController
@RequestMapping("svsuivi")
public class SvSuiviController {

    @Autowired
    SvSuiviRepository svSuiviRepository;

    @Autowired
    WebServiceRepository webServiceRepository;
    @Autowired
    SvSuiviService svSuiviService;

    /**
     * Controller qui appelle toutes les connexions
     * @return la liste de toutes les connexions
     */
    @GetMapping
    public Collection<SvSuivi> findAll() {

        return svSuiviRepository.findAll();
    }

    /**
     * controller qui recherche une liste de connexion par webservice
     * @param webService
     * @return la liste de connexion correspondante
     */
    @GetMapping("/parWebService/{webService}")
    public Set<SvSuivi> findSvSuiviByWebService(@PathVariable WebService webService) {
        return svSuiviService.findSvSuiviByWebService(webService);
    }


    /**
     * Controller qui recherche une connexion par date de debut, de fin et par webservice
     * @param dateDebut
     * @param dateFin
     * @param webService
     * @return la liste de connexion triée
     */
    @GetMapping("/parDate/{date1}/{date2}/{webservice}")
    public Set<SvSuivi> svSuiviParDate(@PathVariable("date1") String dateDebut, @PathVariable("date2") String dateFin, @PathVariable("webservice") WebService webService) {
        LocalDate dateD = LocalDate.parse(dateDebut);
        LocalDate dateF = LocalDate.parse(dateFin);

        return svSuiviService.svSuiviParDate(webService, dateD, dateF);
    }



}
