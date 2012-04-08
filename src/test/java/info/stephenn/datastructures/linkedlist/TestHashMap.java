package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHashMap {
	
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
	
	@Test
	public void entryCountIsAccurate(){
		HashMap<String,String> map = new HashMap<String,String>();
		assertEquals(0,map.getEntryCount());
		map.put("a", "b");
		assertEquals(1,map.getEntryCount());
		map.put("a", "c");
		assertEquals(2,map.getEntryCount());
	}
	
	@Test
	public void profile1(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		long toPutIn = 10000;
		long start = System.currentTimeMillis();
		
		for (int i=0; i < toPutIn; i++){
			map.put(Integer.toOctalString(i), i);
		}
		
		long end = System.currentTimeMillis();
		
		float insertsPerMilliSecond = toPutIn / (end-start);
		
		System.out.println("inserts pr milli "+ insertsPerMilliSecond);
	}
}