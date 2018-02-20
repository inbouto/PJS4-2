package core;

public class Core {
	
	private static ICoreComponentManager CCM;
	
	
	
	
	public static void main(String[] args) {
		
		try {
			//Si vous avez l'exception "ArrayIndexOutOfBounds", assurrez-vous que vous avez bien set l'argument en ligne de commande à coreComponents.CoreComponentManager
			//allez dans Run->Run configurations->Arguments et ajoutez coreComponents.CoreComponentManager
			CCM = (ICoreComponentManager) Class.forName(args[0]).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println("Impossible de charger le CoreComponentManager. L'application va maintenant se fermer.");
			e.printStackTrace();
			System.exit(-1);
		}
		CCM.initComponents();
		//Juste une ligne pour tester si le Component chargé marche bien
		CCM.getIHM().affichage(CCM.getIA().genererReponse(CCM.getIHM().saisie()));
		
	}
	
	public static InterfaceIA getIA() {
		return CCM.getIA();
	}

	public static InterfaceIHM getIHM() {
		return CCM.getIHM();
	}
	public static InterfaceDonnees getDonnees() {
		return CCM.getDonnees();
	}

	
}
