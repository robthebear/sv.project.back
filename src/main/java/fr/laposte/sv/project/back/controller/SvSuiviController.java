package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.dto.SvSuiviDto;
import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvSuiviRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvSuiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("svsuivi")
public class SvSuiviController {

    @Autowired
    SvSuiviRepository svSuiviRepository;

    @Autowired
    WebServiceRepository webServiceRepository;
    @Autowired
    SvSuiviService svSuiviService;

    @GetMapping
    public Collection<SvSuivi> findAll() {

        return svSuiviRepository.findAll();
    }

    @GetMapping("/parWebService/{webService}")
    public Set<SvSuivi> findSvSuiviByWebService(@PathVariable WebService webService) {
        return svSuiviService.findSvSuiviByWebService(webService);
    }



    @GetMapping("/parDate/{date1}/{date2}/{webservice}")
    public Set<SvSuivi> svSuiviParDate(@PathVariable("date1") String dateDebut, @PathVariable("date2") String dateFin, @PathVariable("webservice") WebService webService) {
        LocalDate dateD = LocalDate.parse(dateDebut);
        LocalDate dateF = LocalDate.parse(dateFin);

        return svSuiviService.svSuiviParDate(webService, dateD, dateF);
    }



}
