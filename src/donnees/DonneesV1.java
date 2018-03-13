package donnees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import core.InterfaceDonnees;

public class DonneesV1 implements InterfaceDonnees {
	private File fic;
	private ArrayList<IData> listData;
	
//	public ArrayList<IData> getListData() {
//		return listData;
//	}

	public DonneesV1(/*File f*/){
		//this.fic = f;
		this.listData = new ArrayList<IData>();
		
	}
	
	public void setFic(File f){  //Pas sûr de l'utilité
		this.fic = f;
	}

	@Override
	public IData save(Object o) throws FileNotFoundException, IOException {
		//IData d = new IData(o);		
		//d.writeObject(new ObjectOutputStream(new FileOutputStream(fic)));	
		if (o instanceof String){
			DataType1 d = new DataType1();
			d.setContenu((String)o);
			this.listData.add(d);
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
	
	public void sauvegarder(File f) throws IOException{
		for (IData data : listData){
			data.sauvegarder(f);
		}
	}
	
	public void charger(File f) throws IOException{ //TODO
		BufferedReader reader =  new BufferedReader(new FileReader(f));
		String ligne = "";
		while ((ligne =reader.readLine()) !=null){
			DataType1 data = new DataType1();
			data.setContenu(ligne);				
		}
		reader.close();
		
	}
	
}
