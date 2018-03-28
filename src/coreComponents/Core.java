package coreComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import core.ICore;
import core.InterfaceIA;

public class Core implements ICore {

	static{
		instance = new Core();
		
	}
	private final String initFile;
	private static Core instance;
	private List<Runnable> ihm;
	private InterfaceIA classifierAI;
	private List<Thread> threads;
	
	public Core() {
		initFile = "init";
		ihm = new ArrayList<Runnable>();
		threads = new ArrayList<Thread>();
		
	}

	//WARNING : N'EST PAS THREAD-SAFE !!!!!
	
	//TODO: TESTS
	
	
	//Cette fonction initialise la liste des components, puis charge dans l'ordre les components précisés
	@Override
	public void init() {
		//List<Class<?>> loadedComponents = getComponentsFromFile(initFile); <= give up on the init file. Use the data base instead
		List<Class<?>> loadedComponents = new ArrayList<Class<?>>();
		try {
			loadedComponents.add(Class.forName("ia.IaWatson"));
			loadedComponents.add(Class.forName("mail.AttenteMail"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

for(Class<?> c : loadedComponents){
		try {
			if(aiCheck(c) && this.classifierAI == null){
				//TODO: Code sale, peut-on faire autrement qu'un cast ???
				this.classifierAI = (InterfaceIA) c.newInstance();
			}
			else if(runnableCheck(c)){
				//TODO: Code sale, peut-on faire autrement qu'un cast ???
				Thread t = new Thread((Runnable) c.getConstructor(ICore.class).newInstance(this));
				threads.add(t);
			}
			else{
				throw new UnknownComponentTypeException();
			}
			
		} catch (UnknownComponentTypeException e) {
			System.err.println("Le CoreComponent : " + c.toString() + "n'a pas pu être lancé");
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
		
}
	
	private boolean aiCheck(Class<?> c){
		return InterfaceIA.class.isAssignableFrom(c);
	}
	
	//vérifie si la classe passée en argument est runnable et si elle a un constructeur public content un argument de type ICore
	private boolean runnableCheck(Class<?> c) {
		try {
			return Runnable.class.isAssignableFrom(c) && c.getConstructor(ICore.class) != null;
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
	public String askAI(String s) {
		return this.classifierAI.genererReponse(s);
	}

	public static ICore getInstance() {
		return instance;
	}



	@Override
	//Démarre les Runnable (services) puis sauvegarde les threads correspondant pour pouvoir les interrompre
	public void fullLaunch() {
		for(Thread t : threads){
			try {
				t.start();
			} catch (IllegalArgumentException | SecurityException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String toString(){
		String res = "IA : "+ classifierAI.getClass() +"\nIHM : ";
		for(Runnable r : ihm){
			res += "\n" + r.getClass().getName();
		}
		return res;
	}
	
	@Override
	public void fullStop(){
		for(Thread t : threads){
			t.interrupt();
		}
	}
	
	
	
	
}
