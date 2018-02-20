package donnees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public interface IData {

	void save(File f) throws IOException;
	Object retrieve() throws IOException;
	
	
}
