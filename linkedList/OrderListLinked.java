package linkedList;


public class OrderListLinked<T extends Comparable<T>> extends ListLinked<T>{

	public OrderListLinked() {
		super();
		
	}
	@Override
	public int search(T x) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(x)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1; // Elemento no encontrado
    }
	public void insert(T x) {
	    Node<T> newNode = new Node<>(x);
	    if (head == null) {
	        head = newNode;
	    } else {
	        Node<T> current = head;
	        Node<T> previous = null;
	        while (current != null && x.compareTo(current.getData()) > 0) {
	            previous = current;
	            current = current.getNext();
	        }

	        // Insertar el nuevo nodo en la posición adecuada
	        newNode.setNext(current); // El nuevo nodo apunta al siguiente nodo
	        if (previous == null) {
	            head = newNode;
	        } else {
	            previous.setNext(newNode);
	        }
	    }
	    size++;
	}
	
	public void remove(T x) {
	    if (head == null) {
	        return;
	    }
	    if (head.getData().equals(x)) {
	        head = head.getNext();
	        size--;
	        return;
	    }

	    Node<T> current = head;
	    Node<T> previous = null;
	    while (current != null && !current.getData().equals(x)) {
	        previous = current;
	        current = current.getNext();
	    }

	    if (current != null) {
	        previous.setNext(current.getNext());
	        size--;
	    }
	}
	
	public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getData() + ", ");
            current = current.getNext();
        }
        System.out.println("null");
    }

	
	
}

