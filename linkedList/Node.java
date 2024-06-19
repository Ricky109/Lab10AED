package linkedList;

public class Node<T>{
	private T data;
	private Node<T> next;
	
	Node(T data){
		this(data, null);
	}
	
	Node(T d, Node<T> n){
		this.data = d;
		this.next = n;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setNext(Node<T> n) {
		this.next = n;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
	
	

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
	
	
	
}
