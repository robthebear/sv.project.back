#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Habilitations
#------------------------------------------------------------

CREATE TABLE Habilitations(
        ID    Int  Auto_increment  NOT NULL ,
        ID_RH Varchar (7) NOT NULL ,
        Role  Varchar (50) NOT NULL
	,CONSTRAINT Habilitations_PK PRIMARY KEY (ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_Statistiques
#------------------------------------------------------------

CREATE TABLE SV_Statistiques(
        id          Int  Auto_increment  NOT NULL ,
        date        Date NOT NULL ,
        POURCENTAGE Int NOT NULL
	,CONSTRAINT SV_Statistiques_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Applications
#------------------------------------------------------------

CREATE TABLE Applications(
        Code_Application Varchar (3) NOT NULL ,
        LIBELLE          Varchar (50) NOT NULL ,
        TYPE             Varchar (50) NOT NULL
	,CONSTRAINT Applications_PK PRIMARY KEY (Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_Erreurs
#------------------------------------------------------------

CREATE TABLE SV_Erreurs(
        Id                            Int  Auto_increment  NOT NULL ,
        CODE_APPLICATION              Varchar (50) NOT NULL ,
        WEBSERVICE                    Varchar (50) NOT NULL ,
        LIBELLE_WS                    Varchar (50) NOT NULL ,
        DATE_DEBUT                    Date NOT NULL ,
        STATUS_RETOUR                 Varchar (50) NOT NULL ,
        LIBELLE_ERREUR                Varchar (50) NOT NULL ,
        Code_Application_Applications Varchar (3) NOT NULL
	,CONSTRAINT SV_Erreurs_PK PRIMARY KEY (Id)

	,CONSTRAINT SV_Erreurs_Applications_FK FOREIGN KEY (Code_Application_Applications) REFERENCES Applications(Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SV_SUIVI
#------------------------------------------------------------

CREATE TABLE SV_SUIVI(
        Id                            Int  Auto_increment  NOT NULL ,
        CODE_APPLICATION              Varchar (50) NOT NULL ,
        WEBSERVICE                    Varchar (50) NOT NULL ,
        LIBELLE_WS                    Varchar (50) NOT NULL ,
        DATE_DEBUT                    Date NOT NULL ,
        STATUS_RETOUR                 Varchar (50) NOT NULL ,
        Code_Application_Applications Varchar (3) NOT NULL
	,CONSTRAINT SV_SUIVI_PK PRIMARY KEY (Id)

	,CONSTRAINT SV_SUIVI_Applications_FK FOREIGN KEY (Code_Application_Applications) REFERENCES Applications(Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Correspondant
#------------------------------------------------------------

CREATE TABLE Correspondant(
        Id_RH     Varchar (7) NOT NULL ,
        NOM       Varchar (50) NOT NULL ,
        PRENOM    Varchar (50) NOT NULL ,
        FONCTION  Varchar (50) NOT NULL ,
        EMAIL     Varchar (50) NOT NULL ,
        TELEPHONE Int NOT NULL ,
        ROLE      Varchar (50) NOT NULL
	,CONSTRAINT Correspondant_PK PRIMARY KEY (Id_RH)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: WebService
#------------------------------------------------------------

CREATE TABLE WebService(
        id                 Int  Auto_increment  NOT NULL ,
        Webservice         Varchar (50) NOT NULL ,
        Libelle_Webservice Varchar (50) NOT NULL ,
        Date_Creation      Date NOT NULL ,
        Code_Application   Varchar (3) NOT NULL
	,CONSTRAINT WebService_PK PRIMARY KEY (id)

	,CONSTRAINT WebService_Applications_FK FOREIGN KEY (Code_Application) REFERENCES Applications(Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Autoriser
#------------------------------------------------------------

CREATE TABLE Autoriser(
        Code_Application Varchar (3) NOT NULL ,
        ID               Int NOT NULL
	,CONSTRAINT Autoriser_PK PRIMARY KEY (Code_Application,ID)

	,CONSTRAINT Autoriser_Applications_FK FOREIGN KEY (Code_Application) REFERENCES Applications(Code_Application)
	,CONSTRAINT Autoriser_Habilitations0_FK FOREIGN KEY (ID) REFERENCES Habilitations(ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Appartenir
#------------------------------------------------------------

CREATE TABLE Appartenir(
        Id_RH            Varchar (7) NOT NULL ,
        Code_Application Varchar (3) NOT NULL
	,CONSTRAINT Appartenir_PK PRIMARY KEY (Id_RH,Code_Application)

	,CONSTRAINT Appartenir_Correspondant_FK FOREIGN KEY (Id_RH) REFERENCES Correspondant(Id_RH)
	,CONSTRAINT Appartenir_Applications0_FK FOREIGN KEY (Code_Application) REFERENCES Applications(Code_Application)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CONVERTIR
#------------------------------------------------------------

CREATE TABLE CONVERTIR(
        Id                 Int NOT NULL ,
        Id_SV_SUIVI        Int NOT NULL ,
        id_SV_Statistiques Int NOT NULL
	,CONSTRAINT CONVERTIR_PK PRIMARY KEY (Id,Id_SV_SUIVI,id_SV_Statistiques)

	,CONSTRAINT CONVERTIR_SV_Erreurs_FK FOREIGN KEY (Id) REFERENCES SV_Erreurs(Id)
	,CONSTRAINT CONVERTIR_SV_SUIVI0_FK FOREIGN KEY (Id_SV_SUIVI) REFERENCES SV_SUIVI(Id)
	,CONSTRAINT CONVERTIR_SV_Statistiques1_FK FOREIGN KEY (id_SV_Statistiques) REFERENCES SV_Statistiques(id)
)ENGINE=InnoDB;

