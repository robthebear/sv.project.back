package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.dto.ResultatDto;
import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.ResultatService;
import fr.laposte.sv.project.back.service.impl.ResultatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/resultat")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @Autowired
    private WebServiceRepository webServiceRepository;

    /**
     * Controller qui appelle le back avec plusieurs parametres
     * @param application
     * @param webService
     * @param dateDebut
     * @param dateFin
     * @return un tableau de resultat nombre et temps de connexion, nombre et temps d'erreurs par webservice.
     */

    @GetMapping("/{application}/{webservice}/{dateDebut}/{dateFin}")
    public Set<ResultatDto> resultat(@PathVariable Application application, @PathVariable("webservice") Integer webService, @PathVariable("dateDebut") String dateDebut, @PathVariable("dateFin") String dateFin) {


        return resultatService.resultat(application, webService, dateDebut, dateFin);
    }
}
