package WatsonIA;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifierList;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.ClassifyOptions;

public class Main {
	public static void main(String[] args) {
		String username = "e88c4313-d9a2-445c-8246-29168c3ef6a6";
		String password = "6QMUX5QdO6vm";
		String url = "https://gateway.watsonplatform.net/natural-language-classifier/api";
		String classifierId = "";
		
		
		NaturalLanguageClassifier classifier = new NaturalLanguageClassifier(username,password);
		classifier.setEndPoint(url);
		
		ClassifierList list = classifier.listClassifiers().execute();
		
		System.out.println(classifier.getEndPoint());
		System.out.println(classifier.getName());
		System.out.println(list.getClassifiers().get(0).getClassifierId());
		System.out.println(classifier.getToken());
		
		classifierId = list.getClassifiers().get(0).getClassifierId();
		
		ClassifyOptions options = new ClassifyOptions.Builder().classifierId(classifierId).text("How hot is it today ?").build();
		Classification result = classifier.classify(options).execute();
		System.out.println(result.getTopClass());
	}
}
