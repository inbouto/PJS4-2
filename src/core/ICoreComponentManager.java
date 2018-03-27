package core;


public interface ICoreComponentManager {

	public void initComponents();

	public InterfaceIA getIA();

	public InterfaceIHM_Utilisation getIHM();
	
	public InterfaceDonnees getDonnees();
	
}