package core;

import java.sql.SQLException;
import java.util.List;

public interface IDonnees {

	String getPassword(int sERVICE_ID);

	String getUsername(int SERVICE_ID);

	
	InterfaceIA getAI(String iDAI, Class<? extends InterfaceIA> classifierAI);

	String getPhraseFromClass(String topClass);

	String getAIFromService(int SERVICE_ID);

	String getClasseService(int SERVICE_ID);
	
	List<Integer> getServices();

}
