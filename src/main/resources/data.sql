
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: HABILITATION
#------------------------------------------------------------

CREATE TABLE HABILITATION(
                             ID_RH        Varchar (7) NOT NULL ,
                             ROLE         Varchar (50) NOT NULL ,
                             MOT_DE_PASSE Varchar (50) NOT NULL
    ,CONSTRAINT HABILITATION_PK PRIMARY KEY (ID_RH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: APPLICATION
#------------------------------------------------------------

CREATE TABLE APPLICATION(
                            Code_Application Varchar (3) NOT NULL ,
                            LIBELLE          Varchar (50) NOT NULL ,
                            TYPE             Varchar (50) NOT NULL
    ,CONSTRAINT APPLICATION_PK PRIMARY KEY (Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CORRESPONDANT
#------------------------------------------------------------

CREATE TABLE CORRESPONDANT(
                              Id_RH     Varchar (7) NOT NULL ,
                              NOM       Varchar (50) NOT NULL ,
                              PRENOM    Varchar (50) NOT NULL ,
                              FONCTION  Varchar (50) NOT NULL ,
                              EMAIL     Varchar (50) NOT NULL ,
                              TELEPHONE Varchar (50) NOT NULL
    ,CONSTRAINT CORRESPONDANT_PK PRIMARY KEY (Id_RH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: WEB_SERVICE
#------------------------------------------------------------

CREATE TABLE WEB_SERVICE(
                            ID                 Int  Auto_increment  NOT NULL ,
                            WEB_SERVICE        Varchar (50) NOT NULL ,
                            LIBELLE_WEBSERVICE Varchar (50) NOT NULL ,
                            DATE_CREATION      Date NOT NULL ,
                            Code_Application   Varchar (3) NOT NULL
    ,CONSTRAINT WEB_SERVICE_PK PRIMARY KEY (ID)

    ,CONSTRAINT WEB_SERVICE_APPLICATION_FK FOREIGN KEY (Code_Application) REFERENCES APPLICATION(Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_ERREUR
#------------------------------------------------------------

CREATE TABLE SV_ERREUR(
                          ID             Int  Auto_increment  NOT NULL ,
                          DATE_DEBUT     Date NOT NULL ,
                          DATE_FIN       Date NOT NULL ,
                          STATUT_RETOUR  Varchar (50) NOT NULL ,
                          STATUT_HTTP    Varchar (50) NOT NULL ,
                          LIBELLE_ERREUR Varchar (50) NOT NULL ,
                          ID_WEB_SERVICE Int NOT NULL
    ,CONSTRAINT SV_ERREUR_PK PRIMARY KEY (ID)

    ,CONSTRAINT SV_ERREUR_WEB_SERVICE_FK FOREIGN KEY (ID_WEB_SERVICE) REFERENCES WEB_SERVICE(ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_SUIVI
#------------------------------------------------------------

CREATE TABLE SV_SUIVI(
                         ID               Int  Auto_increment  NOT NULL ,
                         CODE_APPLICATION Varchar (50) NOT NULL ,
                         DATE_DEBUT       Date NOT NULL ,
                         DATE_FIN         Date NOT NULL ,
                         STATUS_RETOUR    Varchar (50) NOT NULL ,
                         STATUT_HTTP      Varchar (50) NOT NULL ,
                         ID_WEB_SERVICE   Int NOT NULL
    ,CONSTRAINT SV_SUIVI_PK PRIMARY KEY (ID)

    ,CONSTRAINT SV_SUIVI_WEB_SERVICE_FK FOREIGN KEY (ID_WEB_SERVICE) REFERENCES WEB_SERVICE(ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_STATISTIQUE
#------------------------------------------------------------

CREATE TABLE SV_STATISTIQUE(
                               ID             Int  Auto_increment  NOT NULL ,
                               DATE           Date NOT NULL ,
                               POURCENTAGE    Int NOT NULL ,
                               TEMPS_MOYEN    Int NOT NULL ,
                               ID_WEB_SERVICE Int NOT NULL
    ,CONSTRAINT SV_STATISTIQUE_PK PRIMARY KEY (ID)

    ,CONSTRAINT SV_STATISTIQUE_WEB_SERVICE_FK FOREIGN KEY (ID_WEB_SERVICE) REFERENCES WEB_SERVICE(ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: AUTORISER
#------------------------------------------------------------

CREATE TABLE AUTORISER(
                          Code_Application Varchar (3) NOT NULL ,
                          ID_RH            Varchar (7) NOT NULL
    ,CONSTRAINT AUTORISER_PK PRIMARY KEY (Code_Application,ID_RH)

    ,CONSTRAINT AUTORISER_APPLICATION_FK FOREIGN KEY (Code_Application) REFERENCES APPLICATION(Code_Application)
    ,CONSTRAINT AUTORISER_HABILITATION0_FK FOREIGN KEY (ID_RH) REFERENCES HABILITATION(ID_RH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: APPARTENIR
#------------------------------------------------------------

CREATE TABLE APPARTENIR(
                           Id_RH            Varchar (7) NOT NULL ,
                           Code_Application Varchar (3) NOT NULL
    ,CONSTRAINT APPARTENIR_PK PRIMARY KEY (Id_RH,Code_Application)

    ,CONSTRAINT APPARTENIR_CORRESPONDANT_FK FOREIGN KEY (Id_RH) REFERENCES CORRESPONDANT(Id_RH)
    ,CONSTRAINT APPARTENIR_APPLICATION0_FK FOREIGN KEY (Code_Application) REFERENCES APPLICATION(Code_Application)
)ENGINE=InnoDB;

INSERT INTO sv_project.application (code_application, libelle, type) VALUES ('l3s', 'bla bla bla', 'oreirjgfe');
INSERT INTO sv_project.application (code_application, libelle, type) VALUES ('l5t', 'tuc tic tac', 'blop');

INSERT INTO sv_project.correspondant (id_RH, nom, prenom, fonction, telephone, email) values ('PBGF123', 'Roussely', 'Robert', 'Développer', '0642749481', 'rousselyrobert@wanadoo.fr');
INSERT INTO sv_project.correspondant (id_RH, nom, prenom, fonction, telephone, email) values ('PPDL890', 'Delcroix', 'Jean-Luc', 'Directeur', '0000000000', 'jean-luc.delcroix@laposte.fr');

INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PBGF123', '123456789', 'UTILISATEUR');
INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PPDL890', 'james', 'ADMIN');
INSERT INTO sv_project.habilitation (id_rh, mot_de_passe, role) VALUES ('PPQR933', 'prstres', 'SUPERADMIN');

INSERT INTO sv_project.sv_erreur (date_debut, date_fin, libelle_erreur, statut_http, statut_retour, web_service) VALUES ('2020-01-10', '2020-01-11', 'erreur systeme', '403', 'FAILURE', 2);
INSERT INTO sv_project.sv_erreur (date_debut, date_fin, libelle_erreur, statut_http, statut_retour, web_service) VALUES ('2020-03-11', '2020-03-12', 'kmezjgpozjgz', '403', 'FAILURE', 1);

INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service) VALUES ('2020-02-28', 18, 3, 1);
INSERT INTO sv_project.sv_statistique (date, pourcentage, temps_moyen, web_service) VALUES ('2020-03-10', 25, 2, 2);

INSERT INTO sv_project.sv_suivi (code_application, date_debut, date_fin, statut_http, statut_retour, web_service) VALUES ('l5t', '2020-03-18', '2020-03-19', '203', 'ok', 1);


INSERT INTO sv_project.web_service (date_creation, libelle_web_service, web_service, code_application) VALUES  ('2020-01-21', 'bla bla bla', 'blo blo blo', 'l3s');
INSERT INTO sv_project.web_service  (date_creation, libelle_web_service, web_service, code_application) VALUES ('2019-05-10', 'loosing', 'look', 'l5t');
INSERT INTO sv_project.web_service (date_creation, libelle_web_service, web_service, code_application) VALUES  ('2020-01-21', 'bla bla bla', 'blo blo blo', 'l3s');
INSERT INTO sv_project.web_service  (date_creation, libelle_web_service, web_service, code_application) VALUES ('2019-05-10', 'loosing', 'look', 'l5t');
INSERT INTO sv_project.web_service (date_creation, libelle_web_service, web_service, code_application) VALUES  ('2020-01-21', 'bla bla bla', 'blo blo blo', 'l3s');
INSERT INTO sv_project.web_service  (date_creation, libelle_web_service, web_service, code_application) VALUES ('2019-05-10', 'loosing', 'look', 'l5t');
