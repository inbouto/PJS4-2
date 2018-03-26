package donnees;

public class PhraseClasse {
	private String phrase, classe;
	private int nbOccurrences = 1;

	public PhraseClasse(String mot, String classe) {		
		this.phrase = mot;
		this.classe = classe;
	}

	public String getPhrase() {
		return phrase;
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
		return this.phrase + ";" + this.classe;
	}
	
}
