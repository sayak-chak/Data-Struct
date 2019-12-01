import java.util.LinkedList;
import java.io.*;
class EmptyStack extends Exception
{
    public EmptyStack(String message)
    {
        super(message);
    }
}
class Stack<T>
{
    /**
     * Implementing a stack using a doubly linked list
     */
    private T head;
    private LinkedList<T> stack; //not List as addFirst()/addLast()/removeFirst()/removeLast() methods are exclusive to LinkedList
    Stack()
    { 
        head = null; 
        stack = new LinkedList<>();
    }
    Stack(T first_ele) 
    { 
        head = first_ele;
        stack = new LinkedList<>();
        stack.add(head);
    }
    boolean isEmpty()
    {
        return stack.size()==0;
    }
    void push(T ele)
    {
        head = ele;
        stack.add(head);
    }
    T peek()
    {
        try{
        if(stack.isEmpty()) throw new EmptyStack("Can't peek, stack is empty"); 
        } catch(Exception e) {e.printStackTrace();}
        return stack.getLast();
    }
    void pop()
    {
        try{
            if(stack.isEmpty()) throw new EmptyStack("Can't pop, stack is empty");
            head = stack.removeLast();
            head = peek();
        } catch(Exception e) {e.printStackTrace();}
    }
    int size()
    {
        return stack.size();
    }
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder("[");
        for(T var: stack) out.append(var+" ");
        return out.append("<--head ]").toString();
    }
}
class Driver 
{//Sample Test Class
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> s = new Stack<>();
        System.out.println("Size of Stack is : "+s.size());
        System.out.println(s.toString());
        System.out.println("Push 5 elements");
        for(int i=0;i<5;i++){int ip = Integer.parseInt(in.readLine()); s.push(ip);}
        System.out.println(s.toString());
        System.out.println("Topmost ele is "+s.peek()); 
        System.out.println("Popping an ele");
        s.pop(); 
        System.out.println(s.toString());
        System.out.println("Generating an error now");
        s.pop();s.pop();s.pop();s.pop();s.pop();s.pop();
    }
}
