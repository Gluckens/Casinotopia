prompt 
prompt --------------------------------------------------
prompt Création des déclencheurs...
prompt --------------------------------------------------

prompt 
prompt Création du déclencheur utilisateur_bir_id_trg...
create trigger utilisateur_bir_id_trg
before insert on utilisateur 
for each row
when (new.id is null)
begin
  select utilisateur_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Création du déclencheur client_bir_id_trg...
create trigger client_bir_id_trg
before insert on client 
for each row
when (new.id is null)
begin
  select client_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Création du déclencheur avatar_bir_id_trg...
create trigger avatar_bir_id_trg
before insert on avatar
for each row
when (new.id is null)
begin
  select avatar_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Création du déclencheur fondation_bir_id_trg...
create trigger fondation_bir_id_trg
before insert on fondation
for each row
when (new.id is null)
begin
  select fondation_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Création du déclencheur donUnique_bir_id_trg...
create trigger donUnique_bir_id_trg
before insert on donUnique
for each row
when (new.id is null)
begin
  select donUnique_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Création du déclencheur prtgGains_bir_prcnt_100_trg...
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

prompt 
prompt Fin du script.