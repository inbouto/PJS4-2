package WatsonIA;

import java.io.File;
import java.io.FileNotFoundException;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifierList;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.GetClassifierOptions;

import ia.NaturalLanguageClassifier;

public class Main {
	
	public static String AMA(NaturalLanguageClassifier service, String phrase){
		
		String classifierId = "";
		
		ClassifierList list = service.listClassifiers().execute();
		
		System.out.println(service.getEndPoint());
		System.out.println(service.getName());
		System.out.println(list.getClassifiers());
		System.out.println(service.getToken());
		
		
		
		classifierId = list.getClassifiers().get(0).getClassifierId();
		
		GetClassifierOptions getOptions = new GetClassifierOptions.Builder()
				  .classifierId(classifierId)
				  .build();
		System.out.println(service.getClassifier(getOptions).execute().getStatus());
		
		ClassifyOptions options = new ClassifyOptions.Builder()
				.classifierId(classifierId)
				.text(phrase)
				.build();
		Classification result = service.classify(options).execute();
		return result.getText() + result.getTopClass();
	}
	
	public static String createAndTrain(NaturalLanguageClassifier service,String pathTrainingFile, String pathMetadataFile) throws FileNotFoundException{
		File trainingData = new File(pathTrainingFile);
		File metaData = new File(pathMetadataFile);
		CreateClassifierOptions options = new CreateClassifierOptions.Builder()
				.metadata(metaData)
				.trainingData(trainingData)
				.build();
		Classifier response = service.createClassifier(options).execute();
		return response.getClassifierId();
	}
	
	
	
	public static void main(String[] args) {
		String username = "e88c4313-d9a2-445c-8246-29168c3ef6a6";
		String password = "6QMUX5QdO6vm";
		NaturalLanguageClassifier service = new NaturalLanguageClassifier(username,password);
		//classifier.setEndPoint(url);
		
		
		
//		String pathTrainingFile = "./ressources/weather_data_train.csv";
//		String pathMetadataFile = "./ressources/metadata.json";
//		try {
//			String classifierId = createAndTrain(service, pathTrainingFile, pathMetadataFile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(AMA(service,"how hot is it"));
		
	}
}
