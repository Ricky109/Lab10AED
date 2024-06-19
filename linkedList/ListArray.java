package linkedList;

public class ListArray<T> implements TDAList<T>{
	private static final int MAX_SIZE = 100; // Tamaño máximo del arreglo
    private T[] array;
    private int size;

    public ListArray() {
        array = (T[]) new Object[MAX_SIZE];
        size = 0;
    }

    @Override
    public boolean isEmptyList() {
        return size == 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public void destroyList() {
        size = 0;
    }

    @Override
    public int search(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertFirst(T x) {
        if (size < MAX_SIZE) {
            for (int i = size; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = x;
            size++;
        } else {
            System.out.println("List is full, cannot insert.");
        }
    }

    @Override
    public void insertLast(T x) {
        if (size < MAX_SIZE) {
            array[size++] = x;
        } else {
            System.out.println("List is full, cannot insert.");
        }
    }

    @Override
    public void removeNode(T x) {
        int index = search(x);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }
}
