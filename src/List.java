import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class List<T> implements ListInterface<T>{

    private Node<T> head;
    private Node<T> last;
    private int size;

    public List(){head = null; size = 0;}

    public List(java.util.List<T> javaList) {
        for (T item : javaList) {
            add(item);
        }
    }

    public int getCurrentSize(){return size;}
    public boolean isEmpty(){return head == null;}

    public void sort() {
        java.util.List<T> temp = toJavaList();
        Collections.sort(temp, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof Comparable)
                    return ((Comparable) o1).compareTo(o2);
                else
                    return 0;
            }
        });
        List<T> temp2 = new List<T>(temp);

        head = temp2.head;
        last = temp2.last;
        size = temp2.size;
    }

    public java.util.List<T> toJavaList() {
        java.util.List<T> temp = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            temp.add(current.getData());
            current = current.getNext();
        }

        return temp;
    }

    /**
     *
     * @return false, shouldnt ever be full
     */
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void display() {
        int currpos = 0;
        System.out.println("{");
        while(getCurrentSize() != 0){
            System.out.println("" + getEntry(currpos));
            currpos++;
        }
        System.out.println("}");
    }

    public boolean add(T newEntry) {
        if (size == 0) {
            Node<T> temp = new Node<T>();
            temp.setData(newEntry);

            head = temp;
            last = temp;
        } else {
            Node<T> newNode = new Node<T>();
            newNode.setData(newEntry);
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if (newPosition >= size)
            return false;

        Node<T> current = head;
        for (int i = 0; i < newPosition; i++) {
            current = current.getNext();
        }

        Node<T> newNode = new Node<>();
        newNode.setData(newEntry);

        newNode.setPrevious(current.getPrevious());
        newNode.setNext(current);

        if (current.getPrevious() != null) {
            current.getPrevious().setNext(newNode);
        }
        size++;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition >= size)
            throw new IndexOutOfBoundsException("Index: " + givenPosition + ", Size: " + size);

        Node<T> current = head;
        for (int i = 0; i < givenPosition; i++) {
            current = current.getNext();
        }

        Node<T> previous = current.getPrevious();
        Node<T> next = current.getNext();

        if (next != null) {
            //Set the previous node for the next node of the current node to the previous node of the current node
            //And set the next node for the previous node of the current node to the next node of the current node
            next.setPrevious(previous);
        } else { //If the next is null, then we're at the end
            this.last = previous;
        }



        if (previous != null) {
            previous.setNext(next);
        } else { //If the previous is null, then we're at the head
            this.head = next;
        }

        T toReturn = current.getData();

        current.setNext(null);
        current.setPrevious(null);
        current.setData(null);
        size--;

        return toReturn;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean removed = false;

        //Start at the beginning
        Node<T> cur = this.head;
        //While we have a node to check
        while (cur != null) {
            //If this node's data equals to the parameter
            if (cur.getData().equals(anEntry)) {

                //Get the previous and next node of the current node we're checking
                Node<T> previous = cur.getPrevious();
                Node<T> next = cur.getNext();

                if (next != null) {
                    //Set the previous node for the next node of the current node to the previous node of the current node
                    //And set the next node for the previous node of the current node to the next node of the current node
                    next.setPrevious(previous);
                } else {
                    this.last = previous;
                }

                if (previous != null) {
                    previous.setNext(next);
                } else { //If the previous is null, then we're at the head
                    this.head = next;
                }

                //Set the current's node next and previous to null
                cur.setNext(null);
                cur.setPrevious(null);
                cur.setData(null);

                //We removed a node, so return true, decrease the size and break from this loop
                removed = true;
                size--;
                break;
            }
            //If we end up here, we didn't break from the loop
            //So go to the next node
            cur = cur.getNext();
        }
        return removed;
    }
    public void clear() {
        this.head = null;
        this.last = null;
        size = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if (givenPosition >= size)
            throw new IndexOutOfBoundsException("Index: " + givenPosition + ", Size: " + size);

        Node<T> current = this.head;
        for (int i = 0; i < givenPosition; i++) {
            current = current.getNext();
        }

        current.setData(newEntry);

        return true;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= size)
            throw new IndexOutOfBoundsException("Index: " + givenPosition + ", Size: " + size);

        Node<T> current = this.head;
        for (int i = 0; i < givenPosition; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean contains(T anEntry) {
        Node<T> cur = this.head;
        while (cur != null) {
            if (cur.getData().equals(anEntry)){
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    @Override
    public int getLength() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[])new Object[size]; //Create array of the same size as bag

        Node<T> cur = this.head; //Start at first node
        int i = 0;
        while (cur != null) { //If the current node is not null
            array[i] = cur.getData(); //Set the current element to the current node's data
            cur = cur.getNext(); //Set the current node to the current node's next node
            i++; //Increment the counter
        }
        return array; //Cast the array to T[] and return
    }
    @SuppressWarnings("hiding")
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;
        public Node(){this.data = null;}
        public void setData(T data){this.data = data;}
        public void setNext(Node<T> next){this.next = next;}
        public void setPrevious(Node<T> prev){this.prev = prev;}
        public T getData(){return data;}
        public Node<T> getNext(){return next;}
        public Node<T> getPrevious(){return prev;}
    }
}