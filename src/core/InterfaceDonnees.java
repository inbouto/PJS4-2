package core;

import java.io.FileNotFoundException;
import java.io.IOException;

import donnees.IData;

public interface InterfaceDonnees {
//getNom, getType => faire une enum
	public IData save (Object o) throws FileNotFoundException, IOException;
	public Object retrieve (IData i);
//	public String getNom();
//	public String getType();
}