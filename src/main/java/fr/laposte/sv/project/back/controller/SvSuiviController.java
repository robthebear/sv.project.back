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

    @GetMapping("/parDate/{date}")
    public Set<SvSuivi> findByDate(@PathVariable String date) {
        LocalDate date1 = LocalDate.parse(date);
        return svSuiviService.findByDate(date1);
    }

//    @GetMapping("/parDate/")
//    public ResponseEntity<?> svSuiviParDate(@RequestBody SvSuivi svSuivis) {
////        LocalDate dateD = LocalDate.parse(dateDebut);
////        LocalDate dateF = LocalDate.parse(dateFin);
//        try {
//            return ResponseEntity.ok(svSuiviService.svSuiviParDate(svSuivis.getWebService(), svSuivis.getDate(), svSuivis.getDate()));
//
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

    @GetMapping("/parDate/{date1}/{date2}/{webservice}")
    public Set<SvSuivi> svSuiviParDate(@PathVariable("date1") String dateDebut, @PathVariable("date2") String dateFin, @PathVariable("webservice") WebService webService) {
        LocalDate dateD = LocalDate.parse(dateDebut);
        LocalDate dateF = LocalDate.parse(dateFin);

        return svSuiviService.svSuiviParDate(webService, dateD, dateF);
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
