package donnees;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.ICore;
import core.IDonnees;
import core.InterfaceIA;
import ia.IaWatson;

public class DonneesOracle implements IDonnees{
	
	private Connection c;
	private ICore core;
	
	static{
		
	}
	
	public DonneesOracle(ICore core){
		this.core = core;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL","GRP201US4","GRP201US4");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getPassword(int SERVICE_ID)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT mdp FROM service where idservice = ?");
		
		statement.setInt(1, SERVICE_ID);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		else {
			String password = rs.getString("mdp");
			return password;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUsername(int SERVICE_ID)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT login FROM service where idservice = " + SERVICE_ID);
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		else {
			String username = rs.getString("login");
			System.out.println(username);
			return username;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	
	

	@Override
	public String getPhraseFromClass(String topClass)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT text FROM Classe where idClasse = '" +topClass +"'");
		
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		else {
			String reponse = rs.getString("text");
			return reponse;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAIFromService(int SERVICE_ID)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT CID FROM ClassifierService where idService = '" +SERVICE_ID +"'");
		
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		else {
			String ai = rs.getString("CID");
			return ai;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getClasseService(int SERVICE_ID)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT classeService FROM Service where idService = '" + SERVICE_ID +"'");
		
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		else {
			String classe = rs.getString("ClasseService");
			return classe;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getServices() {
		List<Integer> listeServices = new ArrayList<Integer>();
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT idService FROM Service");
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				listeServices.add(rs.getInt("idService"));
			}
			return listeServices;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
