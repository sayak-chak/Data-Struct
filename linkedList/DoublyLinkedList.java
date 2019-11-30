import java.io.*;
class Driver
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.add("1");
        list.add("3");
        list.add("456");
        System.out.println(list.toString());
    }
}
class DoublyLinkedList<T>
{
    /**
     * Simple doubly linked list. Code is somewhat sloppy, as in didn't handle possible errors/exceptions.
     * Just the basic implementation assuming valiid input
     * null<---| |data1| |<--->| |data2| |<--->| |data3| |--->null
     * Take care of next and prev Nodes while adding/deleting 
     */
    private int size;
    private Node<T> head,tail;
    public static class Node<T>
    {
        T data; Node<T>prev,next;
        Node(T data, Node <T> prev, Node<T> next)
        {
            this.data = data; this.prev = prev; this.next = next;
        }
    }
    DoublyLinkedList()
    {
        this.size = 0; head = tail = null;
    }
    DoublyLinkedList(Node<T> start )
    {
        head = start; tail = start; start.prev = null; start.next = null; 
        size = 1;
    }
    void clear()
    {
        Node<T> trav;
        trav = head;
        while(trav!=null)
        {
            Node<T> next = trav.next;
            trav = null;
            trav = next;
        }
        head = tail = null; size = 0;
    }
    int size() { return this.size; }
    boolean isEmpty(){ return size ==0; }
    void add(T obj){ addLast(obj); }
    void addLast(T obj)
    {
        if(isEmpty()) head = tail = new Node<T>(obj,null,null); 
        else
        {
            tail = new Node(obj,tail,null);
            tail.prev.next = tail;
        }
        size++;
    }
    void addFirst(T obj)
    {
        if (isEmpty()) head = tail = new Node<T>(obj,null,null);
        else
        {
            head = new Node<T>(obj,null,head);
            head.next.prev = head;
        }
        size++;
    }
    T add(int index, T obj)
    {// adds at ith index from beginning and returns the ele previous;y in index i
        if (i==0) addFirst(obj);
        else if(i==size) addLast(obj);
        else
        {
            int i = 0;
            Node<T> trav = head;
            while(i!=index-1)
            {
                trav = trav.next;
            }
            T temp = trav.data;
            trav.data = obj;
            return temp;
        }    
        return null;
    }
    T peekFirst()
    {
        if (isEmpty()) return null;
        return head.data;
    }
    T peekLast()
    {
        if(isEmpty()) return null;
        return tail.data;
    }
    T removeFirst()
    {
        if (isEmpty()) return null;
        Node<T> temp = head;
        T ret = temp.data;
        head = head.next;
        temp.data = temp.prev = temp.next = null;
        size--;
        return ret;
    }
    T removeLast()
    {
        if (isEmpty()) return null;
        Node<T> temp = tail;
        T ret = temp.data;
        tail = tail.prev;
        temp.data = temp.prev = temp.next = null;
        size--;
        return ret;
    }
    boolean remove(T obj)
    {
        
    }
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder("[");
        Node<T> trav = head;
        while(trav!=null) { out.append(trav.data+" <--> "); trav = trav.next; }
        return out.append("]").toString();
    }
}
