package info.stephenn.datastructures.linkedlist;

public class Node<T> {
	private Node<T> child = null;
	private T value;
	
	public Node(Node<T> parent, T value){
		this.value = value;
	}

	public Node(T value) {
		this.value = value;
	}

	public Node<T> getChild() {
		return child;
	}

	public void setChild(Node<T> child){
		this.child = child;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}