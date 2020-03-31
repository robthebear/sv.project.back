



INSERT INTO sv_project.correspondant (id, nom, prenom, fonction, telephone, email) values ('PBGF123', 'Roussely', 'Robert', 'Développer', '0642749481', 'rousselyrobert@wanadoo.fr');
INSERT INTO sv_project.correspondant (id, nom, prenom, fonction, telephone, email) values ('PPDL890', 'Delcroix', 'Jean-Luc', 'Directeur', '0000000000', 'jean-luc.delcroix@laposte.fr');

INSERT INTO sv_project.habilitation (id, mot_de_passe, role) VALUES ('PBGF123', '123456789', 'UTILISATEUR');
INSERT INTO sv_project.habilitation (id, mot_de_passe, role) VALUES ('PPDL890', 'james', 'ADMIN');
INSERT INTO sv_project.habilitation (id, mot_de_passe, role) VALUES ('PPQR933', 'prstres', 'SUPERADMIN');


INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service_id) VALUES ('2020-02-28', 18, 3, 1);
INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service_id) VALUES ('2020-03-10', 25, 2, 2);

INSERT INTO sv_project.sv_suivi (date_debut, date_fin, statut_http, statut_retour, web_service) VALUES ('2020-03-18', '2020-03-19', '203', 'ok', 1);



