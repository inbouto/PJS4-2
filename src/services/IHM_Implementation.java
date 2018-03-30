package services;

import java.io.IOException;
import core.Service;

import core.ICore;

public class IHM_Implementation implements Service {
	
	private ICore core;
	private String AIid;
	
	private String IdService;

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
}
