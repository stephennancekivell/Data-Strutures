package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeshHashMap {
	
	@Test
	public void canCreate(){
		HashMap<String,String> map = new HashMap<String,String>();
	}
	
	@Test
	public void canPutAndGet(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("a", "b");
		assertEquals("b",map.get("a"));
	}
	
	@Test
	public void canPutGetGrow(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("a", "b");
		map.put("c", "d");
		map.put("e", "f");
		map.put("g", "h");
		assertEquals("b",map.get("a"));
		assertEquals("d",map.get("c"));
		assertEquals("f",map.get("e"));
		assertEquals("h",map.get("g"));
	}
	
	@Test
	public void canOverWrite(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("a", "b");
		map.put("a", "c");
		assertEquals("c",map.get("a"));
	}
}