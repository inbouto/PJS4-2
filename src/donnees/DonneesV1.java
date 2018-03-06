package donnees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import core.InterfaceDonnees;

public class DonneesV1 implements InterfaceDonnees {
	File fic;
	
	public DonneesV1(/*File f*/){
		//this.fic = f;
	}

	@Override
	public IData save(Object o) throws FileNotFoundException, IOException {
		//IData d = new IData(o);		
		//d.writeObject(new ObjectOutputStream(new FileOutputStream(fic)));	
		if (o instanceof String){
			DataType1 d = new DataType1();
			d.setContenu((String)o);
			return d;
		}
		return null;
	}

	@Override
	public Object retrieve(IData i) {
		//if (i instanceof DataType1){
		return i.getContenu();
		//}
		//return null;
	}

	
}
