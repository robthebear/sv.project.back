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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/resultat")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @Autowired
    private WebServiceRepository webServiceRepository;

    @GetMapping("/{application}/{webservice}/{dateDebut}/{dateFin}")
    public Set<ResultatDto> resultat(@PathVariable Application application, @PathVariable("webservice") Integer webService, @PathVariable("dateDebut") String dateD, @PathVariable("dateFin") String dateF) {
        Optional<WebService> webService1 = webServiceRepository.findById(webService);
        LocalDate dateDebut = LocalDate.parse(dateD);
        LocalDate dateFin = LocalDate.parse(dateF);

        return resultatService.resultat(application, webService1, dateDebut, dateFin);
    }
}
