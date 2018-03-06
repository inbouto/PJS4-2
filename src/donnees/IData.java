package donnees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public interface IData {

	String getName();
	int getId();
	Object getContenu();
	void sauvegarder(File f) throws IOException;
	
}
