prompt --------------------------------------------------
prompt Désinstallation du schéma...
prompt --------------------------------------------------

prompt 
prompt Désinstallation des déclencheurs...
--select 'drop trigger '||t.trigger_name||';' from user_triggers t order by t.table_name, t.trigger_name;
drop trigger utilisateur_bir_id_trg;
drop trigger client_bir_id_trg;
drop trigger avatar_bir_id_trg;
drop trigger fondation_bir_id_trg;
drop trigger donUnique_bir_id_trg;
drop trigger prtgGain_bir_prcnt_100_trg;

prompt 
prompt Désinstallation des contraintes...
--select 'alter table '||t.table_name||' drop constraint '||t.constraint_name||';' from user_constraints t where t.generated = 'USER NAME' and t.constraint_type = 'C' order by t.constraint_type, t.table_name, t.constraint_name;
alter table utilisateur drop constraint typeCompte_ck;
alter table client drop constraint prcGlobal_ck;

prompt 
prompt Désinstallation des clés étrangères...
--select 'alter table '||t.table_name||' drop constraint '||t.constraint_name||';' from user_constraints t where t.generated = 'USER NAME' and t.constraint_type = 'R' order by t.constraint_type, t.table_name, t.constraint_name;
alter table client drop constraint client_utilisateur_fk;
alter table amiClient drop constraint amiclient_client_fk;
alter table amiClient drop constraint amiclient_ami_fk;
alter table avatar drop constraint avatar_client_fk;
alter table donUnique drop constraint donUnique_client_fk;
alter table donUnique drop constraint donUnique_fondation_fk;
alter table partageGain drop constraint partageGain_client_fk;
alter table partageGain drop constraint partageGain_fondation_fk;

prompt 
prompt Désinstallation des clés uniques...
--select 'alter table '||t.table_name||' drop constraint '||t.constraint_name||';' from user_constraints t where t.generated = 'USER NAME' and t.constraint_type = 'U' order by t.constraint_type, t.table_name, t.constraint_name;
alter table utilisateur drop constraint identifiant_uk1;
alter table client drop constraint courriel_uk1;
alter table fondation drop constraint nom_uk1;

prompt 
prompt Désinstallation des clés prémaires...
--select 'alter table '||t.table_name||' drop constraint '||t.constraint_name||';' from user_constraints t where t.generated = 'USER NAME' and t.constraint_type = 'P' order by t.constraint_type, t.table_name, t.constraint_name;
alter table utilisateur drop constraint utilisateur_pk;
alter table client drop constraint client_pk;
alter table amiClient drop constraint amiclient_pk;
alter table avatar drop constraint avatar_pk;
alter table compteCasino drop constraint compteCasino_pk;
alter table fondation drop constraint fondation_pk;
alter table donUnique drop constraint donUnique_pk;
alter table partageGain drop constraint partageGain_pk;

prompt 
prompt Désinstallation des séquences...
--select 'drop sequence '||t.sequence_name||';' from user_sequences t order by t.sequence_name;
drop sequence utilisateur_id_seq;
drop sequence client_id_seq;
drop sequence avatar_id_seq;
drop sequence fondation_id_seq;
drop sequence donUnique_id_seq;

prompt 
prompt Désinstallation des index...

prompt 
prompt Désinstallation des tables...
--select 'drop table '||t.table_name||';' from user_tables t order by t.table_name;
drop table utilisateur;
drop table client;
drop table amiClient;
drop table avatar;
drop table fondation;
drop table donUnique;
drop table partageGain;

prompt 
prompt Fin du script.
