package core;

import java.io.File;

public interface InterfaceIA {

	String genererReponse(String question, String classifierId);
	
	String createAI(File csvTraining);
	
	Boolean isAvailable(String cid);
}
