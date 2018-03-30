package donnees;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.ICore;
import core.IDonnees;
import core.InterfaceIA;
import ia.IaWatson;

public class DonneesMySql implements IDonnees{
	
	private Connection c;
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
	public InterfaceIA getAI(String iDAI, Class<? extends InterfaceIA> AIClass)  {
		PreparedStatement statement;
		try {
			statement = c.prepareStatement("SELECT CID FROM Classifier where CID = ?");
		
		statement.setString(1, iDAI);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requ�te");
		}
		
		else {
			InterfaceIA ia;
				ia = AIClass.getConstructor(ICore.class, String.class).newInstance(core, rs.getString("CID"));
				return ia;
			}
		}
			 catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
