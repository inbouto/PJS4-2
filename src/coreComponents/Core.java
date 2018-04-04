package coreComponents;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import core.ICore;
import core.IDonnees;
import core.InterfaceIA;
import core.Service;
import donnees.DonneesMySql;

public class Core implements ICore {

	static{
		instance = new Core();
	}
	private static Core instance;
	private List<Class<? extends Service>> services;
	private InterfaceIA classifierAI;
	private IDonnees donnees;
	private Map<Integer, Pair<Service,Thread>> userServices;
	
	@SuppressWarnings("unchecked")
	public Core() {
		services = new ArrayList<Class<? extends Service>>();
		userServices = new HashMap<Integer, Pair<Service,Thread>>();
		
		//Ce bloc initialise la liste des components, puis charge dans l'ordre les components précisés
		List<Class<?>> loadedComponents = new ArrayList<Class<?>>();
		try {
			//List<Class<?>> loadedComponents = getComponentsFromFile(initFile);
			loadedComponents.add(Class.forName("ia.IaWatson"));
			loadedComponents.add(Class.forName("services.mail.AttenteMail"));
			
		for(Class<?> c : loadedComponents){
			try {
				if(aiCheck(c) && this.classifierAI == null){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.classifierAI = ((Class<? extends InterfaceIA>) c).getConstructor(ICore.class).newInstance(this);
				}
				else if(runnableCheck(c)){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.services.add((Class<? extends Service>) c);
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
			
			//TODO : charger dynamiquement comme le reste
			donnees = new DonneesMySql(this);
		
		}
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		
		
	}

	//WARNING : N'EST PAS THREAD-SAFE !!!!!
	
	//TODO: TESTS
	
	
	//charge les services affiliés à l'utilisateur en cours
	@Override
	public void init() {
		for(int i : donnees.getServices()){
			userServices.put(i, null);
		}
	}
	
	private boolean aiCheck(Class<?> c){
		try {
			c.getConstructor(ICore.class);
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
	@SuppressWarnings("unused")
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
	public String askAI(String s, String IDAI) {
		return classifierAI.genererReponse(s, IDAI);
	}

	public static ICore getInstance() {
		return instance;
	}



	@Override
	//Démarre les Runnable donnés dans le fichier init
	public void launch() {
		for(Integer i : userServices.keySet()){
				startService(i);
		}
	}
		
		
	

	@Override
	public String toString(){
		String r = "IA : "+ classifierAI +"\nIHM : ";
		for(Class<? extends Runnable> c : services){
			r += "\n" + c.getName();
		}
		return r;
	}

	@Override
	public String getPassword(int SERVICE_ID) {
		return donnees.getPassword(SERVICE_ID);
	}

	@Override
	public String getUsername(int SERVICE_ID) {
		return donnees.getUsername(SERVICE_ID);
	}

	@Override
	public String getPhraseFromClass(String topClass) {		
		return donnees.getPhraseFromClass(topClass);
	}

	@Override
	public String getAIFromService(int sERVICE_ID) {
		return donnees.getAIFromService(sERVICE_ID);
	}
	
	public String getClasseService(int SERVICE_ID){
		return donnees.getClasseService(SERVICE_ID);
	}

	@Override
	public void startService(int service_id) {
		String className = donnees.getClasseService(service_id);
		for(Class<? extends Service> c : services){
			if(c.getName().equals(className)){
				System.out.println("on lance un thread avec " + c);
				try {
					Thread t;
					Service s;
					s = c.getConstructor(ICore.class, int.class).newInstance(this, service_id);
					t = new Thread(s);
					Pair<Service, Thread> p = new Pair<Service, Thread>(s, t);
					userServices.put(service_id, p);
					t.start();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					System.err.println("impossible d'instancier le service : " + c.getName());
					e.printStackTrace();
				}
				
			}
		}
	}

	@Override
	public Boolean isRunning(int service_id) {
		if(userServices.get(service_id) != null)
			return userServices.get(service_id).getRight().isAlive();
		return false;
	}

	@Override
	public List<Integer> getServicesID() {
		return donnees.getServices();
	}

	@Override
	public void stopService(int service_id) {
		if(isRunning(service_id)){
			userServices.get(service_id).getLeft().kill();
			userServices.get(service_id).getRight().interrupt();
		}
			
		
	}

	@Override
	public String getAIName(String AIID) {
		return donnees.getAIName(AIID);
	}

	@Override
	public String getServiceName(int service_id) {
		return donnees.getServiceName(service_id);
	}

	@Override
	public String getAIType(String cid) {
		return donnees.getAIType(cid);
	}

	@Override
	public List<Integer> getRunningServiceIDsFromAI(String cid) {
		return donnees.getRunningServiceNameFromAI(cid);
	}

	@Override
	public List<String> getAIs() {
		return donnees.getAIs();
	}

	@Override
	public List<String> getPlatformNames() {
		return donnees.getPlatformNames();
	}

	@Override
	public void createService(String name, String type, String CID, String login, String pwd) {
		donnees.createService(name, type, CID, login, pwd);
		
	}

	@Override
	public List<String> getAITypes() {
		return donnees.getAITypes();
	}

	@Override
	public void deleteService(int service_id) {
		donnees.deleteService(service_id);
		
	}

	@Override
	public void setAIName(String cid, String name) {
		donnees.setAIName(cid, name);
		
	}
	

	
	
	
	
	

}
