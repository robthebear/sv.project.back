package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/sverreur")
public class SvErreurController {

    @Autowired
    private SvErreurRepository svErreurRepository;

    @Autowired
    private WebServiceRepository webServiceRepository;

    @GetMapping
    public Collection<SvErreur> findAll() {
        return svErreurRepository.findAll();
    }

//    @PostMapping
//    public SvErreur ajoutSvErreur (@RequestBody SvErreur svErreur) {
//        final Optional<WebService> web = webServiceRepository.findById(svErreur.getWebService().getId());
//        if (web.isPresent()) {
//            svErreur.setWebService(web.get());
//        }
//System.out.print(svErreur);
//        return svErreurRepository.saveAndFlush(svErreur);
//    }

    @PostMapping
    public SvErreur ajoutSvErreur (@RequestBody SvErreur svErreur) {
        final Optional<WebService> web = webServiceRepository.findByWebService(svErreur.getWebService().getWebService());
        if (web.isPresent()) {
            svErreur.setWebService(web.get());


        }
System.out.print(svErreur);
        return svErreurRepository.saveAndFlush(svErreur);
    }
}
