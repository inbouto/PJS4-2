package coreComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.ICore;
import core.IDonnees;
import core.InterfaceIA;
import core.Service;
import donnees.Donnees;

public class Core implements ICore {

	static{
		instance = new Core();
		
	}
	private final String initFile;
	private static Core instance;
	private List<Class<? extends Service>> ihm;
	private Class<? extends InterfaceIA> classifierAI;
	private IDonnees donnees;
	
	
	public Core() {
		initFile = "init";
		ihm = new ArrayList<Class<? extends Service>>();
		
	}

	//WARNING : N'EST PAS THREAD-SAFE !!!!!
	
	//TODO: TESTS
	
	
	//Cette fonction initialise la liste des components, puis charge dans l'ordre les components précisés
	@Override
	public void init() {
		try {
			List<Class<?>> loadedComponents = getComponentsFromFile(initFile);
		
		for(Class<?> c : loadedComponents){
			try {
				if(aiCheck(c) && this.classifierAI == null){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.classifierAI = (Class<? extends InterfaceIA>) c;
				}
				else if(runnableCheck(c)){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.ihm.add((Class<? extends Service>) c);
				}
				else{
					throw new UnknownComponentTypeException();
				}
				
			} catch (UnknownComponentTypeException e) {
				System.err.println("Le CoreComponent : " + c.toString() + "n'a pas pu être lancé");
				e.printStackTrace();
			}
			
			//TODO : charger dynamiquement comme le reste
			donnees = new Donnees(this);
		
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	private boolean aiCheck(Class<?> c){
		try {
			c.getConstructor(ICore.class, String.class);
			return InterfaceIA.class.isAssignableFrom(c);
		} catch (NoSuchMethodException e) {
			System.err.println(c + " n'est pas une IA valide");
		} catch (SecurityException e) {
			System.err.println("problème de sécurité");
			e.printStackTrace();
		}
		return false;
		
	}
	
	//vérifie si la classe passée en argument est runnable et si elle a un constructeur public content un argument de type ICore
	private boolean runnableCheck(Class<?> c) {
		try {
			c.getConstructor(ICore.class, int.class);
			return Runnable.class.isAssignableFrom(c);
		} catch (NoSuchMethodException e) {
			System.err.println("pas de constructeur publique avec ICore en argument");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}



	//Cette fonction charge tous les Components précisés dans le fichier d"initialisation
	private List<Class<?>> getComponentsFromFile(String string) throws IOException {
		List<Class<?>> liste = new ArrayList<Class<?>>();
		for(String s : Files.readAllLines(FileSystems.getDefault().getPath(string))){
				try {
					liste.add(Class.forName(s));
				} catch (ClassNotFoundException e) {
					System.err.println(e);
				}
		}
		return liste;
	}

	

	@Override
	public String askAI(String s, String IDAI) throws SQLException {
		return donnees.getAI(IDAI, classifierAI).genererReponse(s);
	}

	public static ICore getInstance() {
		return instance;
	}



	@Override
	//Démarre les Runnable donnés dans le fichier init
	public void launch() {
		for(Integer i : donnees.getServices()){
			try {
				for(Class<? extends Service> c : ihm){
					if(c.getName().equals(donnees.getClasseService(i))){
						System.out.println("on lance un thread avec " + c);
						Thread t = new Thread(c.getConstructor(ICore.class, int.class).newInstance(this, i));
						t.start();
					}
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.err.println("impossible d'instancier le service : " + i);
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public String toString(){
		String r = "IA : "+ classifierAI +"\nIHM : ";
		for(Class<? extends Runnable> c : ihm){
			r += "\n" + c.getName();
		}
		return r;
	}

	@Override
	public String getPassword(int SERVICE_ID) throws SQLException {
		// TODO Auto-generated method stub
		return donnees.getPassword(SERVICE_ID);
	}

	@Override
	public String getUsername(int SERVICE_ID) throws SQLException {
		// TODO Auto-generated method stub
		return donnees.getUsername(SERVICE_ID);
	}

	@Override
	public String getPhraseFromClass(String topClass) throws SQLException {		
		return donnees.getPhraseFromClass(topClass);
	}

	@Override
	public String getAIFromService(int sERVICE_ID) throws SQLException {
		return donnees.getAIFromService(sERVICE_ID);
	}
	
	public String getClasseService(int SERVICE_ID){
		return donnees.getClasseService(SERVICE_ID);
	}

	
	
	
	
	
}
