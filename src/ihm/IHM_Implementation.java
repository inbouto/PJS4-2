package ihm;

import java.io.IOException;

import core.Core;

public class IHM_Implementation implements Runnable {
	
	private Core Core;

	public IHM_Implementation (Core core) {
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
	
}
