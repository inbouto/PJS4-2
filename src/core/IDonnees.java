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

	String getAIName(String aIID);

	String getServiceName(int service_id);

	String getAIType(String cid);

	List<Integer> getRunningServiceNameFromAI(String cid);

	List<String> getAIs();

	List<String> getPlatformNames();

	void createService(String name, String type, String cID, String login, String pwd);

	List<String> getAITypes();

	void deleteService(int service_id);

	void setAIName(String cid, String name);

	String getServiceType(int service_id);

	List<String> getClasses(String cid);

	String getClasseText(String classe);

	void setClassText(String classId, String classText);

	/*void createAI(String name, String cid);

	void addClasses(List<String> aiClasses, String cid);*/

}
