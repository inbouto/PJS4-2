package services.IHM;

import java.io.IOException;
import core.Service;

import core.ICore;

public class IHM_Implementation implements Service {
	
	private ICore core;
	private int service_id;
	
	@SuppressWarnings("unused")
	private String IdService;

	
	//TODO : remplacer le String AIid par int service_id
	public IHM_Implementation (ICore core, int service_id) {
		this.core = core;
		this.service_id = service_id;
	}
	
	@Override
	public void run() {
		try {
			new IHM_Menu(core, service_id);
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
