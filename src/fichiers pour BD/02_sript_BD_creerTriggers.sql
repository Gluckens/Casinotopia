prompt 
prompt --------------------------------------------------
prompt Cr�ation des d�clencheurs...
prompt --------------------------------------------------

prompt 
prompt Cr�ation du d�clencheur utilisateur_bir_id_trg...
create trigger utilisateur_bir_id_trg
before insert on utilisateur 
for each row
when (new.id is null)
begin
  select utilisateur_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Cr�ation du d�clencheur client_bir_id_trg...
create trigger client_bir_id_trg
before insert on client 
for each row
when (new.id is null)
begin
  select client_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Cr�ation du d�clencheur avatar_bir_id_trg...
create trigger avatar_bir_id_trg
before insert on avatar
for each row
when (new.id is null)
begin
  select avatar_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Cr�ation du d�clencheur fondation_bir_id_trg...
create trigger fondation_bir_id_trg
before insert on fondation
for each row
when (new.id is null)
begin
  select fondation_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Cr�ation du d�clencheur donUnique_bir_id_trg...
create trigger donUnique_bir_id_trg
before insert on donUnique
for each row
when (new.id is null)
begin
  select donUnique_id_seq.nextval into :new.id from dual;
end;
/

prompt 
prompt Cr�ation du d�clencheur prtgGain_bir_prcnt_100_trg...
create trigger prtgGain_bir_prcnt_100_trg
before insert on partageGain
for each row
when (new.idClient is not null)
declare
  l_total_pourcentage number;
begin
  select sum(1)
  into   l_total_pourcentage
  from   partageGain
  where  idClient = :new.idClient;

  if ((l_total_pourcentage + :new.pourcentage) > 100) then
    raise_application_error(-20000, 'Un client ne peut pas parteger des gains plus que 100%.');
  end if;
end;
/

prompt 
prompt Fin du script.