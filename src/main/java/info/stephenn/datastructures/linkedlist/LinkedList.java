package info.stephenn.datastructures.linkedlist;

public class LinkedList<T> {
	private Node<T> head = null;
	private Node<T> tail = null;
	
	public LinkedList(){
		
	}
	
	public void add(T value) {
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
	
	public T get(int index) throws Exception{
		Node<T> n = head;
		for (int i=0; i < index; i++){
			if (n == null){
				throw new java.lang.IndexOutOfBoundsException();
			}
			n = n.getChild();
		}
		return n.getValue();
	}
	
	public T getFirstValue(){
		if (head != null){
			return head.getValue();
		}
		else return null;
	}

	public void removeAt(int index) throws Exception {
		if (index==0){
			this.head = this.head.getChild();
		} else {
			Node<T> a = this.head;
			if (a == null) throw new java.lang.IndexOutOfBoundsException("list head is null");
			Node<T> b = this.head.getChild();
			for (int i =0; i < index; i++){
				if (i==index){
					
				}
			}
		}
	}	
}