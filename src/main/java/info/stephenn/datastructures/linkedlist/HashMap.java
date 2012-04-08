package info.stephenn.datastructures.linkedlist;

public class HashMap<K,V> {
	
	Entry<K,V>[] table; //never full. never has more items than threshHold.
	
	static int INIT_CAPACITY=2;
	int threshHold;
	private int entryCount;
	
	public int getEntryCount() {
		return entryCount;
	}

	float LOAD_FACTOR = 0.7f;
	
	public HashMap(){
		table = new Entry[INIT_CAPACITY];
		entryCount=0;
		threshHold = calcThreshHold();
	}
	
	private int calcThreshHold() {
		int t = (int) (LOAD_FACTOR * table.length);
		assert(t < table.length);
		return t;
	}
	
	public void put(K key, V value){
		if (entryCount >= threshHold) growTable();
		table[findIndexFor(key, table)] = new Entry<K,V>(key,value);
		entryCount+=1;
	}
	
	public V get(K key){
		return table[findIndexFor(key,table)].value;
	}
	
	private int findIndexFor(K key, Entry<K,V>[] eTable){
		final int startIndex = key.hashCode() % eTable.length;
		int index = startIndex;
		
		while(true){
			if (eTable[index] == null || eTable[index].key.equals(key)) {
											// could compare hashes.
				return index;
			} else {
				index = (index+1) % eTable.length;
				//TODO experiment with value.
				assert(index != startIndex);
			}
		}
	}
	
	private void growTable(){
		Entry<K,V>[] nTable = new Entry[table.length << 1];
		
		for (Entry<K,V> e : table){
			if (e != null){
				nTable[findIndexFor(e.key, nTable)] = e;
			}
		}
		
		table = nTable;
		threshHold = calcThreshHold();
	}
	
	class Entry<K,V> {
		final K key;
		V value;
		public Entry(K k, V v){
			key = k;
			value =v;
		}
	}
}