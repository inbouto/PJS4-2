package donnees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataType1 extends DataAbstract {
	private String contenu="";
	
	public DataType1(){
		super();
	}
	
	public void setContenu(String s){
		this.contenu=s;
	}
	
	public String getContenu(){
		return this.contenu;
	}
	
	public void sauvegarder(File f) throws IOException{
		FileWriter writer = new FileWriter(f, true);		
		writer.write(this.getContenu()+"\r\n");
		writer.close();
	}
}
