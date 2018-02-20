package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import donnees.MotClasse;
import donnees.TrainingData;

public class TrainingDataTest {

	@Test
	public void test() throws IOException {		
		File f = new File("newfic.csv");
		TrainingData td = new TrainingData();
		td.ajouter("Bien le bonjour", "Salutations");
		td.ajouter("La viande est a point", "Nourriture");
		td.ajouter("La viande est a point", "Nourriture");
		td.ajouter("Je lis Les Miserables", "Livre");
		td.ajouter("Je prends le train pour aller au travail", "Transport");
		td.ajouter("Cette guitare est acoustique", "Musique");
		td.ajouter("Oui", null);
		System.out.println("Affichage 1");
		ArrayList<MotClasse> mc1 = td.getDocument();
		for (MotClasse m : mc1){
			System.out.println(m.toString() + m.getNbOccurrences());
		}
		try {
			td.save(f);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		System.out.println("Affichage 2");
		TrainingData td2 = new TrainingData(f);
		ArrayList<MotClasse> mc = td2.getDocument();
		for (MotClasse m : mc){
			System.out.println(m.toString() + m.getNbOccurrences());
		}
		System.out.println("Classes vides : ");
		
		ArrayList<MotClasse> mc3 = td2.getClassesVides();
		for (MotClasse m : mc3){
			System.out.println(m.toString() + m.getNbOccurrences());
		}
		
//		td.ajouter("Bienvenue", "Salutations");
//		try {
//			td.save(f);
//			
//		} catch (IOException e) {			
//			e.printStackTrace();
//		}
//		System.out.println("Affichage 3");
//		mc1 = td.getDocument();
//		for (MotClasse m : mc1){
//			System.out.println(m.toString() + m.getNbOccurrences());
//		}
	}

}
