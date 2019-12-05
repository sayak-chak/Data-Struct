import java.io.*;
import java.util.Arrays;
class MinHeap
{
    /**
     * Implementing a simple binary minheap (array based). Not using geenrics to keep it short and quick, can be extended easily with generics
     * So, Min-heap property: arr[parent] <= arr[left] && arr[parent]<= arr[right]
     * parent <= its children
     * -----------------------------------------------------------------------------------------------------------------------
     * 1. int arr[] = the array to be converted to heap
     * 2. int capacity = initial size of the array
     * 3. int size = heapsize at any instant
     * 4. parent(index), left(index), right(index)
     * 5. minHeapify(index) = Assumes the trees rooted at left(index) and right(index) satisfies heap property but index might not => trickle down to correct pos
     * 6. buildMinHeap() = builds min heap from the given array by calling minHeapify from capaity/2 to 1 (sloppy index)
     * ------------------------------------------------------------------------------------------------------------------------
     */
    private int arr[], size, capacity;
    MinHeap()
    {
        arr = null; size = capacity = 0;
    }
    MinHeap(int A[], int S, int C)
    {
        arr = A; size = S; capacity = C;
    }
    public int parent(int index)
    {
        return index/2 - 1;
    }
    public int left(int index)
    {
        return 2*index+1;
    }
    public int right(int index)
    {
        return 2*index+2;
    }
    public void minHeapify(int index)
    {
        int temp;
        int smallest = index;
        if (left(index) < size && arr[smallest]>arr[left(index)]) smallest = left(index);
        if(right(index) < size && arr[smallest]>arr[right(index)]) smallest = right(index);
        if (smallest != index)
        {
            temp = arr[smallest];
            arr[smallest] = arr[index];
            arr[index] = temp;
            minHeapify(smallest);
        }
    }
    public void buildMinHeap()
    {
        for(int i = size/2;i>=0;i--) minHeapify(i);  
    }
    public void heapSort()
    {// just testing if this works properly
        int last = capacity - 1, temp = 0;
        buildMinHeap();
        while(size!=0)
        {
            temp = arr[last];
            arr[last--] = arr[0];
            arr[0] = temp;
            size -= 1;
            minHeapify(0);
        }
    }
}
class Driver
{
    public static void main(String[] args) throws Exception 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int arr[] = {2,3,123,5,324,23,-1,0,3};
        System.out.println(Arrays.toString(arr));
        MinHeap obj = new MinHeap(arr, arr.length, arr.length);
        obj.heapSort();
        System.out.println(Arrays.toString(arr));
    }
}
