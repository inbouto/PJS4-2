package ihm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import core.InterfaceIHM;

public class IHMTest {

	@Test
	public void test() {
		
		InterfaceIHM IHM = new IHMV1();
		IHM.affichage("Salut");
		IHM.affichage(IHM.saisie());	
		
	}

}
