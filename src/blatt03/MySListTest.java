package blatt03;
import static org.junit.Assert.*;

import org.junit.Test;


public class MySListTest {

	@Test
	public void testCreation() {
		MySList list = new MySList();
		
		assertEquals("[]", list.toString());
		
		list.push_back(1);
		list.push_back(2);
		list.push_back(4);
		list.push_back(3);
		assertEquals("[1,2,4,3]", list.toString());
	}

	@Test
	public void testOddCreation() {
		MySList list = new MySList();
		int expected[] = {85, 93, 81};
		int i = 0;
				
		list.push_back(85);
		list.push_back(72);
		list.push_back(93);
		list.push_back(81);
		list.push_back(74);
		list.push_back(42);
		assertEquals("[85,72,93,81,74,42]", list.toString());
		
		for (int obj : list) {
			if (i > expected.length)
				fail("Iterated over more than expected objects");
			
			assertEquals(expected[i], obj);
			i++;
		}
		
		// List sohuld not be altered.
		assertEquals("[85,72,93,81,74,42]", list.toString());
	}
}
