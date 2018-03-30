package services.IHM;

import java.io.IOException;
import java.util.Timer;

import core.ICore;
import core.Service;

public class IHM_Implementation implements Service {
	
	private ICore core;
	private Boolean isRunning = false;

	public IHM_Implementation (ICore core) {
		this.core = core;
	}
	
	@Override
	public void run() {
		isRunning = true;
		try {
			new IHM_Menu(core);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		System.out.println("on tourne plus !");
	}

	@Override
	public void kill(){
	}

	@Override
	public Boolean isRunning() {
		return isRunning;
	}

	@Override
	public String getName() {
		return "IHM";
	}
}
