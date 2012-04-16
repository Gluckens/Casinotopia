prompt --------------------------------------------------
prompt Création des données de test...
prompt --------------------------------------------------

prompt 
prompt Création des données demo pour la table utilisateur...
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('casinotopia', 'casinotopia1', 'CLT');
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('OlivierADM', 'OlivierADM1', 'ADM');
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('DannyTEC', 'DannyTEC1', 'TEC');
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('AlexeiCLT', 'AlexeiCLT1', 'CLT');
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('OlivierCLT', 'OlivierCLT1', 'CLT');
insert into utilisateur (identifiant, motPasse, typeCompte)
values ('DannyCLT', 'DannyCLT1', 'CLT');
commit;

prompt 
prompt Création des données demo pour la table client...
insert into client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal)
values (1, 'Casino', 'Casinotopia', '04/14/2012', 'Casinotopia@Casinotopia.com', 1000000, 10);
insert into client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal)
values (4, 'Alexei', 'Lavrov', '01/01/1980', 'alexei.lavrov@gmail.com', 1000, 0);
insert into client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal)
values (5, 'Olivier', 'Gaudet', '02/02/1980', 'Olivier.Gaudet@gmail.com', 0, 10);
insert into client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal)
values (6, 'Danny', 'Despres-Bourgon', '03/03/1980', 'Danny.Despres-Bourgont@gmail.com', 3899.29, 60);
commit;

prompt 
prompt Création des données demo pour la table amiClient...
insert into amiClient (idClient, idAmi)
values (3,4);
insert into amiClient (idClient, idAmi)
values (4,3);
commit;

prompt 
prompt Création des données demo pour la table avatar...
insert into avatar (idClient, nomFichier, texte)
values (2, 'alexei.jpeg', 'salut je vais gagner plein argent');
insert into avatar (idClient, nomFichier, texte)
values (3, 'olivier.jpeg', 'super!!!');
insert into avatar (idClient, nomFichier, texte)
values (4, 'danny.jpeg', NULL);
commit;

prompt 
prompt Création des données demo pour la table fondation...
insert into fondation (nom, description)
values ('étudiants en greve','Plus argent, moins etudes');
insert into fondation (nom, description)
values ('wikipedia','Savoir accessible pour tous');
insert into fondation (nom, description)
values ('routesMontreal','fini les nids de poules');
commit;

prompt 
prompt Création des données demo pour la table donUnique...
insert into donUnique (idClient, idFondation, montant)
values (3,1,100);
insert into donUnique (idClient, idFondation, montant)
values (3,2,300);
insert into donUnique (idClient, idFondation, montant)
values (3,3,30);
insert into donUnique (idClient, idFondation, montant)
values (4,2,1000);
commit;

prompt 
prompt Création des données demo pour la table partageGain...
insert into partageGain (idClient, idFondation, pourcentage)
values (2,1,80);
insert into partageGain (idClient, idFondation, pourcentage)
values (2,2,10);
insert into partageGain (idClient, idFondation, pourcentage)
values (2,3,10);
insert into partageGain (idClient, idFondation, pourcentage)
values (3,1,100);
insert into partageGain (idClient, idFondation, pourcentage)
values (4,1,50);
insert into partageGain (idClient, idFondation, pourcentage)
values (4,3,50);
commit;

prompt 
prompt Fin du script.