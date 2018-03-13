package donnees;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DonneesSGBD extends DataAbstract{
	private Connection c;
	private ArrayList<String> classes;
	private ArrayList<PhraseClasse> contenu;
	
	public DonneesSGBD() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		this.c = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL","GRP201US7","GRP201US7");
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
	
	public void sauvegarder() throws SQLException{ //TODO finir deuxieme boucle
		PreparedStatement statement = null;
		int id = 0;
		String nom="";
		String req = "INSERT INTO CLASSES (id, nom) VALUES (" + id +"," + nom + ") WHERE NOT EXISTS (SELECT (id, nom) FROM CLASSES WHERE id =" + id + "AND nom = " + nom;
		c.prepareStatement(req);
		Statement requete = c.createStatement();
		for (int i = 0; i<this.classes.size(); i++){			
			id = i;
			nom = this.classes.get(i);
			req = "INSERT INTO CLASSES (id, nom) VALUES (" + id +"," + nom + ") WHERE NOT EXISTS (SELECT (id, nom) FROM CLASSES WHERE id =" + id + "AND nom = " + nom;
			ResultSet rs = statement.executeQuery();
			rs.close();
		}
		id = 0;
		String phrase ="";
		int idClasse = 0; 
		String req2 = "INSERT INTO PHRASESCLASSES (id, phrase, IdClasse) VALUES (" + id + "," + phrase + "," + idClasse + ") WHERE NOT EXISTS (SELECT (id, phrase, IdClasse) FROM PHRASESCLASSES WHERE id =" + id + "AND phrase =" + phrase + " AND idClasse = " + idClasse;
		requete.close();
		
		
		
	}
	
	@Override
	public void sauvegarder(File f) throws IOException {
		
	}

}
