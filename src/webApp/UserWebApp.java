package webApp;

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
		//TODO : THIS SHOULD CHANGE to give us the name of the service instead
		return Core.getInstance().getClasseService(service_id);
	}
	
	public static String getServiceInfo(int service_id){
		//TODO : THIS SHOULD CHANGE to give us the name of the AI instead
		return Core.getInstance().getAIFromService(service_id) + " on " + Core.getInstance().getUsername(service_id);
	}
	
	public static Boolean getServiceState(int service_id){
		return Core.getInstance().isRunning(service_id);
	}

}
