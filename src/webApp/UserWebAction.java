package webApp;

import coreComponents.Core;

public class UserWebAction {

	public static void launch(int service_id){
		Core.getInstance().startService(service_id);
	}
	
	public static void stop(int service_id){
		Core.getInstance().stopService(service_id);
	}
}
