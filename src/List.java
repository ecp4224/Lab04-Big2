/**
 * Created by corpa on 3/2/2016.
 */
public class List<T> implements ListInterface<T>{

    private Node<T> head;
    private int nodeCount = 0;
    
    @Override
    public boolean add(T newEntry) {
        if(head == null){
            head = new Node<>();
            head.setData(newEntry);
            head.setNext(null);
            nodeCount++;
            return true;
        }

        Node current = new Node();
        Node<T> last = new Node<>();

        while(head.getNext() != null){
            current = head.getNext();
        }

        last.setNext(null);
        last.setData(newEntry);
        current.setNext(last);
        nodeCount++;

        return true;
    }

    @Override
    public boolean add(int newPosition, Object newEntry) {
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean replace(int givenPosition, Object newEntry) {
        return false;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    @Override
    public boolean contains(Object anEntry) {
        return false;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void display() {

    }
}

class Node<T>{
    private T data;
    private Node next;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}