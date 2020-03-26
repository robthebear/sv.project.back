package fr.laposte.sv.project.back.controller;


import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.beans.factory.annotation.*;
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

    @GetMapping("/parWS/{webService}")
    public Optional<WebService> findByWebService(@PathVariable String webService) {
        return webServiceRepository.findByWebService(webService);
    }

    @PostMapping
    public WebService ajoutWebservice(@RequestBody WebService webService) {
        System.out.print(webService);
        final Optional<Application> app = applicationRepository.findById(webService.getApplication().getId());
        if (app.isPresent()) {
            webService.setApplication(app.get());}
        //TODO else
        return webServiceRepository.saveAndFlush(webService);
    }

//    @PostMapping
//    public WebService ajoutWebservice(@RequestBody WebService webService, @RequestBody Application application) {
//webService.setApplication(application);
//return webServiceRepository.saveAndFlush(webService.);
//    }



    @DeleteMapping("/supprimer/{id}")
    public void supprimerWebService(@PathVariable int id) {
        Optional<WebService> optionalWebService = webServiceRepository.findById(id);
        if (optionalWebService.isPresent()) {
            webServiceRepository.deleteById(id);
        }
    }
}
