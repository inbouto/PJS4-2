package ia;

import core.InterfaceIA;
import coreComponents.CoreComponentManager;

public abstract class IACCM extends CoreComponentManager{
	
	public static InterfaceIA genererIA() {
		return new IAV1();
	}
	

}
