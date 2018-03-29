package appli;

import core.ICore;
import coreComponents.Core;
import services.IHM_Implementation;

public class Application {

	public static void main(String[] args) {
		typicalStartup();
	}
	
	public static void typicalStartup(){
		ICore core = Core.getInstance();
		
		core.fullReset();
		core.init();
		System.out.println(core);
		core.fullLaunch();
	}
	
	public static void fullStop(){
		Core.getInstance().fullReset();
	}
	
	
	

}
