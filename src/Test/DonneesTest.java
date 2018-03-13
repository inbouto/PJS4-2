package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

import core.InterfaceDonnees;
import donnees.DonneesV1;
import donnees.IData;

public class DonneesTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		InterfaceDonnees donnees = new DonneesV1();
		IData d1 = donnees.save("Eh bien bonjour");
		assertEquals(d1.getId(), 1);
		assertEquals(donnees.retrieve(d1),("Eh bien bonjour"));
		IData d2 = donnees.save("Salut");
		assertEquals(d2.getId(), 2);
		assertEquals(donnees.retrieve(d2),("Salut"));
		IData d3 = donnees.save("yo");
		assertEquals(d3.getId(), 3);
		assertEquals(donnees.retrieve(d3),("yo"));
		File f = new File("donneestest.txt");
		donnees.sauvegarder(f);
		//ArrayList<IData> ld = donnees.getListData();
	}

}
