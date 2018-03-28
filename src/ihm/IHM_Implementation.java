package ihm;

import java.io.IOException;

import core.ICore;

public class IHM_Implementation implements Runnable {
	
	private ICore Core;

	public IHM_Implementation (ICore core) {
		this.Core = core;
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
