package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvSuiviRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvSuiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


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


//            @PostMapping
//    public SvSuivi ajoutSvSuivi (@RequestBody SvSuivi svSuivi) {
//                final Optional<WebService> web = webServiceRepository.findByWebService(svSuivi.getWebService().getWebService());
//                if (web.isPresent()){
//                    svSuivi.setWebService(web.get());
//                }
//                System.out.print(svSuivi);
//                return svSuiviService.saveSvSuivi(svSuivi);
//            }
}
