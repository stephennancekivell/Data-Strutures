package info.stephenn.datastructures;

import static org.junit.Assert.*;
import info.stephenn.datastructures.linkedlist.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestKDTree {
	
	@Test
	public void canCreate(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(Arrays.asList(2, 1));
		list.add(Arrays.asList(1, 1));
		list.add(Arrays.asList(3, 3));
		
		KDTree.Node head = KDTree.build(list, 0);
		
		assertEquals(new Integer(2), head.keys.get(0));
		assertEquals(new Integer(1), head.keys.get(1));
		assertEquals(new Integer(1), head.left.keys.get(0));
		assertEquals(new Integer(1), head.left.keys.get(1));
		assertEquals(new Integer(3), head.right.keys.get(0));
		assertEquals(new Integer(3), head.right.keys.get(1));
	
		//order doesnt matter
		list.add(list.remove(1));
		head = KDTree.build(list, 0);
		
		assertEquals(new Integer(2), head.keys.get(0));
		assertEquals(new Integer(1), head.keys.get(1));
		assertEquals(0, head.axis);
		assertEquals(new Integer(1), head.left.keys.get(0));
		assertEquals(new Integer(1), head.left.keys.get(1));
		assertEquals(1, head.left.axis);
		assertEquals(new Integer(3), head.right.keys.get(0));
		assertEquals(new Integer(3), head.right.keys.get(1));
		assertEquals(1, head.right.axis);
	}
	
	@Test
	public void canLookup(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(Arrays.asList(2, 1));
		list.add(Arrays.asList(1, 1));
		list.add(Arrays.asList(3, 3));
		
		KDTree.Node head = KDTree.build(list, 0);
		
		KDTree.Node result = head.getChild(Arrays.asList(2,1));
		assertNotNull(result);
		assertEquals(new Integer(2),result.keys.get(0));
		assertEquals(new Integer(1),result.keys.get(1));
		
		result = head.getChild(Arrays.asList(3,3));
		assertNotNull(result);
		assertEquals(new Integer(3),result.keys.get(0));
		assertEquals(new Integer(3),result.keys.get(1));
		
		result = head.getChild(Arrays.asList(1,1));
		assertNotNull(result);
		assertEquals(new Integer(1),result.keys.get(0));
		assertEquals(new Integer(1),result.keys.get(1));
		
		result = head.getChild(Arrays.asList(9,9));
		assertNull(result);
	}
}