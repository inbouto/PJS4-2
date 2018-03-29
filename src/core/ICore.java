package core;

import java.util.List;

public interface ICore {

	public void init();

	public String askAI(String s);
	
	public void fullLaunch();

	public void fullReset();
	
	public Boolean isAnyServiceRunning();

	public Boolean isRunning(String name);

	public List<String> getServicesNames();

	public void startService(String name);

	public void stopService(String name);
	
	
}