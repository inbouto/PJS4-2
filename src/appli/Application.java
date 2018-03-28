package appli;

import core.ICore;
import coreComponents.Core;
import ihm.IHM_Implementation;

public class Application {

	public static void main(String[] args) {
		typicalStartup();
	}
	
	public static void typicalStartup(){
		ICore core = Core.getInstance();
		core.init();
		System.out.println(core);
		core.fullLaunch();
	}
	
	public static void fullStop(){
		Core.getInstance().fullStop();
	}
	

}
