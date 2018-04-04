package core;

import java.io.File;
import java.util.List;

public interface InterfaceIA {

	String genererReponse(String question, String classifierId);
	
	String createAI(File csvTraining);
	
	Boolean isAvailable(String cid);
	
	public List<String> getAIClasses(String cid);
}
