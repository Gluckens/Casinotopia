package ca.uqam.casinotopia.bd;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public abstract class TestBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		resetBD();
		
		ModeleClientServeur client = new ModeleClientServeur("username1", "mdp1", "prenom1", "nom1", java.sql.Date.valueOf("1988-03-01"), "courriel1", 1001, "/img/chip_5.png");
		CtrlBD.BD.ajouterClient(client);
		
		ModeleClientServeur client2 = new ModeleClientServeur("username2", "mdp2", "prenom2", "nom2", java.sql.Date.valueOf("1988-03-02"), "courriel2", 1002, "/img/chip_10.png");
		CtrlBD.BD.ajouterClient(client2);
		
		ModeleClientServeur client3 = new ModeleClientServeur("username3", "mdp3", "prenom3", "nom3", java.sql.Date.valueOf("1988-03-03"), "courriel3", 1003, "/img/chip_25.png");
		CtrlBD.BD.ajouterClient(client3);
		
		CtrlBD.BD.ajouterAmiClient(client, client3);
		CtrlBD.BD.ajouterAmiClient(client, client2);
		
		ModeleClientServeur client4 = CtrlBD.BD.authentifierClient("username1", "mdp1");
		
		/*ModeleClientServeur client2 = CtrlBD.BD.authentifierClient("username1", "mdp1");
		
		ModeleClientServeur client3 = CtrlBD.BD.getClientByIdUtilisateur(client.getIdUtilisateur());
		
		ModeleClientServeur client4 = CtrlBD.BD.getClientById(client.getId());*/
		
		
		System.out.println("FIN DES TESTS");
	}
	
	public static void resetBD() {
		CtrlBD.BD.execQuery("DROP TABLE donUnique CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE partageGains CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE fondation CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE amiClient CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE avatar CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE utilisateur CASCADE CONSTRAINTS PURGE");
		CtrlBD.BD.execQuery("DROP TABLE client CASCADE CONSTRAINTS PURGE");
								
		CtrlBD.BD.execQuery("DROP SEQUENCE utilisateur_id_seq");
		CtrlBD.BD.execQuery("DROP SEQUENCE client_id_seq");
		CtrlBD.BD.execQuery("DROP SEQUENCE avatar_id_seq");
		CtrlBD.BD.execQuery("DROP SEQUENCE fondation_id_seq");
		CtrlBD.BD.execQuery("DROP SEQUENCE donUnique_id_seq");
		CtrlBD.BD.execQuery("DROP SEQUENCE partageGains_id_seq");
								
								
		String query =	"create table utilisateur" +
				"(" +
				"  id     				number not null," +
				"  identifiant    		varchar2(50) not null," +
				"  motDePasse 			varchar2(50) not null," +
				"  typeCompte char(3) 	not null," +
				"  constraint utilisateur_pk primary key (id)," +
				"  constraint identifiant_uk1 unique (identifiant)," +
				"  constraint typeCompte_ck check (typeCompte in ('ADM', 'TEC', 'CLT'))" +
				")";
		CtrlBD.BD.execQuery(query);
				 
		query =	"create table avatar" +
				"(" +
				"  id               	number not null," +
				"  nomFichier			varchar2(50) not null," +
				"  texte				varchar2(300)," +
				"  constraint avatar_pk primary key (id)" +
				")";
		CtrlBD.BD.execQuery(query);
				
		query =	"create table client" +
				"(" +
				"  id               	number not null," +
				"  idUtilisateur		number not null," +
				"  prenom				varchar2(50) not null," +
				"  nom					varchar2(50) not null," +
				"  dateNaissance		date not null," +
				"  courriel				varchar2(200) not null," +
				"  solde				number(9,2)," +
				"  prcGlobal			number(3)," +
				"  idAvatar				number not null," +
				"  constraint client_pk primary key (id)," +
				"  constraint client_utilisateur_fk foreign key (idUtilisateur) references utilisateur (id) on delete cascade," +
				"  constraint client_avatar_fk foreign key (idAvatar) references avatar (id) on delete cascade," +
				"  constraint courriel_uk1 unique (courriel)," +
				"  constraint prcGlobal_ck check (prcGlobal <= 100 AND prcGlobal >= 0)" +
				")";
		CtrlBD.BD.execQuery(query);
				 
		query =	"create table amiClient" +
				"(" +
				"  idClient          	number not null," +
				"  idAmi				number not null," +
				"  constraint amiclient_pk primary key (idClient, idAmi)," +
				"  constraint amiclient_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint amiclient_ami_fk foreign key (idAmi) references client (id) on delete cascade" +
				")";
		CtrlBD.BD.execQuery(query);
				 
		query =	"create table fondation" +
				"(" +
				"  id               	number not null," +
				"  nom					varchar2(150) not null," +
				"  description			varchar2(500) not null," +
				"  constraint fondation_pk primary key (id)," +
				"  constraint nom_uk1 unique (nom)" +
				" )";
		CtrlBD.BD.execQuery(query);
				 
		query =	"create table donUnique" +
				"(" +
				"  id               	number not null," +
				"  idClient				number not null," +
				"  idFondation			number not null," +
				"  montant				number(6,2) not null," +
				"  dateDon 				date default trunc(sysdate) not null, " +
				"  constraint donUnique_pk primary key (id)," +
				"  constraint donUnique_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint donUnique_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade" +
				")";
		CtrlBD.BD.execQuery(query);
				 
		query =	"create table partageGains" +
				"(" +
				"  id               	number not null," +
				"  idClient				number not null," +
				"  idFondation			number not null," +
				"  pourcentage			number(3) not null," +
				"  constraint partageGains_pk primary key (id)," +
				"  constraint partageGains_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint partageGains_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade" +
				")";
		CtrlBD.BD.execQuery(query);
				 
				 
				
		CtrlBD.BD.execQuery("create sequence utilisateur_id_seq start with 1 increment by 1 nocache");
		CtrlBD.BD.execQuery("create sequence client_id_seq start with 1 increment by 1 nocache");
		CtrlBD.BD.execQuery("create sequence avatar_id_seq start with 1 increment by 1 nocache");
		CtrlBD.BD.execQuery("create sequence fondation_id_seq start with 1 increment by 1 nocache");
		CtrlBD.BD.execQuery("create sequence donUnique_id_seq start with 1 increment by 1 nocache");
		CtrlBD.BD.execQuery("create sequence partageGains_id_seq start with 1 increment by 1 nocache");
				
				
				
		/*query =	"create trigger utilisateur_bir_id_trg\n" +
				"  before insert on utilisateur\n" +
				"  for each row\n" +
				"  when (new.id is null)\n" +
				"  begin\n" +
				"    SELECT utilisateur_id_seq.nextval INTO :new.id from dual;\n" +
				"  end;\n";
		CtrlBD.BD.execQuery(query);*/
		
		query =	"create trigger utilisateur_bir_id_trg" +
				"  before insert on utilisateur" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT utilisateur_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
				
		query =	"create trigger client_bir_id_trg" +
				"  before insert on client" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT client_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
				
		query =	"create trigger avatar_bir_id_trg" +
				"  before insert on avatar" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT avatar_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
				
		query =	"create trigger fondation_bir_id_trg" +
				"  before insert on fondation" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT fondation_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
				
		query =	"create trigger donUnique_bir_id_trg" +
				"  before insert on donUnique" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT donUnique_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
				
		query =	"create trigger prtgGains_bir_prcnt_100_trg" +
				"  before insert on partageGains" +
				"  for each row" +
				"    when (new.idClient is not null)" +
				"      declare" +
				"        l_total_pourcentage number;" +
				"      begin" +
				"        SELECT sum(1) INTO l_total_pourcentage" +
				"          FROM partageGains" +
				"        WHERE idClient = :new.idClient;" +
				
				"        if ((l_total_pourcentage + :new.pourcentage) > 100) then" +
				"          raise_application_error(-20000, 'Un client ne peut pas partager plus que 100% de ses gains.');" +
				"        end if;" +
				"      end;";
		CtrlBD.BD.execQuery(query);
	}
}
