package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvErreurRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvErreurService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Set<SvErreur> svErreurParDate(WebService webService, LocalDate dateDebut, LocalDate dateFin) {
        Set<SvErreur> svErreurs = svErreurRepository.findSvErreurByWebService(webService);
        Set<SvErreur> erreurs = new HashSet<>();

        for (SvErreur svErreur : svErreurs) {
            if (svErreur.getDate().isEqual(dateDebut) || svErreur.getDate().isEqual(dateFin)) {
                erreurs.add(svErreur);
            } else if (svErreur.getDate().isAfter(dateDebut) && svErreur.getDate().isBefore(dateFin)) {
                erreurs.add(svErreur);
            }
        }
        return erreurs;
    }


}
