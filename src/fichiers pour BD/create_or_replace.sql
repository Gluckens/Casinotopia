DROP TABLE DONUNIQUE CASCADE CONSTRAINTS PURGE;
DROP TABLE PARTAGEGAINS CASCADE CONSTRAINTS PURGE;
DROP TABLE FONDATION CASCADE CONSTRAINTS PURGE;
DROP TABLE AVATAR CASCADE CONSTRAINTS PURGE;
DROP TABLE AMICLIENT CASCADE CONSTRAINTS PURGE;
DROP TABLE CLIENT CASCADE CONSTRAINTS PURGE;
DROP TABLE UTILISATEUR CASCADE CONSTRAINTS PURGE;

DROP SEQUENCE utilisateur_id_seq;
DROP SEQUENCE client_id_seq;
DROP SEQUENCE avatar_id_seq;
DROP SEQUENCE fondation_id_seq;
DROP SEQUENCE donUnique_id_seq;
DROP SEQUENCE partageGains_id_seq;



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
 
 create table avatar
(
  id               	number not null,
  --idClient			number not null,
  nomFichier		varchar2(50) not null,
  texte				varchar2(300),
  constraint avatar_pk primary key (id)
  --constraint avatar_client_fk foreign key (idClient) references client (id) on delete cascade
 );

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
  idAvatar			number not null,
  constraint client_pk primary key (id),
  constraint client_utilisateur_fk foreign key (idUtilisateur) references utilisateur (id) on delete cascade,
  constraint client_avatar_fk foreign key (idAvatar) references avatar (id) on delete cascade,
  constraint courriel_uk1 unique (courriel),
  constraint prcGlobal_ck check (prcGlobal <= 100 AND prcGlobal >= 0)
 );
 
 create table amiClient
(
  idClient          number not null,
  idAmi				number not null,
  constraint amiclient_pk primary key (idClient, idAmi),
  constraint amiclient_client_fk foreign key (idClient) references client (id) on delete cascade,
  constraint amiclient_ami_fk foreign key (idAmi) references client (id) on delete cascade
 );
 
 create table fondation
(
  id               	number not null,
  nom				varchar2(150) not null,
  description		varchar2(500) not null,
  constraint fondation_pk primary key (id),
  constraint nom_uk1 unique (nom)
 );
 
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
 
 

create sequence utilisateur_id_seq start with 1 increment by 1 nocache;
create sequence client_id_seq start with 1 increment by 1 nocache;
create sequence avatar_id_seq start with 1 increment by 1 nocache;
create sequence fondation_id_seq start with 1 increment by 1 nocache;
create sequence donUnique_id_seq start with 1 increment by 1 nocache;
create sequence partageGains_id_seq start with 1 increment by 1 nocache;



create trigger utilisateur_bir_id_trg
before insert on utilisateur 
for each row
when (new.id is null)
begin
  select utilisateur_id_seq.nextval into :new.id from dual;
end;
/

create trigger client_bir_id_trg
before insert on client 
for each row
when (new.id is null)
begin
  select client_id_seq.nextval into :new.id from dual;
end;
/

create trigger avatar_bir_id_trg
before insert on avatar
for each row
when (new.id is null)
begin
  select avatar_id_seq.nextval into :new.id from dual;
end;
/

create trigger fondation_bir_id_trg
before insert on fondation
for each row
when (new.id is null)
begin
  select fondation_id_seq.nextval into :new.id from dual;
end;
/

create trigger donUnique_bir_id_trg
before insert on donUnique
for each row
when (new.id is null)
begin
  select donUnique_id_seq.nextval into :new.id from dual;
end;
/

create trigger prtgGains_bir_prcnt_100_trg
before insert on partageGains
for each row
when (new.idClient is not null)
declare
  l_total_pourcentage number;
begin
  select sum(1)
  into   l_total_pourcentage
  from   partageGains
  where  idClient = :new.idClient;

  if ((l_total_pourcentage + :new.pourcentage) > 100) then
    raise_application_error(-20000, 'Un client ne peut pas partager plus que 100% de ses gains.');
  end if;
end;
/