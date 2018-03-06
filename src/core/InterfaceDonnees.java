package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import donnees.IData;

public interface InterfaceDonnees {
//getNom, getType => faire une enum
	
	
	
	// Permet d'encapluser un Object dans une IData portant un numero unique
	public IData save (Object o) throws FileNotFoundException, IOException;
	
	//Permet de récuperer le contenu d'une IData
	public Object retrieve (IData i);

	public void sauvegarder(File f) throws IOException;
	
	
//	public String getNom();
//	public String getType();
}