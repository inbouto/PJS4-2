package services.IHM;

import java.io.IOException;

import core.ICore;
import core.IService;

public class IHM_Implementation implements IService {
	
	private ICore core;

	public IHM_Implementation (ICore core) {
		this.core = core;
	}
	
	@Override
	public void run() {
		try {
			new IHM_Menu(core);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean isRunning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "IHM";
	}
}
