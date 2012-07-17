package blatt03;
import static org.junit.Assert.*;

import org.junit.Test;


public class SortListTest {

	@Test
	public void testCreation() {
		SortList<String> list = new SortList<String>();
		
		assertEquals(list.toString(), "[]");
	}
	
	@Test
	public void testSortedInsert() {
		SortList<String> list = new SortList<String>();
		
		list.insert("Apfel");
		assertEquals(list.toString(), "[Apfel]");
		list.insert("Kiwi");
		assertEquals(list.toString(), "[Apfel,Kiwi]");
		list.insert("Ananas");
		assertEquals(list.toString(), "[Ananas,Apfel,Kiwi]");
		list.insert("Birne");
		assertEquals(list.toString(), "[Ananas,Apfel,Birne,Kiwi]");
	}

	@Test
	public void testNoDoubleInsert() {
		SortList<String> list = new SortList<String>();
		
		list.insert("Apfel");
		assertEquals(list.toString(), "[Apfel]");
		assertFalse(list.insert("Apfel"));
		assertEquals(list.toString(), "[Apfel]");
	}
}
