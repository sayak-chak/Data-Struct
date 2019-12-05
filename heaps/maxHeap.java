import java.io.*;
import java.util.Arrays;
class MaxHeap
{
    /**
     * Implementing a simple binary maxheap (array based). Not using geenrics to keep it short and quick, can be extended easily with generics
     * So, Max-heap property: arr[parent] >= arr[left] && arr[parent] >= arr[right]
     * parent >= its children [MaxHeap property]
     * -----------------------------------------------------------------------------------------------------------------------
     * 1. int arr[] = the array to be converted to heap
     * 2. int capacity = initial size of the array
     * 3. int size = heapsize at any instant
     * 4. parent(index), left(index), right(index)
     * 5. maxHeapify(index) = Assumes the trees rooted at left(index) and right(index) satisfies heap property but index might not => trickle down to correct pos
     * 6. buildMaxHeap() = builds max heap from the given array by calling maxHeapify from capaity/2 to 1 (sloppy index)
     * ------------------------------------------------------------------------------------------------------------------------
     */
    private int arr[], size, capacity;
    MaxHeap()
    {
        arr = null; size = capacity = 0;
    }
    MaxHeap(int A[], int S, int C)
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
    public void maxHeapify(int index)
    {
        int temp;
        int largest = index;
        if (left(index) < size && arr[largest] < arr[left(index)]) largest = left(index);
        if(right(index) < size && arr[largest] < arr[right(index)]) largest = right(index);
        if (largest != index)
        {
            temp = arr[largest];
            arr[largest] = arr[index];
            arr[index] = temp;
            maxHeapify(largest);
        }
    }
    public void buildMaxHeap()
    {
        for(int i = size/2;i>=0;i--) maxHeapify(i);  
    }
    public void heapSort()
    {// just testing if this works properly
        int last = capacity - 1, temp = 0;
        buildMaxHeap();
        while(size!=0)
        {
            temp = arr[last];
            arr[last--] = arr[0];
            arr[0] = temp;
            size -= 1;
            maxHeapify(0);
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
        MaxHeap obj = new MaxHeap(arr, arr.length, arr.length);
        obj.heapSort();
        System.out.println(Arrays.toString(arr));
    }
}
