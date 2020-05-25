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
//            String dateR[] = format.format(svErreur.getDateDebut()).split(" ");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//LocalDate date = LocalDate.parse(dateR[0], formatter);

            if (svErreur.getDate().isEqual(dateDebut) || svErreur.getDate().isEqual(dateFin)) {
                erreurs.add(svErreur);
            } else if (svErreur.getDate().isAfter(dateDebut) && svErreur.getDate().isBefore(dateFin)) {
                erreurs.add(svErreur);
            }
        }
        return erreurs;
    }

//
//    public SvErreurDto svErreurEnLecture(SvErreur svErreur) {
//
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
//    Date db = svErreur.getDateDebut();
//    Date df = svErreur.getDateFin();
//
//    String dateR[] = format.format(db).split(" ");
//    String heure[] = format.format(df).split(" ");
//        String d[] = dateR[1].split(":");
//        String f[] = heure[1].split(":");
//        int debut = Integer.parseInt(d[2]);
//        int fin = Integer.parseInt(f[2]);
//    SvErreurDto svErreurDto = new SvErreurDto();
//
//    svErreurDto.setId(svErreur.getId());
//    svErreurDto.setDate(LocalDate.parse(dateR[0], formatter));
//    svErreurDto.setHeureDebut(LocalTime.parse(dateR[1], formatTime));
//    svErreurDto.setDuree(fin - debut);
//    svErreurDto.setLibelleErreur(svErreur.getLibelleErreur());
//    svErreurDto.setStatutHttp(svErreur.getStatutHttp());
//    svErreurDto.setStatutRetour(svErreur.getStatutRetour());
//    svErreurDto.setWebService(svErreur.getWebService());
//
//    return svErreurDto;
//}
}
