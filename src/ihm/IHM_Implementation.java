package ihm;

import java.io.IOException;

import core.Core;

public class IHM_Implementation implements Runnable {
	
	private static ICore core;

	public IHM_Implementation (ICore core) {
		this.core = core;
	}
	
	@Override
	public void run() {
		try {
			new IHM_Menu();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static ICore getCore() {
		return core;
	}
	
}
