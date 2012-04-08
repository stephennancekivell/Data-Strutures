package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBidirectionalHashMap {
	@Test
	public void shifting(){
		assertEquals(4, 2<<1);
	}
	
	@Test
	public void canCreate(){
		BidirectionalHashMap<String> biMap = new BidirectionalHashMap<String>();
	}
	
	@Test
	public void canPutAndGetOne(){
		BidirectionalHashMap<String> biMap = new BidirectionalHashMap<String>();
		biMap.put("a", "b");
		assertEquals("a",biMap.get("a"));
		assertEquals("b",biMap.getPair("a"));
		assertEquals("b",biMap.get("b"));
		assertEquals("a",biMap.getPair("b"));
	}
	
	@Test
	public void canGrowAndGetBack(){
		BidirectionalHashMap<String> biMap = new BidirectionalHashMap<String>();
		biMap.put("a", "b");
		biMap.put("c", "d");
		biMap.put("e", "f");
		
		assertEquals("a", biMap.get("a"));
		assertEquals("b", biMap.get("b"));
		assertEquals("c", biMap.get("c"));
		assertEquals("d", biMap.get("d"));
		assertEquals("e", biMap.get("e"));
		assertEquals("f", biMap.get("f"));	
	}
}