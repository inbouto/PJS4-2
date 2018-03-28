package core;


public interface ICore {

	public void init();

	public String askAI(String s);
	
	public ICore getInstance();
	
	public void launch();
	
}