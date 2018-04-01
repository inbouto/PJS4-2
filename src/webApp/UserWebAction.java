package webApp;

import coreComponents.Core;

public class UserWebAction {

	public static void launch(int service_id){
		Core.getInstance().startService(service_id);
	}
	
	public static void stop(int service_id){
		Core.getInstance().stopService(service_id);
	}
	
	public static void createService(String name, String type, String CID, String login, String pwd){
		Core.getInstance().createService(name, type, CID, login, pwd);
	}
	
	public static void deleteService(int service_id){
		Core.getInstance().deleteService(service_id);
	}
}
