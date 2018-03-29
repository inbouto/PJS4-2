package core;

public interface IService extends Runnable {
	public void kill();
	
	public Boolean isRunning();
	
	public String getName();
}
