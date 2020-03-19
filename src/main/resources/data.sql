
INSERT INTO sv_project.application (code_application, libelle, type) VALUES ('l3s', 'bla bla bla', 'oreirjgfe');
INSERT INTO sv_project.application (code_application, libelle, type) VALUES ('l5t', 'tuc tic tac', 'blop');


INSERT INTO sv_project.correspondant (id_RH, nom, prenom, fonction, telephone, email) values ('PBGF123', 'Roussely', 'Robert', 'Développer', '0642749481', 'rousselyrobert@wanadoo.fr');
INSERT INTO sv_project.correspondant (id_RH, nom, prenom, fonction, telephone, email) values ('PPDL890', 'Delcroix', 'Jean-Luc', 'Directeur', '0000000000', 'jean-luc.delcroix@laposte.fr');

INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PBGF123', '123456789', 'Admin');
INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PPDL890', 'james', 'Utilisateur');
INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PPQR933', 'prstres', 'Su');

INSERT INTO sv_project.sv_erreur (date_debut, date_fin, libelle_erreur, statut_http, statut_retour, web_service) VALUES ('2020-01-10', '2020-01-11', 'erreur systeme', '403', 'FAILURE', 2);
INSERT INTO sv_project.sv_erreur (date_debut, date_fin, libelle_erreur, statut_http, statut_retour, web_service) VALUES ('2020-03-11', '2020-03-12', 'kmezjgpozjgz', '403', 'FAILURE', 1);

INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service) VALUES ('2020-02-28', 18, 3, 1);
INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service) VALUES ('2020-03-10', 25, 2, 2);

INSERT INTO sv_project.sv_suivi (code_application, date_debut, date_fin, statut_http, statut_retour, web_service) VALUES ('l5t', '2020-03-18', '2020-03-19', '203', 'ok', 1);


INSERT INTO sv_project.web_service (date_creation, libelle_web_service, web_service, application_code_application) VALUES  ('2020-01-21', 'bla bla bla', 'blo blo blo', 'l3s');
INSERT INTO sv_project.web_service  (date_creation, libelle_web_service, web_service, application_code_application) VALUES ('2019-05-10', 'loosing', 'look', 'l5t');

