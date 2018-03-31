package services.IHM;

import java.io.IOException;
import core.Service;

import core.ICore;

public class IHM_Implementation implements Service {
	
	private ICore core;
	private String AIid;
	
	private String IdService;

	
	//TODO : remplacer le String AIid par int service_id
	public IHM_Implementation (ICore core, String AIid) {
		this.core = core;
		this.AIid = AIid;
	}
	
	@Override
	public void run() {
		try {
			new IHM_Menu(core, AIid);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void kill() {
		
		
	}

	@Override
	public String getName() {
		return "IHM";
	}
}
