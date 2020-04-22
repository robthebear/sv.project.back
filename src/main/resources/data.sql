


INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service_id) VALUES ('2020-02-28', 18, 3, 1);
INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service_id) VALUES ('2020-03-10', 25, 2, 2);

INSERT INTO sv_project.correspondant (id, nom, prenom, fonction, telephone, email) values ('PBGF123', 'Roussely', 'Robert', 'Développer', '0642749481', 'rousselyrobert@wanadoo.fr');
INSERT INTO sv_project.correspondant (id, nom, prenom, fonction, telephone, email) values ('PPDL890', 'Delcroix', 'Jean-Luc', 'Directeur', '0000000000', 'jean-luc.delcroix@laposte.fr');
INSERT INTO sv_project.correspondant (id, nom, prenom, fonction, telephone, email) values ('PPQR933', 'Delannoy', 'Laurence', 'Directeur', '0000000000', 'laurence.delannoy@laposte.fr');


INSERT INTO sv_project.habilitation (id, mot_de_passe) VALUES ('PBGF123', '123456789');
INSERT INTO sv_project.habilitation (id, mot_de_passe) VALUES ('PPDL890', 'james');
INSERT INTO sv_project.habilitation (id, mot_de_passe) VALUES ('PPQR933', 'prstres');

INSERT INTO sv_project.habilitation_role_list (habilitation_id, role_list) VALUES ('PBGF123', 'ROLE_ADMIN');
INSERT INTO sv_project.habilitation_role_list (habilitation_id, role_list) VALUES ('PPDL890', 'ROLE_UTILISATEUR');
INSERT INTO sv_project.habilitation_role_list (habilitation_id, role_list) VALUES ('PPQR933', 'ROLE_SUPERADMIN');







