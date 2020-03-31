package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebServiceServiceImpl implements WebServiceService {

    private WebServiceRepository webServiceRepository;

    private ApplicationRepository applicationRepository;

    public WebServiceServiceImpl(WebServiceRepository webServiceRepository, ApplicationRepository applicationRepository) {
        this.webServiceRepository = webServiceRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public WebService saveWebService(WebService webService) {
        final Optional<Application> app = applicationRepository.findById(webService.getApplication().getId());
        if (app.isPresent()) {
            webService.setApplication(app.get());}
        //TODO else
        return webServiceRepository.saveAndFlush(webService);
    }
}
