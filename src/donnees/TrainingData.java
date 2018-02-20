 package donnees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.element.Element;

public class TrainingData extends DataAbstract{
	//private ArrayList<String> mots;
	private ArrayList<String> phrases;
	private ArrayList<String> classes;
	private ArrayList<MotClasse> document; //Contient des MotClasse associant une chaine à une classe
	private File fichier;
	
	
	// Constructeur sans paramètre, crée une TrainingData vide
	public TrainingData(){
		super();
		this.phrases = new ArrayList<String>();
		this.classes = new ArrayList<String>();
		this.document = new ArrayList<MotClasse>();
	}
	
	
	// Constructeur avec un fichier en paramètre, crée une TrainingData contenant les données d'un fichier 
	public TrainingData(File f){
		super();
		this.fichier = f;
		this.phrases = new ArrayList<String>();
		this.classes = new ArrayList<String>();
		this.document = new ArrayList<MotClasse>();
		try {
			this.retrieve();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
	
	// Ajoute une phrase et sa classe dans les listes correspondantes de la Training Data
	public void ajouter(String phrase, String classe){
		phrase = phrase.toLowerCase();				
		// Ajout d'une classe à la liste des classes si elle n'y est pas déjà
		if (!this.classes.contains(classe)){
			this.classes.add(classe);
		}		
		this.phrases.add(phrase);
		
		MotClasse mc = new MotClasse(phrase, classe);
		// Ajout des MotClasse pour les mots n'existant pas dans la liste
		boolean contains = false;
		for (MotClasse element : this.document){
			if ((element.getMot().equals(phrase)) && (element.getClasse().equals(classe))){
				contains = true;
				element.incNbOccurrences();
			}				
		}
		if (!contains){
			this.document.add(mc);
		}
	}		
	
	
	//Enregistre les données d'une TrainingData dans un fichier
	public void save(File f) throws IOException{		
		FileWriter writer = new FileWriter(f, false);
		BufferedReader reader =  new BufferedReader(new FileReader(f));
		String ligne = "";
		boolean contains = false;
		for (MotClasse mc : this.document){
			while ((ligne =reader.readLine()) !=null){
				if (ligne.equals(mc.toString()))
						contains = true;
			}
			if (!contains)
			writer.write(mc.toString()+"\r\n");			
		}
		reader.close();
		writer.close();
	}
	
	// Recupère les données d'un fichier et actualise les listes de la TrainingData
		public ArrayList<MotClasse> retrieve() throws IOException{
			//File fic = new File("listeMotClasse.txt");
			BufferedReader reader =  new BufferedReader(new FileReader(this.fichier));
			String ligne = "";
			while ((ligne =reader.readLine()) !=null){
				String parts[] = ligne.split(";");
				this.phrases.add(parts[0]);
				this.classes.add(parts[1]);
				MotClasse mc = new MotClasse(parts[0], parts[1]);
				this.document.add(mc);
			}
			reader.close();
			return this.document;
			
		}
	
	
//	// Recupère les données d'un fichier et actualise les listes de la TrainingData
//	public ArrayList<MotClasse> retrieve() throws IOException{
//		//File fic = new File("listeMotClasse.txt");
//		BufferedReader reader =  new BufferedReader(new FileReader(this.fichier));
//		String ligne = "";
//		while ((ligne =reader.readLine()) !=null){
//			String parts[] = ligne.split(",");
//			this.phrases.add(parts[0]);
//			this.classes.add(parts[1]);
//			MotClasse mc = new MotClasse(parts[0], parts[1]);
//			this.document.add(mc);
//		}
//		reader.close();
//		return this.document;
//		
//	}
	
	// Renvoie la lsite des MotClasse ayant une classe vide 
	public ArrayList<MotClasse> getClassesVides(){
		ArrayList<MotClasse> classesVides = new ArrayList<MotClasse>();
		for (MotClasse mc : this.document){
			if (mc.getClasse().equals("null")){
				classesVides.add(mc);
			}				
		}
		return classesVides;
	}

	public ArrayList<String> getPhrases() {
		return phrases;
	}

	public ArrayList<String> getClasses() {
		return classes;
	}

	public ArrayList<MotClasse> getDocument() {
		return document;
	}

	public File getFichier() {
		return fichier;
	}
}
