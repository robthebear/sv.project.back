package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.dto.SvErreurDto;
import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import fr.laposte.sv.project.back.service.SvErreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sverreur")
public class SvErreurController {


    @Autowired
    private SvErreurRepository svErreurRepository;

    @Autowired
    private SvErreurService svErreurService;


    @GetMapping
    public Collection<SvErreur> findAll() {
        return svErreurRepository.findAll();
    }

    @GetMapping("/parDate/{date1}/{date2}/{webservice}")
    public Set<SvErreur> svErreurParDate(@PathVariable("date1") String dateDebut, @PathVariable("date2") String dateFin, @PathVariable("webservice") WebService webservice) {
        LocalDate dateD = LocalDate.parse(dateDebut);
        LocalDate dateF = LocalDate.parse(dateFin);

        return svErreurService.svErreurParDate(webservice, dateD, dateF);
    }

}
