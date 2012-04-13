package info.stephenn.datastructures.linkedlist;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		int increment = 1000000;
		int count = 0;
		
		for (int i=0 ; i < 50; i++){
			profileSome(increment,count,map);
			count += increment;
		}
	}
	
	private void profileSome(int toPut, int putFrom, HashMap<String,Integer> map){
		long start = System.currentTimeMillis();
		for (int i=putFrom; i < toPut+putFrom; i++){
			map.put(Integer.toOctalString(i), i);
		}
		long end = System.currentTimeMillis();
		float insertsPerMilliSecond = toPut / (end-start);
		System.out.println("inserts pr milli "+ insertsPerMilliSecond + " got to size "+map.getEntryCount() +" over "+(end-start)+"ms occupancy "+map.getOccupancy()+" collisions"+map.getCollisions());
	}
	
	@Test
	public void profile2() throws FileNotFoundException{
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		Integer[] data = loadData();
		
		int increment = 1000000;
		int count = 0;
		
		for (int i=0 ; i < 50; i++){
			profileSomeFrom(data,count,increment+count,map);
			count += increment;
		}
	}
	
	private void profileSomeFrom(Integer[] data, int from, int to, HashMap<String,Integer> map){
		long start = System.currentTimeMillis();
		for(int i=from; i < to; i++){
			map.put(Integer.toString(data[i].hashCode()), i);
		}
		long end = System.currentTimeMillis();
		float insertsPerMilliSecond = (to-from) / (end-start);
		System.out.println("inserts pr milli "+ insertsPerMilliSecond + " got to size "+map.getEntryCount() +" over "+(end-start)+"ms occupancy "+map.getOccupancy()+" collisions"+map.getCollisions());
	}
	
	private Integer[] loadData() throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream( "key.txt"));
		List<Integer> list = new ArrayList<Integer>();
		while (sc.hasNext()){
			list.add(sc.nextInt());
		}
		return list.toArray(new Integer[0]);
	}
}