package fr.laposte.sv.project.back.controller;


import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

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
}
