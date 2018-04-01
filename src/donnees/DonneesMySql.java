package donnees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import core.ICore;
import core.IDonnees;

public class DonneesMySql implements IDonnees{
	
	private Connection c;
	@SuppressWarnings("unused")
	private ICore core;
	
	static{
		
	}
	
	public DonneesMySql(ICore core){
		this.core = core;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.c = DriverManager.getConnection("jdbc:mysql://localhost/techbot?user=techbot_run&password=evVkGpsK6yQK9z4X");
			
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
		System.out.println(SERVICE_ID);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getPassword");
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
			System.err.println("Erreur requête : getUsername");
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
			System.err.println("Erreur requête : getPhraseFromClass pour " + topClass);
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
			System.err.println("Erreur requête : getAIFromService");
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
			System.err.println("Erreur requête : getClasseService pour service_id = " + SERVICE_ID);
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

	@Override
	public String getAIName(String AIID) {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT nomClassifier FROM classifier where CID = '" + AIID + "'");
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getAIName");
		}
		else {
			String name = rs.getString("nomClassifier");
			System.out.println(name);
			return name;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public String getServiceName(int service_id) {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT name FROM service where idService = " + service_id);
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getServiceName");
		}
		else {
			String name = rs.getString("name");
			System.out.println(name);
			return name;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public String getAIType(String cid) {
		return "Natural language classifier (NLC)";
	}

	@Override
	public List<Integer> getRunningServiceNameFromAI(String cid) {

		List<Integer> listNames = new Vector<Integer>();
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("select idservice from classifierservice where CId='"+cid+"'");
		
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			listNames.add(rs.getInt("idService"));
		}
		return listNames;
		} catch (SQLException e1) {
			System.err.println("Erreur requête : getRunningServiceNameFromAI : "+cid);
			e1.printStackTrace();
			}
		return null;
	}
	
	
	

}
