package appli;

import java.util.List;

import core.ICore;
import coreComponents.Core;
import services.IHM.IHM_Implementation;

public class Application {

	public static void main(String[] args) {
		typicalStartup();
	}
	
	public static void typicalStartup(){
		ICore core = Core.getInstance();
		
		core.fullReset();
		core.init();
		core.fullLaunch();
	}
	
	public static void fullStop(){
		Core.getInstance().fullReset();
	}
	
	public static Boolean isAnythingRunning(){
		return Core.getInstance().isAnyServiceRunning();
	}
	
	public static Boolean isServiceRunning(String name){
		return Core.getInstance().isRunning(name);
	}
	
	public static List<String> getServicesNames(){
		return Core.getInstance().getServicesNames();
	}
	
	public static void startService(String name){
		Core.getInstance().startService(name);
	}
	
	public static void stopService(String name){
		Core.getInstance().stopService(name);
	}
	
}
