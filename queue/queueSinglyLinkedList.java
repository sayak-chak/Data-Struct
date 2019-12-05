import java.io.*;
import java.util.EmptyStackException;
class Queue<Type>
{
    /**
     * Implements a queue with a singly linked list. Doesn't make use of the collection framework 
     * In a queue, elements are added from rear - Enqueue
     * Removed from front - Dequeue
     * ADT structure:
     * 1. Node front, rear
     * 2. int size
     * 3. Queue(), Queue (Type firstElement) O(1)
     * 4. int size() O(1)
     * 5. boolean isEmpty() O(1)
     * 6. Type peek() O(1)
     * 7. Type dequeue() O(1)
     * 8. void enqueue(Type element) O(1)
     * 9. Type remove(Type element) O(n) 
     */
    private class Node<Type>
    {
        private Type data; 
        private Node next;
        Node(Type d, Node n)
        {
            data = d; next = n;
        }
    }
    private Node front, rear;
    private int size;
    public Queue()
    {
        front = rear = null;
        size = 0;
    }
    public Queue(Type ele)
    {
        front = rear = new Node(ele, null);
        size = 1;
    }
    public int size()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public Type peek()
    {
        if(front==null) throw new EmptyStackException();
        return (Type)front.data;
    }
    public Type dequeue()
    {
        if(front == null) throw new EmptyStackException();
        Type temp = (Type)front.data;
        if (size==1) rear = front.next;
        front = front.next;
        size--;
        return temp;
    }
    public void enqueue(Type ele)
    {
        if(size == 0)
        {
            front = rear = new Node(ele,null); size = 1;
        }
        else
        {
            rear.next = new Node(ele, null);
            rear = rear.next;
            size++;
        }
    }
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder("(front)--> ");
        Node trav = front;
        while(trav!=null) 
        { 
            out.append(trav.data+" ,");
            trav = trav.next;
        }
        return out.append("<--(rear)").toString();
    }    
}
class Driver
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new Queue<>();
        for(int i=0;i<10;i++) {System.out.println(i); queue.enqueue(i);}
        System.out.println(queue.toString());
        for(int i =0;i<5;i++) queue.dequeue();
        System.out.println(" Empty = "+queue.isEmpty()+" Size = "+queue.size());
        System.out.println(queue.toString());
        System.out.println(queue.peek());
    }
}
