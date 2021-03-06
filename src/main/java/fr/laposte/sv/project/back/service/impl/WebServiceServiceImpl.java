package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.WebServiceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

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
            webService.setApplication(app.get());
        }
        //TODO else
        return webServiceRepository.saveAndFlush(webService);
    }

    @Override
    public Optional<WebService> findByWebService(String webService) {
        return webServiceRepository.findByWebService(webService);
    }

    @Override
    public LocalDate findDateCreation(String webService) {
        LocalDate date = webServiceRepository.findByWebService(webService).get().getDateCreation();
        return date;
    }

    @Override
    public int findIdByWebService(String webService) {
        int id = webServiceRepository.findByWebService(webService).get().getId();
        System.out.println(id);
        return id;
    }

    @Override
    public Set<WebService> findWebServiceByApplication(Application codeApplication) {
        Set<WebService> webservice = webServiceRepository.findWebServiceByApplication(codeApplication);
        return webservice;
    }


    @Override
    public WebService modifierWebService(WebService webService, int id) {
        webService.setId(id);
        return webServiceRepository.saveAndFlush(webService);
    }
}
