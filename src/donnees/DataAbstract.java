package donnees;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class DataAbstract implements IData {	
	//private static final long serialVersionUID = 1L;
	//private Object obj;
	private int id;
	private static int NUMERO_DATA;
	
//	public Data(Object o){		
//		this.obj = o;
//		this.id = NUMERO;
//		numero();
//	}
	
	public DataAbstract(){
		this.id = NUMERO_DATA;
	}
	
	public int numero(){
		return NUMERO_DATA++;
	}
	
//	public Object getObj(){
//		return this.obj;
//	}
		
	// méthode readObject, utilisée pour reconstituer un objet sérializé
//    public  void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
//       // l'ordre de lecture doit être le même que l'ordre d'écriture d'un objet
//       this.obj = inStream.readUTF() ;
//       this.id = inStream.readInt() ;
//       // le salaire n'est pas relu, vu qu'il n'a pas été écrit
//   }
//    
//    public  void writeObject(ObjectOutputStream outStream) throws IOException {
//    	// écriture de toute ou partie des champs d'un objet
//    	outStream.writeUTF((String) obj) ;
//    	outStream.writeInt(id) ;
//    	// on choisit de ne pas écrire le salaire, qui ne fait
//    	// pas partie de l'état d'une instance de marin
//   }


	public int getId() {
		return id;
	}
	
	// TODO
	public String getName(){
		return null;
		
	}
}
