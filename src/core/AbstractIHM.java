package core;

public abstract class AbstractIHM implements Runnable {
	private Core core;
	
	protected String ask(String s){
		return core.generateAIResponse(s);
	}

	public AbstractIHM(Core core) {
		super();
		this.core = core;
	}
	
	
	
}