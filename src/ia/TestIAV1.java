package ia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import core.InterfaceIA;

public class TestIAV1 {

	@Test
	public void test() {
		InterfaceIA ia = IACCM.genererIA();
		String question = "Comment allez-vous ?";
		String result = ia.genererReponse(question);
		assertEquals(result, "Her name is Caroline");
	}

}
