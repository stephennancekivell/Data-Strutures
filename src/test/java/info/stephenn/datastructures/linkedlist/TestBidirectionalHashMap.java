package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import java.util.HashMap;

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
		assertEquals("a",biMap.getKey("a"));
		assertEquals("b",biMap.getValueFromKey("a"));
		assertEquals("b",biMap.getKey("b"));
		assertEquals("a",biMap.getValueFromKey("b"));
	}
	
	@Test
	public void canGrowAndGetBack(){
		BidirectionalHashMap<String> biMap = new BidirectionalHashMap<String>();
		biMap.put("a", "b");
		biMap.put("c", "d");
		biMap.put("e", "f");
		
		assertEquals("a", biMap.getKey("a"));
		assertEquals("b", biMap.getKey("b"));
		assertEquals("c", biMap.getKey("c"));
		assertEquals("d", biMap.getKey("d"));
		assertEquals("e", biMap.getKey("e"));
		assertEquals("f", biMap.getKey("f"));
	}
}