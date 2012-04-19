package info.stephenn.datastructures;

import static org.junit.Assert.*;
import info.stephenn.datastructures.KDTree.Node;
import info.stephenn.datastructures.KDTree.Node.Point;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestKDTree {
	
	@Test
	public void canCreate(){
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(new int[]{2,1}));
		list.add(new Point(new int[]{1,1}));
		list.add(new Point(new int[]{3,3}));
		
		KDTree.Node head = KDTree.build(list, 0);
		
		assertEquals(0, head.axis);
		assertEquals(1, head.left.axis);
		assertEquals(1, head.right.axis);
		assertArrayEquals(new int[] {2,1}, head.point.values);
		assertArrayEquals(new int[] {1,1}, head.left.point.values);
		assertArrayEquals(new int[] {3,3}, head.right.point.values);
	
		//order doesnt matter
		list.add(list.remove(1));
		head = KDTree.build(list, 0);
		
		assertEquals(0, head.axis);
		assertEquals(1, head.left.axis);
		assertEquals(1, head.right.axis);
		assertArrayEquals(new int[] {2,1}, head.point.values);
		assertArrayEquals(new int[] {1,1}, head.left.point.values);
		assertArrayEquals(new int[] {3,3}, head.right.point.values);
	}
	
	@Test
	public void canLookup(){
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(new int[]{2,1}));
		list.add(new Point(new int[]{1,1}));
		list.add(new Point(new int[]{3,3}));
		
		Node head = KDTree.build(list, 0);
		
		KDTree.Node result = head.getChild(new Point(new int[]{2,1}));
		assertNotNull(result);
		assertArrayEquals(new int[]{2,1}, result.point.values);
		
		result = head.getChild(new Point(new int[] {3,3}));
		assertNotNull(result);
		assertArrayEquals(new int[]{3,3}, result.point.values);
		
		result = head.getChild(new Point(new int[]{1,1}));
		assertNotNull(result);
		assertArrayEquals(new int[]{1,1}, result.point.values);
		
		result = head.getChild(new Point(new int[] {9,9}));
		assertNull(result);
	}
	
	@Test
	public void canFindNearest(){
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(new int[]{2,1}));
		list.add(new Point(new int[]{1,1}));
		list.add(new Point(new int[]{3,3}));
		
		Node head = KDTree.build(list, 0);
		System.out.println(head.toString());
		
		Node result = head.findClosest(null, new Point(new int[]{2,1}));
		assertNotNull(result);
		assertArrayEquals(new int[] {2,1}, result.point.values);
		
		result = head.findClosest(null, new Point(new int[]{2,2}));
		assertNotNull(result);
		assertArrayEquals(new int[] {2,1}, result.point.values);
	}
	
	@Test
	public void nodeGetChild(){
		Node n = new Node(new Point(new int[]{3,2}), 0);
		n.left = new Node(new Point(new int[]{2,3}), 1);
		
		Node lookupNode = n.getChild(new Point(new int[]{2,3}));
		assertEquals(n.left, lookupNode);
		
		n.left.left = new Node(new Point(new int[]{1,2}),0);
		
		lookupNode = n.getChild(new Point(new int[]{1,2}));
		assertEquals(n.left.left, lookupNode);
	}
	
	@Test
	public void squaredDistanceIsCorrect(){
		Point a = new Point(new int[]{0,0});
		Point b = new Point(new int[]{2,2});
		assertEquals(8, a.squaredDistanceTo(b));
	}
	
	@Test
	public void nodeGetNearChild(){
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(new int[]{2,1}));
		list.add(new Point(new int[]{1,1}));
		list.add(new Point(new int[]{3,3}));
		
		Node head = KDTree.build(list, 0);
		
		assertArrayEquals(new int[]{1,1}, head.getChildNear(new Point(new int[]{2,1})).point.values);
		assertArrayEquals(new int[]{1,1}, head.getChildNear(new Point(new int[]{1,1})).point.values);
		assertArrayEquals(new int[]{1,1}, head.getChildNear(new Point(new int[]{0,0})).point.values);
		assertArrayEquals(new int[]{1,1}, head.getChildNear(new Point(new int[]{-1,-1})).point.values);
		assertArrayEquals(new int[]{3,3}, head.getChildNear(new Point(new int[]{5,5})).point.values);
	}
	
	@Test
	public void nodeGetFarChild(){
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(new int[]{2,1}));
		list.add(new Point(new int[]{1,1}));
		list.add(new Point(new int[]{3,3}));
		
		Node head = KDTree.build(list, 0);
		
		assertArrayEquals(new int[]{1,1}, head.getChildFar(new Point(new int[]{3,3})).point.values);
		assertArrayEquals(new int[]{3,3}, head.getChildFar(new Point(new int[]{2,1})).point.values);
		assertArrayEquals(new int[]{3,3}, head.getChildFar(new Point(new int[]{1,1})).point.values);
		assertArrayEquals(new int[]{3,3}, head.getChildFar(new Point(new int[]{0,0})).point.values);
		assertArrayEquals(new int[]{3,3}, head.getChildFar(new Point(new int[]{-1,-1})).point.values);
		assertArrayEquals(new int[]{1,1}, head.getChildFar(new Point(new int[]{5,5})).point.values);
	}
}