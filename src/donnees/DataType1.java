package donnees;

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
}
