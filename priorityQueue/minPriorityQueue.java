import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
class MinPQ<Type> 
{
    /**
     * Constructs a Min Priority Queue based on a binary Min Heap
     * I've tried to keep the implementation as easy and as close to the JAVA Collections class PriorityQueue  as possible.
     * For example, to convert this to maxPQ, all you need to do is reverse the comprateTo() function
     * *********************************
     * A priority queue is like a normal queue except it adds/removes elements based on their priorities.
     * 1. Using Heap is not the only ways to implement Priority Queues, but it's the most efficient way
     * 2. The elements of PQ should be Comparable
     * *********************************
     * Essentially, min/maxPQ is just a min/max heap with some additional functions like:
     * 1. poll() - > removes the ele at arr[0] & swaps it with last then bubbles down 
     * 2. add() -> adds an ele at last and bubbles up
     * 3. remove(Type ele) - > removes the specific ele and bubbles up/bubbles down as needed (call both to be on safe side)
     * The two functions that are essential in maintaining heap property: bubbleUp() & bubbleDown()
     * ==========================================================================================================
     * ADT Structure
     * ==========================================================================================================
     * 1. int heapCapacity, ine heapSize, Type heapArray[]
     * 2. minPQ()
     * 3. minPQ(Type arr[], int size, int capacity) = adds ele of arr to heapArray
     * 4. boolean isEmpty()
     * 5. void clear()
     * 6.int size()
     * 7. Type peek() = returns heapArray[0] without polling it
     * 8. Type pool() = returns heapArray[0] & removes it -> O(lgn)
     * 9. void add(Type ele) = adds ele to PQ -> O(lgn)
     * 10. void bubbleUp() -> O(lgn)
     * 11. void bubbleDown() -> O(lgn)
     * 12. boolean remove(int ele) = removes the ele -> O(n) without HashMap, O(lgn) with HashMap
     * 13. Type removeAt(int index) = removes the element at specified index an returns its value -> O(lgn)
     * 14. boolean contains(Type ele) -> O(n) withtout HashMap, 0(1) with HashMap
     */
    private int heapCapacity, heapSize;
    Type heapArray[];
    MinPQ()
    {
        heapCapacity = heapSize = 0; heapArray = null;
    }
    MinPQ(Type arr[], int heapSize, int heapCapacity)
    {
        int i;
        this.heapCapacity = heapCapacity;
        this.heapSize = heapSize;
        this.heapArray = (Type[])new Object[heapCapacity];
        for(i=0;i<heapSize;i++) heapArray[i] = arr[i];
        //Converting to MinHeap
        for(i=heapSize/2;i>=0;i--) bubbleDown(i);
    }
    public int compareTo(Type ele1, Type ele2)
    {
        //return -(Integer.parseInt(ele1.toString()) - Integer.parseInt(ele2.toString())); //<---converts it to MaxPQ
        return Integer.parseInt(ele1.toString()) - Integer.parseInt(ele2.toString());
    }
    public boolean isEmpty()
    {
        return heapSize==0;
    }
    public void clear()
    {
        for (int i=0;i<heapCapacity;i++) heapArray[i] = null;
        heapSize = 0;
    }
    public int size()
    {
        return heapSize;
    }
    public Type peek() throws Exception
    {
        if(isEmpty()) throw new Exception("PQ Empty, can't peek");
        return heapArray[0];
    }
    public Type poll() throws Exception
    {
        if(isEmpty()) throw new Exception("PQ empty, can't poll");
        Type temp = heapArray[0];
        swap(0,heapSize-1);
        heapSize -= 1;
        bubbleDown(0);
        return temp;
    }
    public boolean contains(Type ele)
    {
        for(Type var: heapArray)
            if(var.equals(ele)) return true;
        return false;
    }
    public void add(Type ele) throws Exception
    {
        heapSize += 1;
        if (heapSize>heapCapacity) throw new Exception("PQ can't accomodate this element, it's already full");
        heapArray[heapSize-1] = ele;
        bubbleUp(heapSize-1);
    }
    private void bubbleUp(int index)
    {
        while(index!=0 && compareTo(heapArray[parent(index)],heapArray[index]) > 0)
        {
            swap(parent(index),index);
            index = parent(index);
        }
    }
    private void bubbleDown(int index)
    {
        int smallest = index;
        if (left(index)<heapSize && compareTo(heapArray[smallest], heapArray[ left(index)]) > 0 ) smallest = left(index);
        if (right(index)<heapSize && compareTo(heapArray[smallest],heapArray[ right(index)]) > 0 ) smallest = right(index);
        if(smallest!=index)
        {
            swap(index,smallest);       
            bubbleDown(smallest);
        }
    }
    public boolean remove(Type ele) throws Exception
    {
        if(isEmpty()) throw new Exception("Can't remove anything, PQ already empty");
        for(int i = 0;i < heapSize; i++)
        {
            if (heapArray[i].equals(ele))
            {
                removeAt(i);
                return true;
            }
        }
        return false;
    }
    private Type removeAt(int index)
    {
        Type temp = heapArray[index];
        heapArray[index] = null; //Deleting
        swap(index,heapSize - 1);
        heapSize -= 1;
        bubbleDown(index);
        bubbleUp(index); //Either one is needed depending on the case, just calling both instead of checking for which is needed
        return temp;
    }
    private int parent(int index)
    {
        return index%2==0?index/2-1:index/2;
    }
    private int left(int index)
    {
        return 2*index+1;
    }
    private int right(int index)
    {
        return 2*index+2;
    }
    private void swap(int index1, int index2)
    {
        Type temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }
    @Override
    public String toString()
    {
        return Arrays.toString(heapArray);
    }
}
class Driver 
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer arr[] = {3,2,65,12,-4,9,0,34,123,12,0};
        MinPQ<Integer> minpq = new MinPQ<>(arr,arr.length, arr.length+6);
        System.out.println(minpq.toString());
        System.out.println("Add 6 elements to the PQ");
        for(int i=0;i<6;i++) { minpq.add(Integer.parseInt(in.readLine())); System.out.println(minpq.peek()); System.out.println(minpq.toString());}
        System.out.println("What do you want to remove?");
        minpq.remove(Integer.parseInt(in.readLine()));
        System.out.println(minpq.toString());
        minpq.poll();
        System.out.println(minpq.toString());
    }
}
