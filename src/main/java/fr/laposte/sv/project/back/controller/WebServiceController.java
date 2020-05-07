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
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/webservice")
public class WebServiceController {

    @Autowired
    private WebServiceRepository webServiceRepository;

    @Autowired
    private WebServiceService webServiceService;


    /**
     * Controller qui affiche tous les webservices
     * @return la liste de tous les webservices
     */
    @GetMapping
    public Collection<WebService> findAll() {
        return webServiceRepository.findAll();
    }

    /**
     * Controller qui recupere un objet webservice avec son nom
     * @param webService
     * @return
     */
    @GetMapping("/parWS/{webService}")
    public Optional<WebService> findByWebService(@PathVariable String webService) {
        return webServiceService.findByWebService(webService);
    }

    /**
     * Controller qui recupere un objet webservice avec un code application
     * @param codeApplication
     * @return le webservice correspondant
     */
    @GetMapping("/parApp/{codeApplication}")
    public Set<WebService> findWebServiceByApplication(@PathVariable Application codeApplication) {
        return webServiceService.findWebServiceByApplication(codeApplication);
    }

    /**
     * Controller qui recupere un objet Webservice grace à son id
     * @param id
     * @return le Webservice correspondant à cet id
     */
    @GetMapping("{id}")
    public Optional<WebService> findbyId(@PathVariable int id){
        return webServiceRepository.findById(id);
    }

    /**
     * Controller qui permet de supprimer un objet webservice dans la base de donnée grace a son id
     * @param id
     */
    @DeleteMapping("{id}")
    public void supprimerWebService(@PathVariable int id) {
        webServiceRepository.deleteById(id);
    }
}
