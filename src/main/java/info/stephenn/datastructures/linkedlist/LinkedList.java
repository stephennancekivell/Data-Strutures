package info.stephenn.datastructures.linkedlist;

public class LinkedList<T> {
	private Node<T> head = null;
	private Node<T> tail = null;
	
	public LinkedList(){
		
	}
	
	public void add(T value) throws Exception{
		if (head == null){
			head = new Node<T>(value);
			tail = head;
		}
		else {
			Node<T> newNode = new Node<T>(value);
			tail.setChild(newNode);
			tail = newNode;
		}
	}
}