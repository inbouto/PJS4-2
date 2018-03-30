package core;

import java.sql.SQLException;

public interface ICore {

	public void init();

	public String askAI(String s, String aIid);
	
	public void launch();

	public String getPassword(String aI_ID) throws SQLException;

	public String getUsername(String aI_ID) throws SQLException;

	
}