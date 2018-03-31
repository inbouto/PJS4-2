package coreComponents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import core.ICore;
import core.InterfaceIA;
import core.Service;

public class ServiceApplication {

	private ICore core;
	private Thread serviceThread;
	private int serviceID;
	private String serviceAIID;
	private Class<? extends Service> serviceClass;
	private Class<? extends InterfaceIA> serviceAIClass;
	private Service serviceInstance;
	private InterfaceIA serviceAIInstance;
	
	public ServiceApplication(ICore core, int serviceID, String serviceAIID, Class<? extends Service> serviceClass,
			Class<? extends InterfaceIA> serviceAIClass) {
		super();
		this.serviceAIID = serviceAIID;
		this.core = core;
		this.serviceID = serviceID;
		this.serviceClass = serviceClass;
		this.serviceAIClass = serviceAIClass;
	}
	
	public void start(){
		
	}
	
	public void updateService(Class<? extends Service> servClass){
		
	}
	
	public void updateAI(Class<? extends InterfaceIA> aiClass){
		
	}
	
	public Boolean isRunning(){
		return null;
		
	}
	
	private InterfaceIA makeAI(){
		
			try {
				return serviceAIClass.getConstructor(ICore.class, String.class).newInstance(core, serviceAIID);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.err.println("error during makeAI : ");
				e.printStackTrace();
			}
		
		return null;
	}
	
}
