package core;

import java.util.List;

public interface IDonnees {

	String getPassword(int sERVICE_ID);

	String getUsername(int SERVICE_ID);

	String getPhraseFromClass(String topClass);

	String getAIFromService(int SERVICE_ID);

	String getClasseService(int SERVICE_ID);
	
	//eventually, getServices should take into account what user is asking for their services. For the demo, we'll assume there is only one user.
	List<Integer> getServices();

}
