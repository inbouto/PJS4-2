package services.IHM;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Vector;

import core.Service;
import coreComponents.Core;
import core.ICore;

public class IHM_Implementation implements Service {
	
	private List<Thread> threads;
	private ServerSocket s;
	
	private static int port = 7999;
	
	private ICore core;
	private int service_id;
	
	@SuppressWarnings("unused")
	private String IdService;
	private boolean run;

	
	public static void main(String[] args) {
		new Thread(new IHM_Implementation(null, 0)).start();
	}
	
	
	//TODO : remplacer le String AIid par int service_id
	public IHM_Implementation (ICore core, int service_id) {
		
		try {
			this.core = core;
			this.service_id = service_id;
			threads = new Vector<Thread>();
			s = new ServerSocket(port);
			run=true;
		} catch (IOException e) {
			System.err.println("SERVICE LAUNCH IHM_Implementation ABORTED : ");
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run() {
		try {
			while(run){
				Thread t = new Thread(new IHM_Comm(s.accept(), core, service_id));
				threads.add(t);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		for(Thread t : threads){
			t.interrupt();
		}
		run=false;
		
	}

	@Override
	public String getName() {
		return "IHM";
	}
	
	
	
	
}
