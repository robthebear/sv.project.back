package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SvErreurServiceImpl implements SvErreurService {

    private SvErreurRepository svErreurRepository;

    private WebServiceRepository webServiceRepository;

    public SvErreurServiceImpl(SvErreurRepository svErreurRepository, WebServiceRepository webServiceRepository) {
        this.svErreurRepository = svErreurRepository;
        this.webServiceRepository = webServiceRepository;
    }

    @Override
    public SvErreur saveSvErreur(SvErreur svErreur) {
        final Optional<WebService> web = webServiceRepository.findByWebService(svErreur.getWebService().getWebService());
        if (web.isPresent()) {
            svErreur.setWebService(web.get());
        }
        return svErreurRepository.saveAndFlush(svErreur);
    }



}
