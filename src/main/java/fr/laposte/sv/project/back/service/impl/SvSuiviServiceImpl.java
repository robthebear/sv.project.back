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

//    @Override
//    public Set<SvSuivi> findByDate(LocalDate date) {
//        Set<SvSuivi> svSuivi = svSuiviRepository.findByDate(date);
//        return svSuivi;
//    }


    @Override
    public Set<SvSuivi> svSuiviParDate(WebService webService, LocalDate dateDebut, LocalDate dateFin) {
        Set<SvSuivi> svSuivis = svSuiviRepository.findSvSuiviByWebService(webService);
        Set<SvSuivi> suivis = new HashSet<>();
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");


        for (SvSuivi svSuivi : svSuivis) {
//            String dateR[] = format.format(svSuivi.getDateDebut()).split(" ");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate date = LocalDate.parse(dateR[0], formatter);
            if (svSuivi.getDate().isEqual(dateDebut) || svSuivi.getDate().isEqual(dateFin)) {
                suivis.add(svSuivi);
            } else if (svSuivi.getDate().isAfter(dateDebut) && svSuivi.getDate().isBefore(dateFin)) {
                suivis.add(svSuivi);
            }
        }
        return suivis;
    }

//    public SvSuiviDto svSuiviEnLecture(SvSuivi svSuivi){
//
//        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
//        Date db = svSuivi.getDateDebut();
//        Date df = svSuivi.getDateFin();
//
//        String dateR[] = format.format(db).split(" ");
//        String heure[] = format.format(df).split(" ");
//        String d[] = dateR[1].split(":");
//        String f[] = heure[1].split(":");
//        int debut = Integer.parseInt(d[2]);
//        int fin = Integer.parseInt(f[2]);
//        SvSuiviDto svSuiviDto = new SvSuiviDto();
//
//        svSuiviDto.setId(svSuivi.getId());
//        svSuiviDto.setDate(LocalDate.parse(dateR[0], formatter));
//        svSuiviDto.setHeureDebut(LocalTime.parse(dateR[1], formatTime));
//        svSuiviDto.setDuree(fin - debut);
//        svSuiviDto.setStatutHttp(svSuivi.getStatutHttp());
//        svSuiviDto.setStatutRetour(svSuivi.getStatutRetour());
//        svSuiviDto.setWebService(svSuivi.getWebService());
//
//        return svSuiviDto;
//    }

//    @Override
//    public Set<SvSuivi> svSuiviParDate(WebService webService, LocalDate dateDebut, LocalDate dateFin) {
//        Set<SvSuivi> svSuivis = new HashSet<>();
//        Set<SvSuivi> suivis = new HashSet<SvSuivi>();
//        List<SvSuivi> svSuiviSansWs;
//
//
//            svSuiviSansWs = svSuiviRepository.findAll();
//            try {
//                for (SvSuivi svSuivi : svSuiviSansWs) {
//                    if (svSuivi.getDate().isEqual(dateDebut) || svSuivi.getDate().isEqual(dateFin)) {
//                        suivis.add(svSuivi);
//                    } else if (svSuivi.getDate().isAfter(dateDebut) && svSuivi.getDate().isBefore(dateFin)) {
//                        suivis.add(svSuivi);
//                    }
//                } return suivis;
//            } catch (Exception e) {
//                e.printStackTrace();
//             }
//       if (svSuiviRepository.findSvSuiviByWebService(webService).equals(suivis.getClass())) {
//            try {
//                for (SvSuivi svSuivi : svSuivis) {
//
//                    if (svSuivi.getDate().isEqual(dateDebut) || svSuivi.getDate().isEqual(dateFin)) {
//                        suivis.add(svSuivi);
//                    } else if (svSuivi.getDate().isAfter(dateDebut) && svSuivi.getDate().isBefore(dateFin)) {
//                        suivis.add(svSuivi);
//                    }
//
//                } return suivis;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
