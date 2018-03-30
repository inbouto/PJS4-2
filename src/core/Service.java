package core;

public interface Service extends Runnable {
	public void kill();
	
	public Boolean isRunning();
	
	public String getName();
}
