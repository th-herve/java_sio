package modele.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modele.dao.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Connexion {

	private static Connection connect = null;
	
//	private static final String SQL_SERVER = "DESKTOP-7O2DRQE\SQLEXPRESS01"; // Charles
//	private static final String SQL_SERVER = "DESKTOP-7O2DRQE\SQLEXPRESS04"; // Moatasm
//	private static final String SQL_SERVER = "DESKTOP-7O2DRQE\SQLEXPRESS03"; // Jeanne
	private static final String SQL_SERVER = "DESKTOP-7O2DRQE\\SQLEXPRESS"; // Thibault

	private static final String BASE_DE_DONNEES = "bd_Ludotheque";
	private static final String ID = "admin";
	private static final String MDP = "sio";
	
	private static final int COLONNE_TEXTE = 10;
	private static final int COLONNE_ENTIER = 6;
	private static final int COLONNE_DATE = 11;

	/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static Connection getInstance() {
		if (connect==null) {
			try { 

				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser(ID);
				ds.setPassword(MDP);
				ds.setServerName(SQL_SERVER);
				ds.setDatabaseName(BASE_DE_DONNEES);
				connect = ds.getConnection();
				System.out.println("connecté");
			}
			catch (SQLException e){
				System.out.println("Echec de la tentative de connexion : " + e.getMessage() + e.getStackTrace()) ;
			}
		}
		return connect;
	}
	
	private Connexion() {
		super();
	}

	public static ResultSet executeQuery(String requete){
		Statement st = null ;
		ResultSet rs = null;
		//System.out.println("requete = "+requete);
		try{
			st = Connexion.getInstance().createStatement() ;
			rs = st.executeQuery(requete) ;
		}catch(SQLException e){
			System.out.println("Echec de la tentative d'ex�cution de requete : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return rs;
	}

	/**
	 * Une m�thode statique qui permet de faire une mise � jour d'une table (INSERT / UPDATE / DELETE)
	 * @param requete
	 * @return
	 */
	public static boolean executeUpdate(String requete){
		boolean succes = true;
		//System.out.println(requete);
		Statement st = null ;
		try{
			st = Connexion.getInstance().createStatement() ;
			st.executeUpdate(requete) ;
		}catch(SQLException e){
			succes = false;
			System.out.println("Echec de la tentative d'ex�cution de Mise � Jour : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return succes;
	}

	/**
	 * Fermeture de la connexion au SGBD SQL ServerExpress
	 */
	public static void fermer()
	{
		try
		{
			getInstance().close();
			System.out.println("deconnexion ok");
		}
		catch (SQLException e)
		{
			connect=null;
			System.out.println("echec de la fermeture");
		}
	}

	/**
	 * Requ�te qui permet de voir le contenu d'une table
	 * Attention � ne pas perdre la premi�re ligne en testant la table vide
	 * @param table
	 */
	public static void afficheSelectEtoile(String table, String clauseWhere){
		try{
			String requete = "SELECT * FROM "+table;
			if (clauseWhere!=null) {
				requete += " WHERE "+clauseWhere;
			}
			ResultSet res = Connexion.executeQuery(requete) ;
			ResultSetMetaData rsmd = res.getMetaData();
			int taille = rsmd.getColumnCount();
			boolean hasNext =res.next(); 
			if (!hasNext) {System.out.println("table vide");}
			else {
				// Affichage du nom des colonnes
				System.out.print("|");
				for (int j = 1; j <= taille; j++) {
					String colonneNorme = extraireNomColonneNorme(rsmd, j);
					System.out.print(colonneNorme+"|");							
				} 
				System.out.println();

				// Affichage des donn�es
				while(hasNext){
					System.out.print("|");
					for (int j = 1; j <= taille; j++) {
						String valeurNormee = extraireValeurNormeeTypee(res, rsmd, j);
						System.out.print(valeurNormee+"|");							
					} 
					System.out.println();
					hasNext = res.next();
				}
			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
	}

	private static String extraireValeurNormeeTypee(ResultSet res, ResultSetMetaData rsmd, int j)
			throws SQLException {
		String valeurNormee = "";
		switch (rsmd.getColumnType(j)) {
		case Types.VARCHAR:
			valeurNormee = res.getString(j);
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_TEXTE, Alignement.Droite);
			break;
		case Types.DATE:
			valeurNormee = res.getDate(j).toString();
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_DATE, Alignement.Droite);
			break;
		case Types.TIMESTAMP:
			valeurNormee = res.getTimestamp(j).toString();
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_DATE, Alignement.Droite);
			break;
		case Types.INTEGER:
			valeurNormee = res.getInt(j)+"";
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_ENTIER, Alignement.Droite);
			break;
		default:
			break;
		}
		return valeurNormee;
	}

	private static String extraireNomColonneNorme(ResultSetMetaData rsmd, int j)
			throws SQLException {
		String nomColonneNorme = rsmd.getColumnName(j);
		switch (rsmd.getColumnType(j)) {
		case Types.VARCHAR:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_TEXTE, Alignement.Droite);
			break;
		case Types.DATE:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_DATE, Alignement.Droite);
			break;
		case Types.TIMESTAMP:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_DATE, Alignement.Droite);
			break;
		case Types.INTEGER:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_ENTIER, Alignement.Droite);
			break;
		default:
			break;
		}
		return nomColonneNorme;
	}

	
	/** Le seul alignement pris en compte est � droite.
	 * 
	 * @param valeurNormee la chaine de texte � normaliser
	 * @param colonneTexte  la largeur maximale de la colonne
	 * @param aligne  gauche / droite / centr�
	 * @return la chaine de caract�re normalis� pour affichage de tableau.
	 */
	private static String norme(String valeurNormee, int colonneTexte, Alignement aligne) {
		String rep = "";
		int tailleEffective =valeurNormee.length(); 
		if (tailleEffective>=colonneTexte) {
			rep = valeurNormee.substring(0, colonneTexte);
		}
		else {
			rep = valeurNormee;
			for (int i = tailleEffective; i < colonneTexte; i++) {
				rep += " ";
			}
		}
		return rep;
	}

	/**
	 * Requ�te qui permet de r�cup�rer le plus grand id de la table, a priori celui qui vient d'�tre affect�
	 * � une ligne cr��e via identity.
	 * @param cle
	 * @param table
	 * @return
	 */
	public static int getMaxId(String cle, String table) {
		String requete = "SELECT MAX("+cle+")as max FROM "+table;
		ResultSet rs = Connexion.executeQuery(requete);
		int id= -1;
		try {
			rs.next();
			id = rs.getInt("max");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static List<Integer> getLesIds(String attribut, String table, String clauseWhere) {
		String requete = "SELECT DISTINCT "+attribut+" FROM "+table;
		if (clauseWhere!=null) {
			requete += " WHERE "+clauseWhere;
		}		
		ResultSet rs = Connexion.executeQuery(requete);
		List<Integer> liste = new ArrayList<Integer>();
		try {
			while (rs.next()) {
			int id = rs.getInt(attribut);
			liste.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
		
	}
	public static void main(String[] args) {
		
		/** décommenter pour afficher la table adherent 
		 */
//		Connexion.getInstance();
//		AdherentDAO adherentDAO = AdherentDAO.getInstance();
//		adherentDAO.afficheSelectEtoileAdherent();
		
        /**
         pour afficher les tables Personne et Personnel 
         */
		PersonneDAO personneDAO = PersonneDAO.getIntstance();
		personneDAO.afficheSelectEtoilePersonne();
		PersonnelDAO personnelDAO = PersonnelDAO.getInstance();
		personnelDAO.afficheSelectEtoilePersonnel();

	}

//	public static void main(String[] args) {
//		Connexion.getInstance();
//		
//		 try {
//		        // Get the database metadata
//		        DatabaseMetaData metaData = connect.getMetaData();
//
//		        // Get the list of table names
//		        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
//
//		        // Display the table names
//		        while (tables.next()) {
//		            String tableName = tables.getString("TABLE_NAME");
//		            System.out.println("Table: " + tableName);
//		        }
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    } finally {
//		        // Don't forget to close the connection when you're done
//		    	String tableName = "admin";
////				
////				    // Optional WHERE clause, set to null if not needed
////				   // String whereClause = "your_condition_column = 'your_condition_value'";
////				    
////				    // Display data from the table
//				    afficheSelectEtoile(tableName,null);
//		        fermer();
//		    }
		
		  
//		    
//		    // Don't forget to close the connection when you're done
//		    fermer();
//	}

	
}

