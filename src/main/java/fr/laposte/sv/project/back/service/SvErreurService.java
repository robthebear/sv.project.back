package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.SvErreur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface SvErreurService {


    @Query(value = "INSERT INTO sv_project.sv_erreur (date_debut, date_fin, libelle_erreur, statut_http, statut_retour, web_service) VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
    SvErreur saveSvErreur(SvErreur svErreur);
}
