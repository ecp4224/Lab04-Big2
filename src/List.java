

/**
 * Created by corpa on 3/2/2016.
 */
public class List<T> implements ListInterface<T>{

    private Node<T> head;
    private int size;

    public List(){head = null; size = 0;}
    public int getCurrentSize(){return size;}
    public boolean isEmpty(){return head == null;}

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
        //Saves the old value of head
        Node<T> temp = head;
        //Sets head to a clear new node
        head = new Node<T>();
        //Sets the head to the newEntry data
        head.setData(newEntry);
        //Links the next link from head to the old head value
        head.setNext(temp);
        //temp.setPrevious(head);
        size++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        return null;
    }

    public T remove() {
        if (head == null){
            return null;
        }
        //Get first data
        T toReturn = (T) this.head.getData();

        //Set firstNode to firstNode.next
        this.head = this.head.getNext();

        //Set the previous node for the new firstNode to null
        this.head.setPrevious(null);
        size--;

        return toReturn;
    }
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

                //Set the previous node for the next node of the current node to the previous node of the current node
                //And set the next node for the previous node of the current node to the next node of the current node
                next.setPrevious(previous);

                if (previous != null) {
                    previous.setNext(next);
                } else { //If the previous is null, then we're at the head
                    this.head = next;
                }

                //Set the current's node next and previous to null
                cur.setNext(null);
                cur.setPrevious(null);

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
        //While the bag is empty call the remove function
        while(isEmpty() == false){
            remove();
        }
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        return false;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    public int getFrequencyOf(T anEntry){
        //Initialize variable
        int frequency = 0;
        Node<T> temp = this.head;
        //Checks all nodes; When it finds one the same as anEntry increases frequency
        while (temp != null){
            if (temp.getData().equals(anEntry)){
                frequency++;
            }
            temp = temp.getNext();
        }
        return frequency;
    }
    public boolean contains(T anEntry) {
        Node<T> cur = this.head;
        while (cur != null) {
            if (cur.getData().equals(anEntry)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLength() {
        return 0;
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