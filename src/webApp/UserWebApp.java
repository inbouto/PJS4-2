package webApp;

import java.util.List;

import core.ICore;
import coreComponents.Core;



public class UserWebApp {

	public static void main(String[] args) {
		ICore core = new Core();
		
		core.init();
		
		core.startService(3);

	}
	
	public static int getUserServiceAmounts(){
		return Core.getInstance().getServicesID().size();
	}
	
	public static String getServiceName(int service_id){
		return Core.getInstance().getServiceName(service_id);
	}
	
	public static String getServiceAIName(int service_id){
		return Core.getInstance().getAIName(Core.getInstance().getAIFromService(service_id));
	}
	
	public static String getServiceUsername(int service_id){
		return Core.getInstance().getUsername(service_id);
	}
	
	public static Boolean getServiceState(int service_id){
		return Core.getInstance().isRunning(service_id);
	}
	
	public static String getAIName(String cid){
		return Core.getInstance().getAIName(cid);
	}
	
	public static String getAIType(String cid){
		return Core.getInstance().getAIType(cid);
	}
	
	public static List<Integer> getRunningServiceIDsFromAI(String cid){
		return Core.getInstance().getRunningServiceIDsFromAI(cid);
	}

}
