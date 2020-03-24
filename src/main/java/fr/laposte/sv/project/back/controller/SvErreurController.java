package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@CrossOrigin("*")
@RequestMapping("/sverreur")
public class SvErreurController {

    @Autowired
    private SvErreurRepository svErreurRepository;

    @GetMapping("/tout")
    public Collection<SvErreur> findAll() {
        return svErreurRepository.findAll();
    }

    @PostMapping("/ajout")
    public SvErreur ajoutSvErreur (@RequestBody SvErreur svErreur) {
       return svErreurRepository.saveAndFlush(svErreur);
    }

}
