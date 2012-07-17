package blatt03;
import static org.junit.Assert.*;

import org.junit.Test;

public class IntSListTest {
	class OddPredicate implements IntSList.Predicate {
		public boolean test(int el) {
			return el % 2 == 0;
		}
	}
	
	@Test
	public void testCreation() {
		IntSList list = new IntSList();
		
		assertEquals(list.toString(), "[]");
		
		list.push_back(85);
		list.push_back(72);
		list.push_back(93);
		list.push_back(81);
		list.push_back(74);
		list.push_back(42);
		assertEquals(list.toString(), "[85,72,93,81,74,42]");
	}

	@Test
	public void testFilter() {
		IntSList list = new IntSList();
		IntSList filteredList;
				
		list.push_back(85);
		list.push_back(72);
		list.push_back(93);
		list.push_back(81);
		list.push_back(74);
		list.push_back(42);
		assertEquals(list.toString(), "[85,72,93,81,74,42]");
		
		filteredList = list.filter(new OddPredicate());
		
		// Old list stay unchanged
		assertEquals(list.toString(), "[85,72,93,81,74,42]");
		assertEquals(filteredList.toString(), "[72,74,42]");
	}
}
