package appli;

import core.ICore;
import coreComponents.Core;



public class Main {

	public static void main(String[] args) {
		ICore core = new Core();
		
		core.init();
		
		core.launch();

	}

}
