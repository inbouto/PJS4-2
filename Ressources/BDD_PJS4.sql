REM ********************************************************
REM BDD PJS4
REM Auteur : PATRY Mathieu DUGAUQUIER Thibault
REM ********************************************************

SET FEEDBACK OFF

PROMPT ** CREATION DES TABLES

REM ********************************************************
REM BDD PJS4
REM Auteur : PATRY Mathieu DUGAUQUIER Thibault
REM ********************************************************

SET FEEDBACK OFF

PROMPT ** CREATION DES TABLES

drop table Classe cascade constraint purge;
drop table Classifier cascade constraint purge;
drop table ClassifierService cascade constraint purge;
drop table Service cascade constraint purge;

CREATE TABLE Classe (
	idClasse VARCHAR2(50) PRIMARY KEY,
	text VARCHAR2(1024),
	CID VARCHAR2(50)
)
/

CREATE TABLE Classifier(
	CID VARCHAR2(50) PRIMARY KEY,
	nomClassifier VARCHAR2(50)
)
/

CREATE TABLE ClassifierService(
	CID VARCHAR2(50),
	idService INTEGER,
	PRIMARY KEY (CID, idService)
)
/

CREATE TABLE Service (
	idService INTEGER PRIMARY KEY,
	typeService VARCHAR2(50),
	login VARCHAR2(50),
	mdp VARCHAR2(50),
	idUtilisateur INTEGER
)
/

CREATE TABLE Utilisateur (
	idUtilisateur INTEGER PRIMARY KEY,
	nomUtilisateur VARCHAR2(50),
	Adresse VARCHAR2(50),
)
/

PROMPT ** CREATION DES ALTER_TABLE

ALTER TABLE Classe
ADD CONSTRAINT REF_ClassifierClasse FOREIGN KEY(CID) REFERENCES Classifier(CID)
/

ALTER TABLE ClassifierService
ADD CONSTRAINT REF_ClassifierService FOREIGN KEY(CID) REFERENCES Classifier(CID)
ADD CONSTRAINT REF_Service FOREIGN KEY(idService) REFERENCES Service(idService)
/

ALTER TABLE Service
ADD CONSTRAINT REF_Service_user FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(idUtilisateur)

/

INSERT INTO Classifier VALUES('2fc31ex330-nlc-1535','MyTestWeather');
INSERT INTO Classe VALUES('temperature','Ta mere est a 53 degrees fdp','2fc31ex330-nlc-1535');
INSERT INTO Classe VALUES('condition','Ta mere pleut batard','2fc31ex330-nlc-1535');
INSERT INTO Service VALUES(1,'mail','techbotdemo@gmail.com','L8KwuzRjh5WB/j\g');
INSERT INTO Service VALUES(2,'twitter','techbotdemo','L8KwuzRjh5WB/j\g');
INSERT INTO ClassifierService VALUES('2fc31ex330-nlc-1535',1);
INSERT INTO ClassifierService VALUES('2fc31ex330-nlc-1535',2);
INSERT INTO Utilisateur VALUES(1, 'Julien le Mexicain', 'Tres de Paquitos');