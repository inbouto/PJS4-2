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
			statement = c.prepareStatement("SELECT CID FROM service where idService = '" +SERVICE_ID +"'");
		
		
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
			System.err.println("Erreur requête : getServiceName : " + service_id);
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
			statement = c.prepareStatement("select idservice from service where CId='"+cid+"'");
		
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

	@Override
	public List<String> getAIs() {
		List<String> listCIDs = new Vector<String>();
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("select cid from classifier");
		
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			listCIDs.add(rs.getString("cid"));
		}
		return listCIDs;
		} catch (SQLException e1) {
			System.err.println("Erreur requête : getAIs");
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public List<String> getPlatformNames() {
		List<String> listNames = new Vector<String>();
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("select platName from platforms");
		
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			listNames.add(rs.getString("platname"));
		}
		return listNames;
		} catch (SQLException e1) {
			System.err.println("Erreur requête : getPlatformNames");
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public void createService(String name, String type, String CID, String login, String pwd) {
		 try {
			Statement st = c.createStatement();
			st.executeUpdate("insert into service (classeService, login, mdp, name, platName, CID) VALUES ('" + getPlatformClass(type) + "', '" + login + "', '" + pwd + "', '" + name + "', '" + type + "', '" + CID + "')");
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : createService");
			e.printStackTrace();
		} 
		
	}
	
	private String getPlatformClass(String platformName) {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT class FROM platforms where platname = '" + platformName + "'");
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getPlatformClass : " + platformName);
		}
		else {
			String cls = rs.getString("class");
			System.out.println(cls);
			return cls;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public List<String> getAITypes() {
		List<String> l = new Vector<String>();
		l.add("Natural language classifier (NLC)");
		return l;
	}

	@Override
	public void deleteService(int service_id) {
		try {
			Statement st = c.createStatement();
			st.executeUpdate("delete from service where idService = " + service_id);
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : createService");
			e.printStackTrace();
		} 
		
	}

	@Override
	public void setAIName(String cid, String name) {
		try {
			Statement st = c.createStatement();
			st.executeUpdate("update classifier set nomClassifier='" + name + "' where cid='" + cid + "'");
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : setAIName");
			e.printStackTrace();
		} 
		
	}

	@Override
	public String getServiceType(int service_id) {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT platName FROM service where idService = " + service_id);
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getServiceType : " + service_id);
		}
		else {
			String cls = rs.getString("platName");
			System.out.println(cls);
			return cls;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public List<String> getClasses(String cid) {
		List<String> listNames = new Vector<String>();
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("select idClasse from classe where cid='" + cid + "'");
		
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			listNames.add(rs.getString("idClasse"));
		}
		return listNames;
		} catch (SQLException e1) {
			System.err.println("Erreur requête : getClasses : " + cid);
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public String getClasseText(String classe) {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT text FROM classe where idClasse = '" + classe + "'");
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.err.println("Erreur requête : getClasseText : " + classe);
		}
		else {
			String cls = rs.getString("text");
			System.out.println(cls);
			return cls;
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		return null;
	}

	@Override
	public void setClassText(String classId, String classText) {
		try {		
			
			PreparedStatement st = c.prepareStatement("update classe set text=? where idClasse=?");
			
			st.setString(1, classText);
			st.setString(2, classId);
			st.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : setClassText");
			e.printStackTrace();
		}
	}

	@Override
	public void createAI(String name, String cid) {
		try {
			Statement st = c.createStatement();
			st.executeUpdate("insert into classifier (cid, nomClassifier) VALUES ('" + cid + "', '" + name + "')");
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : createAI");
			e.printStackTrace();
		} 
		
	}

	@Override
	public void addClasses(List<String> aiClasses, String cid) {
		try {
			Statement st = c.createStatement();
			for(String classe : aiClasses){
				st.executeUpdate("insert into classe (idClasse, cid, text) VALUES ('" + classe + "', '" + cid + "', '')");
			}
			
		} catch (SQLException e) {
			System.err.println("Erreur requête : addClasses");
			e.printStackTrace();
		} 
		
	}
	
	

}
