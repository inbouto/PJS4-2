package core;


public interface ICore {

	public void init();

	public String askAI(String s);
	
	public void fullLaunch();

	public void fullReset();
	
	public Boolean isAnyServiceRunning();
	
	
}