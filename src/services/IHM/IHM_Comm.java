package services.IHM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import core.ICore;

public class IHM_Comm implements Runnable {

	private Socket s;
	private ICore core;
	private int service_id;
	
	public IHM_Comm(Socket s, ICore core, int service_id) {
		super();
		this.core = core;
		this.service_id = service_id;
		this.s = s;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		    
		    while(true){
		    	
		    	out.println("response for : " + in.readLine());
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
