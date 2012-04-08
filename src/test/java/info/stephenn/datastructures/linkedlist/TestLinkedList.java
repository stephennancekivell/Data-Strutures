package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {

	@Test
	public void shouldCreate() {
		LinkedList<String> ll = new LinkedList<String>();
	}
	
	@Test
	public void shouldBeAbleToPutInAndGetOut() throws Exception{
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("one");
		assertEquals("one",ll.getFirstValue());
	}
	
	@Test
	public void shouldReturnResultsInOrder(){
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("one");
		ll.add("two");
		
		try {
			assertEquals("one",ll.get(0));
			assertEquals("two",ll.get(1));
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void shouldRemove() throws Exception{
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("one");
		ll.add("two");
		
		
		ll.removeAt(0);
		
		assertEquals("two",ll.get(0));
	}
}