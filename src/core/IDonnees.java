package core;

import java.sql.SQLException;

public interface IDonnees {

	String getPassword(String SERVICE_ID) throws SQLException;

	String getUsername(String SERVICE_ID) throws SQLException;

	//String getAIResponse(String s, String iDAI);

	InterfaceIA getAI(String iDAI);

}
