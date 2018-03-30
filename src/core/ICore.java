package core;

import java.sql.SQLException;

public interface ICore {

	public void init();

	public String askAI(String s, String aIid) throws SQLException;
	

	public String getPassword(int sERVICE_ID) throws SQLException;

	public String getUsername(int SERVICE_ID) throws SQLException;

	public String getPhraseFromClass(String topClass) throws SQLException;


	public String getAIFromService(int sERVICE_ID) throws SQLException;

	void launch();
	
	public String getClasseService(int SERVICE_ID);
	
	
}