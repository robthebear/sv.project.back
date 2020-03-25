package fr.laposte.sv.project.back.controller;


import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/webservice")
public class WebServiceController {

    @Autowired
    private WebServiceRepository webServiceRepository;

    @Autowired

    private ApplicationRepository applicationRepository;

    @GetMapping
    public Collection<WebService> findAll() {
        return webServiceRepository.findAll();
    }

    @PostMapping
    public WebService ajoutWebservice (@RequestBody WebService webService) {
        System.out.print(webService);

        return webServiceRepository.saveAndFlush(webService);
    }

//        @PostMapping("/ajout")
//    public void ajoutWebservice (@RequestBody WebService webService) {
//        webServiceRepository.save(webService);
//    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerWebService(@PathVariable int id) {
        Optional<WebService> optionalWebService = webServiceRepository.findById(id);
        if (optionalWebService.isPresent()) {
            webServiceRepository.deleteById(id);
        }
    }
}
