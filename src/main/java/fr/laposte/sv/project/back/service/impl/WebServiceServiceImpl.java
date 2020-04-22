package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.WebServiceService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
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


    //    public ResponseEntity<WebService> updateWebService(WebService webService) {
//        Optional <WebService> webServiceEnBase = Optional.ofNullable((webServiceRepository.findByWebService(webService.getWebService())));
//        if (webServiceEnBase.isPresent()){
//if (webService.getDateCreation() == null) {
//    webService.setDateCreation(webServiceEnBase.get().getDateCreation());
//} if (webService.getLibelleWebService() == null) {
//    webService.setLibelleWebService(webServiceEnBase.get().getLibelleWebService());
//            } if (webService.getWebService() == null) {
//    webService.setWebService(webServiceEnBase.get().getWebService());
//            }
//         return new ResponseEntity<WebService>(webServiceRepository.saveAndFlush(webService));}
//    }

    @Override
    public WebService modifierWebService(WebService webService, int id) {
            webService.setId(id);
            return webServiceRepository.saveAndFlush(webService);
    }
}
