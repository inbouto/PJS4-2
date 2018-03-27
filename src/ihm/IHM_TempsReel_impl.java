package ihm;

import core.AbstractIHM;
import core.Core;
import core.InterfaceIHM_Utilisation;

public class IHM_TempsReel_impl extends AbstractIHM  {

	public IHM_TempsReel_impl(Core core) {
		super(core);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread tempsReel = new Thread(new IHM_TempsReel());
	}


}
