package linkedList;

public interface TDAList<T> {
	public boolean isEmptyList();
	public int length();
	public void destroyList();
	public int search(T x);
	public void insertFirst(T x);
	public void insertLast(T x);
	public void removeNode(T x);
	
}
