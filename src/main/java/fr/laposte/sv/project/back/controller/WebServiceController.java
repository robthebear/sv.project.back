package fr.laposte.sv.project.back.controller;


import fr.laposte.sv.project.back.model.WebService;
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

    @GetMapping("/tout")
    public Collection<WebService> findAll() {
        return webServiceRepository.findAll();
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerWebService(@PathVariable int id) {
        Optional<WebService> optionalWebService = webServiceRepository.findById(id);
        if (optionalWebService.isPresent()) {
            webServiceRepository.deleteById(id);
        }
    }
}
