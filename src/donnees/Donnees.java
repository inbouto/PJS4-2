package donnees;

import java.sql.*;

import core.IDonnees;
import core.InterfaceIA;

public class Donnees implements IDonnees{
	
	private Connection c;
	
	static{
		
	}
	
	public Donnees(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.c = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL","GRP201US4","GRP201US4");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getPassword(String SERVICE_ID) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT mdp FROM service where idservice = ?");
		statement.setString(1, SERVICE_ID);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requête");
		}
		else {
			String password = rs.getString("mdp");
			return password;
		}
		return null;
	}

	@Override
	public String getUsername(String SERVICE_ID) throws SQLException {
		PreparedStatement statement = c.prepareStatement("SELECT login FROM service where idservice = ?");
		statement.setString(1, SERVICE_ID);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requête");
		}
		else {
			String username = rs.getString("login");
			return username;
		}
		return null;
	}

	
	@Override
	public InterfaceIA getAI(String iDAI) {
		PreparedStatement statement = c.prepareStatement("SELECT login FROM service where idservice = ?");
		statement.setString(1, SERVICE_ID);
		ResultSet rs = statement.executeQuery();
		if(!rs.next()){
			System.out.println("Erreur requête");
		}
		else {
			String username = rs.getString("login");
			return username;
		}
		return null;
	}
	}

}
