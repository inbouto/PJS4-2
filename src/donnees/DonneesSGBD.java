package donnees;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DonneesSGBD extends DataAbstract{
	private static Connection c;
	private ArrayList<String> classes;
	private ArrayList<PhraseClasse> contenu;
	
static {
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			c = DriverManager.getConnection ("jdbc:oracle:thin:@vs-oracle2:1521:ORCL","GRP201US7","GRP201US7");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DonneesSGBD() throws ClassNotFoundException, SQLException {
		
		this.classes = new ArrayList<String>();
		this.contenu = new ArrayList<PhraseClasse>();
	}
	@Override
	public Object getContenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public void getClasses() throws SQLException{
		PreparedStatement statement = null;
		String req = "SELECT id, nom FROM CLASSES";		
		c.prepareStatement(req);
		Statement requete = c.createStatement();
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String classe = rs.getString("nom");
			this.classes.set(id,classe);
		}
		rs.close();
		statement.close();		
	}
	
	public void getContenus() throws SQLException{
		PreparedStatement statement = null;
		String req = "SELECT * FROM PHRASESCLASSES";
		c.prepareStatement(req);
		Statement requete = c.createStatement();
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String phrase = rs.getString("phrase");
			int idClasse = rs.getInt("idClasse");
			this.contenu.set(id,(new PhraseClasse(phrase, this.classes.get(idClasse))));
		}
		rs.close();
		statement.close();
	}
	
	public void ajouter(String classe, PhraseClasse p){
		
	}
	
	public void ajouterClasse(String classe){
		this.classes.add(classe);
	}
		
	public void sauvegarder() throws SQLException{ //TODO finir deuxieme boucle
		PreparedStatement statement = null;
		int id = 0;
		String nom="";
		//String req = "INSERT INTO CLASSES (id, nom) VALUES (?,?) WHERE NOT EXISTS (SELECT (id, nom) FROM CLASSES WHERE id = ? AND nom = ?)";
		//insert into CLASSE (id,nom) select 1,'salut' from dual where not exists(select id from classe where id = 1);
		String req = "insert into CLASSE (id,nom) select ?, ? from dual where not exists(select id from classe where id = ?)";
		//statement = c.prepareStatement(req);
		for (int i = 0; i<this.classes.size(); i++){	
			statement = c.prepareStatement(req);
			statement.setInt(1, i);
			statement.setString(2, this.classes.get(i));
			statement.setInt(3, i);
			//statement.setString(4, this.classes.get(i));
			//req = "INSERT INTO CLASSES (id, nom) VALUES (?,?) WHERE NOT EXISTS (SELECT (id, nom) FROM CLASSES WHERE id = ? AND nom = ?)";
			statement.executeUpdate();
			System.out.println("yo");
			statement.close();	
		}
		id = 0;
		String phrase ="";
		int idClasse = 0; 
		//String req2 = "INSERT INTO PHRASESCLASSES (id, phrase, IdClasse) VALUES (?, ?, ?) WHERE NOT EXISTS (SELECT (id, phrase, IdClasse) FROM PHRASESCLASSES WHERE id =" + id + "AND phrase =" + phrase + " AND idClasse = " + idClasse;
		String req2 = "insert into PHRASESCLASSES (id,phrase,IdClasse) select ?, ?, ? from dual where not exists(select id from PHRASESCLASSES where id = ?)";
		statement = c.prepareStatement(req2);
		for (int i = 0; i<this.contenu.size(); i++){
			statement = c.prepareStatement(req2);
			statement.setInt(1, i);
			statement.setString(2, this.contenu.get(i).getPhrase());
			//statement.setString(3, this.contenu.get(i).get());
			
		}
		statement.close();		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DonneesSGBD d = new DonneesSGBD();
		d.ajouterClasse("Salutations");
		d.ajouterClasse("Nourriture");
		PhraseClasse p = new PhraseClasse("Bonjour", "Salutations");
		d.sauvegarder();
		
	}
	
	@Override
	public void sauvegarder(File f) throws IOException {
		
	}

}
