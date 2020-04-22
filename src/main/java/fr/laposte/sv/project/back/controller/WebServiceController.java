package fr.laposte.sv.project.back.controller;


import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.WebServiceService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/webservice")
public class WebServiceController {

    @Autowired
    private WebServiceRepository webServiceRepository;

    @Autowired
    private WebServiceService webServiceService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping
    public Collection<WebService> findAll() {
        return webServiceRepository.findAll();
    }

    @GetMapping("/parWS/{webService}")
    public Optional<WebService> findByWebService(@PathVariable String webService) {
        return webServiceService.findByWebService(webService);
    }


//    @PostMapping
//    public WebService ajoutWebservice(@RequestBody WebService webService) {
//        System.out.print(webService);
//        final Optional<Application> app = applicationRepository.findById(webService.getApplication().getId());
//        if (app.isPresent()) {
//            webService.setApplication(app.get());}
//        //TODO else
//        return webServiceService.saveWebService(webService);
//    }

//    @PostMapping
//    public WebService ajoutWebservice(@RequestBody WebService webService, @RequestBody Application application) {
//webService.setApplication(application);
//return webServiceRepository.saveAndFlush(webService.);
//    }

//    @PutMapping
//    public WebService updateWebService(WebService webService) {
//        return webServiceService.updateWebService(webService);
//    }


    @DeleteMapping("{id}")
    public void supprimerWebService(@PathVariable int id) {
//        Optional<WebService> optionalWebService = webServiceRepository.findById(id);
//        if (optionalWebService.isPresent()) {
//            webServiceRepository.deleteById(id);
//        }
        webServiceRepository.deleteById(id);
    }
}
