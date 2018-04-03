package core;

import java.util.List;

public interface ICore {

	public void init();

	public String askAI(String s, String aIid);
	

	public String getPassword(int sERVICE_ID);

	public String getUsername(int SERVICE_ID);

	public String getPhraseFromClass(String topClass);


	public String getAIFromService(int sERVICE_ID);

	void launch();
	
	public String getClasseService(int SERVICE_ID);

	public void startService(int service_id);
	
	public Boolean isRunning(int service_id);
	
	public List<Integer> getServicesID();

	public void stopService(int service_id);

	public String getAIName(String aiFromService);

	public String getServiceName(int service_id);

	public String getAIType(String cid);


	public List<Integer> getRunningServiceIDsFromAI(String cid);

	public List<String> getAIs();

	public List<String> getPlatformNames();

	public void createService(String name, String type, String cID, String login, String pwd);

	public List<String> getAITypes();

	public void deleteService(int service_id);

	public void setAIName(String cid, String name);

	public String getServiceType(int service_id);
	
	
	
}