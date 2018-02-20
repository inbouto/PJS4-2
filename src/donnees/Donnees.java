//package donnees;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//
//import core.InterfaceDonnees;
//
//public class Donnees implements InterfaceDonnees {
//	File fic;
//	
//	public Donnees(File f){
//		this.fic = f;
//	}
//
//	@Override
//	public IData save(Object o) throws FileNotFoundException, IOException {
//		Data d = new Data(o);		
//		d.writeObject(new ObjectOutputStream(new FileOutputStream(fic)));		
//		return d;
//	}
//
//	@Override
//	public Object retrieve(IData i) {
//		
//		return i.getObj();
//	}
//
//	
//}
