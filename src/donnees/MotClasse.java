package donnees;

public class MotClasse {
	private String mot, classe;
	private int nbOccurrences = 1;

	public MotClasse(String mot, String classe) {		
		this.mot = mot;
		this.classe = classe;
	}

	public String getMot() {
		return mot;
	}

	public String getClasse() {
		return classe;
	}

	public int getNbOccurrences() {
		return nbOccurrences;
	}

	public void setNbOccurrences(int nbOccurrences) {
		this.nbOccurrences = nbOccurrences;
	}
	
	public void incNbOccurrences() {
		this.nbOccurrences += 1;
	}
	
	public void setClasse(String classe){
		this.classe = classe;
	}
	
	public String toString(){
		return this.mot + ";" + this.classe;
	}
	
}
