package Test;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import donnees.Data;
import donnees.IData;

public class DataTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		File f = new File("tmp.txt");
		//Donnees donnees = new Donnees(f);
		Object o = "Salut";
		//Data d = new Data("Salut");
		
		//IData d = donnees.save(o);
		//assertEquals(d.getId(), 0);
		
	}

}
