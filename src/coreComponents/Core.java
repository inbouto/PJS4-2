package coreComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import core.ICore;
import core.IService;
import core.InterfaceIA;

public class Core implements ICore {

	static{
		instance = new Core();
		
	}
	private final String initFile;
	private static Core instance;
	private List<IService> services;
	private InterfaceIA classifierAI;
	private List<Thread> threads;
	
	public Core() {
		initFile = "init";
		services = new Vector<IService>();
		threads = new Vector<Thread>();
		
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
			if(aiCheck(c)){
				//TODO: Code sale, peut-on faire autrement qu'un cast ???
				this.classifierAI = (InterfaceIA) c.newInstance();
			}
			else if(serviceCheck(c)){
				//TODO: Code sale, peut-on faire autrement qu'un cast ???
				services.add((IService) c.getConstructor(ICore.class).newInstance(this));
			}
			else{
				throw new IllegalComponentException();
			}
			
		} catch (IllegalComponentException e) {
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
	public void fullReset(){
		allServicesStop();
		services.clear();
		classifierAI = null;
		threads.clear();
	}
	
	private boolean aiCheck(Class<?> c){
		return InterfaceIA.class.isAssignableFrom(c);
	}
	
	//vérifie si la classe passée en argument est runnable et si elle a un constructeur public content un argument de type ICore
	private boolean serviceCheck(Class<?> c) {
		try {
			return IService.class.isAssignableFrom(c) && c.getConstructor(ICore.class) != null;
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
		for(Runnable r : services){
			try {
				Thread t = new Thread(r);
				threads.add(t);
				t.start();
			} catch (IllegalArgumentException | SecurityException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String toString(){
		String res = "IA : "+ classifierAI.getClass() +"\nIHM : ";
		for(Runnable r : services){
			res += "\n" + r.getClass().getName();
		}
		return res;
	}
	
	
	public void allServicesStop(){
		for(IService s : services){
			s.kill();
		}
		for(Iterator<Thread> i = threads.iterator(); i.hasNext();){
			i.next().interrupt();
			i.remove();
		}
	}

	@Override
	public Boolean isAnyServiceRunning() {
		for(IService s : services){
			if(s.isRunning())
				return true;
		}
		return false;
	}
	
	
	
	
}
