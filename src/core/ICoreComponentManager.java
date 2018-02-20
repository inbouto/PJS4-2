package core;


public interface ICoreComponentManager {

	public void initComponents();

	public InterfaceIA getIA();

	public InterfaceIHM getIHM();
	
	public InterfaceDonnees getDonnees();
	
}