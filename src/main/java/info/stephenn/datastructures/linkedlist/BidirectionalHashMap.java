package info.stephenn.datastructures.linkedlist;

public class BidirectionalHashMap<T> {
	//private Object[] objects; // static array of object to grow exponentially.
	private Node<T>[] objects;
	private int objects_stored = 0;
	private static final int OBJECTS_START_SIZE = 4;
	public BidirectionalHashMap(){
		objects = new Node[OBJECTS_START_SIZE];
	}
	
	public void put(T a, T b){
		this.putNode(new Node<T>(a,b));
		this.putNode(new Node<T>(b,a));
	}
	
	public T get(T value){
		Node<T> node = getNodeFor(value);
		if (node != null){
			return node.value;
		} else {
			return null;
		}
	}
	
	public T getPair(T value){
		Node<T> node = getNodeFor(value);
		if (node != null){
			return node.pair;
		} else {
			return null;
		}
	}
	
	public Node<T> getNodeFor(T value){
		return objects[indexFor(value, objects)];
	}
	
	private void putNode(Node node) {
		if (objects_stored == objects.length) {
			objects = grow();
		}

		putNodeInto(node, this.objects);
		objects_stored += 1;
	}
	
	private int indexFor(T value, Node<T>[] array){
		int index = value.hashCode();
		final int startIndex = index % array.length;
		
		while(true){
			if (array[index % array.length] == null || array[index % array.length].value == value){
				return index % array.length;
			} else {
				index +=1;
				assert(index % array.length != startIndex);
				// Fun point to optimize, some use cases might hit this lots.
			}
		}
	}
	
	private void putNodeInto(Node<T> node, Node<T>[] array) {
		array[indexFor(node.value, array)] = node;
	}

	private Node<T>[] grow(){
		Node<T>[] newArray = new Node[objects.length<<1];
		for (Node<T> node : this.objects){
			if (node != null){
				putNodeInto(node, newArray);
			}
		}
		return newArray;
	}

	private class Node<T>{
		final T value;
		final T pair;
		public Node(T a, T b){
			value = a;
			pair = b;
		}
		@Override
		public int hashCode() {
			return value.hashCode();
		}
	}
}