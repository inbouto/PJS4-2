package coreComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import core.ICore;
import core.Service;
import core.InterfaceIA;

public class Core implements ICore {

	static{
		instance = new Core();
		
	}
	private final String initFile;
	private static Core instance;
	private InterfaceIA classifierAI;
	
	
	private Map<Service, Thread> applications;
	
	
	public Core() {
		initFile = "init";
		applications = new HashMap<Service, Thread>();
		init();
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
			loadedComponents.add(Class.forName("services.mail.AttenteMail"));
			loadedComponents.add(Class.forName("services.IHM.IHM_Implementation"));
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
				applications.put((Service) c.getConstructor(ICore.class).newInstance(this), null);
				
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
		stopAllServices();
		applications.clear();
		classifierAI = null;
	}
	
	private boolean aiCheck(Class<?> c){
		return InterfaceIA.class.isAssignableFrom(c);
	}
	
	//vérifie si la classe passée en argument est runnable et si elle a un constructeur public content un argument de type ICore
	private boolean serviceCheck(Class<?> c) {
		try {
			return Service.class.isAssignableFrom(c) && c.getConstructor(ICore.class) != null;
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
		for(Service r : applications.keySet()){
			try {
				Thread t = new Thread(r);
				applications.put(r, t);
				t.start();
			} catch (IllegalArgumentException | SecurityException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String toString(){
		String res = "IA : "+ classifierAI.getClass() +"\nIHM : ";
		for(Service s : applications.keySet()){
			res += "\n" + s.getClass().getName();
		}
		return res;
	}
	
	
	public void stopAllServices(){
		for(Service s : applications.keySet()){
			s.kill();
			applications.get(s).interrupt();
		}
	}

	@Override
	public Boolean isAnyServiceRunning() {
		for(Thread t : applications.values()){
			if(t != null)
				if(t.isAlive())
					return true;
		}
		return false;
	}

	@Override
	public Boolean isRunning(String name) {
		try {
			Service s = getServiceFromName(name);
			System.out.println(s.isRunning());
			return s.isRunning();
		} catch (NoSuchServiceException e) {
			System.err.println("cannot find service \""+name+"\"");
		}
		
		return false;
		
	}

	@Override
	public List<String> getServicesNames() {
		List<String> r = new Vector<String>();
		for(Service s : applications.keySet())
			r.add(s.getName());
		return r;
	}

	@Override
	public void startService(String name) {
		try {
			Service s;
			s = getServiceFromName(name);
			Thread t = new Thread(s);
			applications.put(s, t);
			t.start();
				
		} catch (NoSuchServiceException e) {
			System.err.println("cannot find service \""+name+"\"");
		}
		
		
		
	}
	
	public Service getServiceFromName(String name) throws NoSuchServiceException{
		for(Service s : applications.keySet())
			if(s.getName().toLowerCase().equals(name.toLowerCase()))
				return s;
		throw new NoSuchServiceException();
	}

	@Override
	public void stopService(String name) {
		try {
			getServiceFromName(name).kill();
			applications.get(getServiceFromName(name)).interrupt();
			
		} catch (NoSuchServiceException e) {
			System.err.println("cannot find service \""+name+"\"");
		}
		
	}
	
	
	
	
}
