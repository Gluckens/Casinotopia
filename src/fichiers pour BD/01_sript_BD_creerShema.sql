prompt 
prompt --------------------------------------------------
prompt Création des tables...
prompt --------------------------------------------------

prompt 
prompt Création de la table utilisateur...
create table utilisateur
(
  id     				number not null,
  identifiant    		varchar2(50) not null,
  motPasse 				varchar2(50) not null,
  typeCompte char(3) 	not null,
  constraint utilisateur_pk primary key (id),
  constraint identifiant_uk1 unique (identifiant),
  constraint typeCompte_ck check (typeCompte in ('ADM', 'TEC', 'CLT'))
);

prompt 
prompt Création de la table client...
create table client
(
  id               	number not null,
  idUtilisateur		number not null,
  prenom			varchar2(50) not null,
  nom				varchar2(50) not null,
  dateNaissance		date not null,
  courriel			varchar2(200) not null,
  solde				number(9,2),
  prcGlobal			number(3),
  constraint client_pk primary key (id),
  constraint client_utilisateur_fk foreign key (idUtilisateur) references utilisateur (id) on delete cascade,
  constraint courriel_uk1 unique (courriel),
  constraint prcGlobal_ck check (prcGlobal <= 100 AND prcGlobal >= 0)
 );

prompt 
prompt Création de la table amiClient...
create table amiClient
(
  idClient          number not null,
  idAmi				number not null,
  constraint amiclient_pk primary key (idClient, idAmi),
  constraint amiclient_client_fk foreign key (idClient) references client (id) on delete cascade,
  constraint amiclient_ami_fk foreign key (idAmi) references client (id) on delete cascade
 );

prompt 
prompt Création de la table avatar...
create table avatar
(
  id               	number not null,
  idClient			number not null,
  nomFichier		varchar2(50) not null,
  texte				varchar2(300),
  constraint avatar_pk primary key (id),
  constraint avatar_client_fk foreign key (idClient) references client (id) on delete cascade
 );   
  
prompt 
prompt Création de la table fondation...
create table fondation
(
  id               	number not null,
  nom				varchar2(150) not null,
  description		varchar2(500) not null,
  constraint fondation_pk primary key (id),
  constraint nom_uk1 unique (nom)
 );
   
prompt 
prompt Création de la table donUnique...
create table donUnique
(
  id               	number not null,
  idClient			number not null,
  idFondation		number not null,
  montant			number(6,2) not null,
  dateDon 			date default trunc(sysdate) not null, 
  constraint donUnique_pk primary key (id),
  constraint donUnique_client_fk foreign key (idClient) references client (id) on delete cascade,
  constraint donUnique_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade
 );
    
prompt 
prompt Création de la table partageGains...
create table partageGains
(
  id               	number not null,
  idClient			number not null,
  idFondation		number not null,
  pourcentage		number(3) not null,
  --constraint partageGains_pk primary key (idClient, idFondation),
  constraint partageGains_pk primary key (id),
  constraint partageGains_client_fk foreign key (idClient) references client (id) on delete cascade,
  constraint partageGains_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade
 );

prompt 
prompt Création de la séquence utilisateur_id_seq...
create sequence utilisateur_id_seq start with 1 increment by 1 nocache;
 
prompt 
prompt Création de la séquence client_id_seq...
create sequence client_id_seq start with 1 increment by 1 nocache; 

prompt 
prompt Création de la séquence avatar_id_seq...
create sequence avatar_id_seq start with 1 increment by 1 nocache; 

prompt 
prompt Création de la séquence fondation_id_seq...
create sequence fondation_id_seq start with 1 increment by 1 nocache; 

prompt 
prompt Création de la séquence donUnique_id_seq...
create sequence donUnique_id_seq start with 1 increment by 1 nocache;

prompt 
prompt Création de la séquence partageGains_id_seq...
create sequence partageGains_id_seq start with 1 increment by 1 nocache;
 
prompt 
prompt Fin du script. 
  
  
  