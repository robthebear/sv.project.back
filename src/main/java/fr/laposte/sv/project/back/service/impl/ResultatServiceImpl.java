package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.dto.LibelleDto;
import fr.laposte.sv.project.back.dto.ResultatDto;
import fr.laposte.sv.project.back.dto.StaturRetourDto;
import fr.laposte.sv.project.back.dto.StatutHttpDto;
import fr.laposte.sv.project.back.model.Application;
import fr.laposte.sv.project.back.model.SvErreur;
import fr.laposte.sv.project.back.model.SvSuivi;
import fr.laposte.sv.project.back.model.WebService;
import fr.laposte.sv.project.back.repository.ApplicationRepository;
import fr.laposte.sv.project.back.repository.WebServiceRepository;
import fr.laposte.sv.project.back.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ResultatServiceImpl implements ResultatService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    WebServiceRepository webServiceRepository;


    @Override
    public Set<ResultatDto> resultat(Application application, int webservice, String dateD, String dateF) {
        Optional<WebService> webservices = webServiceRepository.findById(webservice);
        LocalDate dateDebut = LocalDate.parse(dateD);
        LocalDate dateFin = LocalDate.parse(dateF);
        Set<ResultatDto> resultats = new HashSet<>();
        Set<WebService> webServices = webServiceRepository.findWebServiceByApplication(application);

        ResultatDto resultatDto = new ResultatDto();


//        Set<StaturRetourDto> retours = new HashSet<>();
        StaturRetourDto retour = new StaturRetourDto();
//        Set<LibelleDto> libelles = new HashSet<>();
        LibelleDto libelle = new LibelleDto();
//        Set<StatutHttpDto> https = new HashSet<>();
        StatutHttpDto http = new StatutHttpDto();

//Vérifie si la liste application est présente
        if (!application.getId().isEmpty()) {
//Vérifie si la liste de webservice de l'application est absente
            if (!webservices.isPresent()) {
                try {
                    for (WebService webService : webServices) {
                        Set<StatutHttpDto> https = new HashSet<>();
                        Set<LibelleDto> libelles = new HashSet<>();
                        Set<StaturRetourDto> retours = new HashSet<>();
                        Set<SvErreur> svErreurs = new HashSet<>();


                        for (SvErreur svErreur : webService.getSvErreur()) {
                            if (svErreur.getDate().isEqual(dateDebut) || svErreur.getDate().isEqual(dateFin)) {
                                svErreurs.add(svErreur);
                            } else if (svErreur.getDate().isAfter(dateDebut) && svErreur.getDate().isBefore(dateFin)) {
                                svErreurs.add(svErreur);
                            }
                        }
                        System.out.println(svErreurs);

                        for (SvErreur svErreur : svErreurs) {



                            if ( resultats.isEmpty() || !resultatDto.getWebService().equals(svErreur.getWebService().getWebService())) {
                                if (https.isEmpty()) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svErreur.getStatutHttp(), 1);
                                    https.add(http);
                                }
                                if (retours.isEmpty()) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                    retours.add(retour);
                                }
                                if (libelles.isEmpty()) {
                                    libelles = new HashSet<>();
                                    libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                    libelles.add(libelle);
                                }
                                resultatDto = new ResultatDto(svErreur.getWebService().getWebService(), dateDebut, dateFin, 0, 0, 1, svErreur.getDuree(), libelles, retours, https);
                                resultats.add(resultatDto);

                            } else if (resultatDto.getWebService().equals(svErreur.getWebService().getWebService())) {
                                resultatDto.setTpsErreur(resultatDto.getTpsErreur() + svErreur.getDuree());
                                resultatDto.setNbErreur(resultatDto.getNbErreur() + 1);
                                if (resultatDto.getHttps().equals(https)) {
                                    if (!http.getHttp().equals(svErreur.getStatutHttp())) {
                                        http.setHttp(svErreur.getStatutHttp());
                                        http.setNbHttp(1);
                                        https.add(http);
                                    } else if (http.getHttp().equals(svErreur.getStatutHttp())) {

                                        http.setNbHttp(http.getNbHttp() + 1);
                                        https.add(http);
                                    }
                                } else if (!resultatDto.getHttps().equals(https)) {

                                    http.setHttp(svErreur.getStatutHttp());
                                    http.setNbHttp(1);
                                    https.add(http);
                                }resultatDto.setHttps(https);


                                if (resultatDto.getLibelles().equals(libelles)) {
                                    if (!libelle.getLibelle().equals(svErreur.getLibelleErreur())) {
                                        libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                        libelles.add(libelle);
                                        resultatDto.setLibelles(libelles);
                                    } else if (libelle.getLibelle().equals(svErreur.getLibelleErreur())) {

                                        libelle.setLibelle(svErreur.getLibelleErreur());
                                        libelle.setNbLibelle(libelle.getNbLibelle() + 1);
                                        libelles.add(libelle);
                                        resultatDto.setLibelles(libelles);
                                    }
                                } else if (!resultatDto.getLibelles().equals(libelles)) {
                                    libelles = new HashSet<>();
                                    libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                    libelles.add(libelle);
                                    resultatDto.setLibelles(libelles);
                                }


                                if (resultatDto.getRetours().equals(retours)) {
                                    if (!retour.getRetour().equals(svErreur.getStatutRetour())) {
                                        retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    } else if (retour.getRetour().equals(svErreur.getStatutRetour())) {

                                        retour.setRetour(svErreur.getStatutRetour());
                                        retour.setNbRetour(retour.getNbRetour() + 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    }
                                } else if (!resultatDto.getRetours().equals(retours)) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                    retours.add(retour);
                                    resultatDto.setRetours(retours);
                                }


                                resultats.add(resultatDto);
                            }
                        }


                        Set<SvSuivi> svSuivis = new HashSet<>();
                        for (SvSuivi svSuivi : webService.getSvSuivi()) {


                            if (svSuivi.getDate().isEqual(dateDebut) || svSuivi.getDate().isEqual(dateFin)) {
                                svSuivis.add(svSuivi);
                            } else if (svSuivi.getDate().isAfter(dateDebut) && svSuivi.getDate().isBefore(dateFin)) {
                                svSuivis.add(svSuivi);
                            }
                        }
                        System.out.println(svSuivis);

                        for (SvSuivi svSuivi : svSuivis) {
//                        Set<StatutHttpDto> https = new HashSet<>();
//                        Set<LibelleDto> libelles = new HashSet<>();
//                        Set<StaturRetourDto> retours = new HashSet<>();

//                        if (resultats.isEmpty()) {
//
//                            if (https.isEmpty()) {
//                                https = new HashSet<>();
//                                http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
//                                https.add(http);
//                            }
//
//
//                            if (retours.isEmpty()) {
//                                retours = new HashSet<>();
//                                retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
//                                retours.add(retour);
//
//                            }
//                            resultatDto = new ResultatDto(svSuivi.getWebService().getWebService(), dateDebut, dateFin, 1, svSuivi.getDuree(), 0, 0, libelles, retours, https);
//                            resultats.add(resultatDto);
//                        } else
                            if (resultats.isEmpty() || !resultatDto.getWebService().equals(svSuivi.getWebService().getWebService())) {
                                if (https.isEmpty()) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                    https.add(http);
                                }


                                if (retours.isEmpty()) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                    retours.add(retour);

                                }
                                resultatDto = new ResultatDto(svSuivi.getWebService().getWebService(), dateDebut, dateFin, 1, svSuivi.getDuree(), 0, 0, libelles, retours, https);

                                resultats.add(resultatDto);

                            } else if (resultatDto.getWebService().equals(svSuivi.getWebService().getWebService())) {
//                            resultatDto.getWebService().equals(svSuivi.getWebService().getWebService());
                                resultatDto.setTpsConnexion(resultatDto.getTpsConnexion() + svSuivi.getDuree());
                                resultatDto.setNbConnexion(resultatDto.getNbConnexion() + 1);
                                if (resultatDto.getHttps().equals(https)) {
                                    if (!http.getHttp().equals(svSuivi.getStatutHttp())) {
                                        http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                        https.add(http);
                                        resultatDto.setHttps(https);

                                    } else if (http.getHttp().equals(svSuivi.getStatutHttp())) {
                                        http.setHttp(svSuivi.getStatutHttp());
                                        http.setNbHttp(http.getNbHttp() + 1);
                                        https.add(http);
                                        resultatDto.setHttps(https);
                                    }
//
                                } else if (!resultatDto.getHttps().equals(https)) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                    https.add(http);
                                    resultatDto.setHttps(https);
                                }


                                if (resultatDto.getRetours().equals(retours)) {
                                    if (!retour.getRetour().equals(svSuivi.getStatutRetour())) {
                                        retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    } else if (retour.getRetour().equals(svSuivi.getStatutRetour())) {

                                        retour.setRetour(svSuivi.getStatutRetour());
                                        retour.setNbRetour(retour.getNbRetour() + 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    }
                                } else if (!resultatDto.getRetours().equals(retours)) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                    retours.add(retour);
                                    resultatDto.setRetours(retours);
                                }


                                resultats.add(resultatDto);
                            }


                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                if (webservices.isPresent()) {
                    try {

                        Set<StatutHttpDto> https = new HashSet<>();
                        Set<LibelleDto> libelles = new HashSet<>();
                        Set<StaturRetourDto> retours = new HashSet<>();
                        Set<SvErreur> svErreurs = new HashSet<>();


                        for (SvErreur svErreur : webservices.get().getSvErreur()) {
                            if (svErreur.getDate().isEqual(dateDebut) || svErreur.getDate().isEqual(dateFin)) {
                                svErreurs.add(svErreur);
                            } else if (svErreur.getDate().isAfter(dateDebut) && svErreur.getDate().isBefore(dateFin)) {
                                svErreurs.add(svErreur);
                            }
                        }
                        System.out.println(svErreurs);

                        for (SvErreur svErreur : svErreurs) {


                            if (resultats.isEmpty() || !resultatDto.getWebService().equals(svErreur.getWebService().getWebService())) {
                                if (https.isEmpty()) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svErreur.getStatutHttp(), 1);
                                    https.add(http);
                                }
                                if (retours.isEmpty()) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                    retours.add(retour);
                                }
                                if (libelles.isEmpty()) {
                                    libelles = new HashSet<>();
                                    libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                    libelles.add(libelle);
                                }
                                resultatDto = new ResultatDto(svErreur.getWebService().getWebService(), dateDebut, dateFin, 0, 0, 1, svErreur.getDuree(), libelles, retours, https);
                                resultats.add(resultatDto);

                            } else if (resultatDto.getWebService().equals(svErreur.getWebService().getWebService())) {
                                resultatDto.setTpsErreur(resultatDto.getTpsErreur() + svErreur.getDuree());
                                resultatDto.setNbErreur(resultatDto.getNbErreur() + 1);
                                if (resultatDto.getHttps().equals(https)) {
                                    if (!http.getHttp().equals(svErreur.getStatutHttp())) {
                                        http.setHttp(svErreur.getStatutHttp());
                                        http.setNbHttp(1);
                                        https.add(http);
                                    } else if (http.getHttp().equals(svErreur.getStatutHttp())) {

                                        http.setNbHttp(http.getNbHttp() + 1);
                                        https.add(http);
                                    }
                                } else if (!resultatDto.getHttps().equals(https)) {

                                    http.setHttp(svErreur.getStatutHttp());
                                    http.setNbHttp(1);
                                    https.add(http);
                                }
                                resultatDto.setHttps(https);


                                if (resultatDto.getLibelles().equals(libelles)) {
                                    if (!libelle.getLibelle().equals(svErreur.getLibelleErreur())) {
                                        libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                        libelles.add(libelle);
                                        resultatDto.setLibelles(libelles);
                                    } else if (libelle.getLibelle().equals(svErreur.getLibelleErreur())) {

                                        libelle.setLibelle(svErreur.getLibelleErreur());
                                        libelle.setNbLibelle(libelle.getNbLibelle() + 1);
                                        libelles.add(libelle);
                                        resultatDto.setLibelles(libelles);
                                    }
                                } else if (!resultatDto.getLibelles().equals(libelles)) {
                                    libelles = new HashSet<>();
                                    libelle = new LibelleDto(svErreur.getLibelleErreur(), 1);
                                    libelles.add(libelle);
                                    resultatDto.setLibelles(libelles);
                                }


                                if (resultatDto.getRetours().equals(retours)) {
                                    if (!retour.getRetour().equals(svErreur.getStatutRetour())) {
                                        retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    } else if (retour.getRetour().equals(svErreur.getStatutRetour())) {

                                        retour.setRetour(svErreur.getStatutRetour());
                                        retour.setNbRetour(retour.getNbRetour() + 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    }
                                } else if (!resultatDto.getRetours().equals(retours)) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svErreur.getStatutRetour(), 1);
                                    retours.add(retour);
                                    resultatDto.setRetours(retours);
                                }


                                resultats.add(resultatDto);
                            }
                        }


                        Set<SvSuivi> svSuivis = new HashSet<>();
                        for (SvSuivi svSuivi : webservices.get().getSvSuivi()) {


                            if (svSuivi.getDate().isEqual(dateDebut) || svSuivi.getDate().isEqual(dateFin)) {
                                svSuivis.add(svSuivi);
                            } else if (svSuivi.getDate().isAfter(dateDebut) && svSuivi.getDate().isBefore(dateFin)) {
                                svSuivis.add(svSuivi);
                            }
                        }
                        System.out.println(svSuivis);

                        for (SvSuivi svSuivi : svSuivis) {

                            if (resultats.isEmpty() || !resultatDto.getWebService().equals(svSuivi.getWebService().getWebService())) {
                                if (https.isEmpty()) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                    https.add(http);
                                }


                                if (retours.isEmpty()) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                    retours.add(retour);

                                }
                                resultatDto = new ResultatDto(svSuivi.getWebService().getWebService(), dateDebut, dateFin, 1, svSuivi.getDuree(), 0, 0, libelles, retours, https);

                                resultats.add(resultatDto);

                            } else if (resultatDto.getWebService().equals(svSuivi.getWebService().getWebService())) {
//                            resultatDto.getWebService().equals(svSuivi.getWebService().getWebService());
                                resultatDto.setTpsConnexion(resultatDto.getTpsConnexion() + svSuivi.getDuree());
                                resultatDto.setNbConnexion(resultatDto.getNbConnexion() + 1);
                                if (resultatDto.getHttps().equals(https)) {
                                    if (!http.getHttp().equals(svSuivi.getStatutHttp())) {
                                        http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                        https.add(http);
                                        resultatDto.setHttps(https);

                                    } else if (http.getHttp().equals(svSuivi.getStatutHttp())) {
                                        http.setHttp(svSuivi.getStatutHttp());
                                        http.setNbHttp(http.getNbHttp() + 1);
                                        https.add(http);
                                        resultatDto.setHttps(https);
                                    }
//
                                } else if (!resultatDto.getHttps().equals(https)) {
                                    https = new HashSet<>();
                                    http = new StatutHttpDto(svSuivi.getStatutHttp(), 1);
                                    https.add(http);
                                    resultatDto.setHttps(https);
                                }


                                if (resultatDto.getRetours().equals(retours)) {
                                    if (!retour.getRetour().equals(svSuivi.getStatutRetour())) {
                                        retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    } else if (retour.getRetour().equals(svSuivi.getStatutRetour())) {

                                        retour.setRetour(svSuivi.getStatutRetour());
                                        retour.setNbRetour(retour.getNbRetour() + 1);
                                        retours.add(retour);
                                        resultatDto.setRetours(retours);
                                    }
                                } else if (!resultatDto.getRetours().equals(retours)) {
                                    retours = new HashSet<>();
                                    retour = new StaturRetourDto(svSuivi.getStatutRetour(), 1);
                                    retours.add(retour);
                                    resultatDto.setRetours(retours);
                                }


                                resultats.add(resultatDto);
                            }


                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }

        }


        return resultats;
    }


}

