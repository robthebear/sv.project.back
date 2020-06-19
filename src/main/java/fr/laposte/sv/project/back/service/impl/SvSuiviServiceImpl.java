package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.SvSuiviRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.SvSuiviService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Set<SvSuivi> findSvSuiviByWebService(WebService webService) {
        Set<SvSuivi> svSuivi = svSuiviRepository.findSvSuiviByWebService(webService);
        return svSuivi;
    }




    @Override
    public Set<SvSuivi> svSuiviParDate(WebService webService, String  dateDebut, String dateFin) {
        LocalDate dateD = LocalDate.parse(dateDebut);
        LocalDate dateF = LocalDate.parse(dateFin);
        Set<SvSuivi> svSuivis = svSuiviRepository.findSvSuiviByWebService(webService);
        Set<SvSuivi> suivis = new HashSet<>();


        for (SvSuivi svSuivi : svSuivis) {

            if (svSuivi.getDate().isEqual(dateD) || svSuivi.getDate().isEqual(dateF)) {
                suivis.add(svSuivi);
            } else if (svSuivi.getDate().isAfter(dateD) && svSuivi.getDate().isBefore(dateF)) {
                suivis.add(svSuivi);
            }
        }
        return suivis;
    }


}
