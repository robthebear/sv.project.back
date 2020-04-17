package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvSuiviRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvSuiviService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SvSuiviServiceImpl implements SvSuiviService {

    private SvSuiviRepository svSuiviRepository;

    private WebServiceRepository webServiceRepository;

    public SvSuiviServiceImpl(SvSuiviRepository svSuiviRepository, WebServiceRepository webServiceRepository) {
        this.svSuiviRepository = svSuiviRepository;
        this.webServiceRepository = webServiceRepository;
    }

    @Override
    public SvSuivi saveSvSuivi(SvSuivi svSuivi) {
        final Optional<WebService> web = webServiceRepository.findByWebService(svSuivi.getWebService().getWebService());
        if (web.isPresent()) {
            svSuivi.setWebService(web.get());
        }
        return svSuiviRepository.saveAndFlush(svSuivi);
    }
}
