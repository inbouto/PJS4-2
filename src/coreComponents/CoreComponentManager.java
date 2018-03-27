package coreComponents;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import core.ICoreComponentManager;
import core.InterfaceIA;
import core.InterfaceIHM;

public class CoreComponentManager implements ICoreComponentManager {

	
	private ArrayList<Class<?>> loadedComponents;
	private InterfaceIHM ihm;
	private InterfaceIA ia;
	private String initFile;

	//WARNING : N'EST PAS THREAD-SAFE !!!!!
	
	//TODO: TESTS
	
	
	//Le nom par défaut des Component à charger est "init"
	public CoreComponentManager() {
		this("init");
	}
	
	public CoreComponentManager(String initFile) {
		this.initFile = initFile;
		loadedComponents = new ArrayList<Class<?>>();
	}

	public void add(Class<?> class1){
		loadedComponents.add(class1);
	}

	
	
	//Cette fonction initialise la liste des components, puis charge dans l'ordre les components précisés
	@Override
	public void initComponents() {
		try {
			getComponentsFromFile(initFile);
		} catch (ClassNotFoundException e1) {
			System.err.println("Le CoreComponent spécifié dans le fichier " + initFile + " n'existe pas");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Le fichier d'initialisation n'a pas pu être chargé");
			e1.printStackTrace();
		}
		for(Class<?> c : loadedComponents){
			try {
				Object cc = c.newInstance();
				if(InterfaceIA.class.isAssignableFrom(cc.getClass())){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.ia = (InterfaceIA) cc;
				}
				else if(InterfaceIHM.class.isAssignableFrom(cc.getClass())){
					//TODO: Code sale, peut-on faire autrement qu'un cast ???
					this.ihm = (InterfaceIHM) cc;
				}		
				else{
					throw new UnknownComponentTypeException();
					}
				
			} catch (InstantiationException | IllegalAccessException | UnknownComponentTypeException e) {
				System.err.println("Le CoreComponent : " + c.toString() + "n'a pas pu être lancé");
				e.printStackTrace();
			}
		}
	}

	
	
	//Cette fonction charge tous les Components précisés dans le fichier d"initialisation
	private void getComponentsFromFile(String string) throws IOException, ClassNotFoundException {
		for(String s : Files.readAllLines(FileSystems.getDefault().getPath(string))){
			
				//ENCORE UN CAST (meme avec le if, c'est sale)
				this.add((Class<?>) Class.forName(s));
				System.out.println(s + " ajouté à la liste des Components chargés");
		}
	}

	@Override
	public InterfaceIA getIA() {
		return ia;
	}

	@Override
	public InterfaceIHM getIHM() {
		return ihm;
	}

	
	
	
	
}
