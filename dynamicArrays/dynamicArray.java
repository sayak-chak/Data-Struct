
import java.io.*;
class Driver
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DynamicArray<String> arr = new DynamicArray<>();
        //Sample operations
        int n = Integer.parseInt(in.readLine());
        while(--n>=0) arr.add(in.readLine());
        System.out.println(arr.toString());
        arr.remove(3);
        System.out.println(arr.toString());
        System.out.println(arr.size());
    }
}
class DynamicArray<T>
{
    /**
     * Basic idea is that
     * 1. Create a static array of size n
     * 2. If n ele are filled up and you need to fill n+1 th ele, then create a new array & copy. 
     * (This is implementing a dynamic array with a static array, there are other ways to implement it as well)
     */
    private T arr[];
    private int size; //Number of ele in the arr
    private int capacity; //Max no of ele it can support
    DynamicArray() { this(10); }
    DynamicArray(int n)
    {
        capacity = n; size = 0;
        arr = (T[])new Object[capacity];
    }
    int size() { return size; }
    boolean isEmpty(){ return size==0; }
    void remove(T obj)
    {
        int i; boolean flag = false;
        for (i=0;i<size;i++)
        {
            if (arr[i].equals(obj)) flag = true;
            if(flag) 
                if(i!=size-1) arr[i] = arr[i+1];
        }
        arr[size-1] = null; 
        size -= 1;
    }
    void remove(int index)
    {
        int i; 
        for(i=index;i<size-1;i++) arr[i] = arr[i+1];
        size-=1;
    }
    void add(T obj)
    {
        int i=0;
        if (size == capacity)
        {
            capacity = (capacity==0)?1:capacity*2;
            T temp[] = (T[]) new Object[capacity];
            while(i<size) temp[i] = arr[i];
            temp[i] = obj;
            arr = temp;
        }
        else { arr[size] = obj; }
        size+=1;
    }
    @Override
    public String toString() 
    {
        if (size == 0) return "[ ]";
        else 
        {
          StringBuilder sb = new StringBuilder("[ ");
          for (int i = 0; i < size-1; i++) sb.append(arr[i] + ", ");
          return sb.append(arr[size-1]+" ]").toString();
        }
    }
}
