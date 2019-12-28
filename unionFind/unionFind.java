import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
class UnionFind<Type>
{
    private ArrayList <Node> indexArr;
    private int noOfGroups, capacity, size;
    private class Node 
    {
        int currentIndex, parentIndex, size;
        Type data;
        private Node(int currentIndex, int parentIndex, Type data)
        {
            this.currentIndex = currentIndex; this.parentIndex = parentIndex; this.data = data; 
            this.size = 1; //size of this group
        }
    }
    public UnionFind(int CAPACITY)
    {
        capacity = CAPACITY; size = 0; noOfGroups = 0;
        indexArr = new ArrayList<>(capacity);
    }
    public void add(Type element)
    {
        if(size==capacity) throw new OutOfMemoryError("Union find can't accomodate new ele");
        indexArr.add(new Node(size,size,element)); //initially every element is its own root
        size++;
        noOfGroups++;
    }
    public int noOfGroups()
    {
        return noOfGroups;
    }
    public int size()
    {
        return size;
    }
    public int find(int index)
    {//finds root index of this index
        int rootIndex = index, travIndex = index, tempIndex;
        //goto parent till you reach root => the node pointing to itself is root
        while( indexArr.get(rootIndex).parentIndex != indexArr.get(rootIndex).currentIndex ) rootIndex = indexArr.get(rootIndex).parentIndex;
        //Now I found root but to get the amortized TC of alpha n path compression is necessary
        //Path compression
        while(travIndex!=rootIndex)
        {
            tempIndex = indexArr.get(travIndex).parentIndex;
            indexArr.get(travIndex).parentIndex = rootIndex;
            travIndex = tempIndex;
        }
        return rootIndex;
    }
    public boolean Connected(int index1, int index2)
    {
        return find(index1)==find(index2);
    }
    public void unify(int index1, int index2)
    {
        int rootIndex1 = find(index1), rootIndex2 = find(index2);
        if(rootIndex1!=rootIndex2)
        {//=> they belong to different groups
            if(indexArr.get(rootIndex1).size>indexArr.get(rootIndex2).size)
            {
                indexArr.get(rootIndex2).parentIndex = rootIndex1;
                indexArr.get(rootIndex1).size += indexArr.get(rootIndex2).size;
            }
            else 
            {
                indexArr.get(rootIndex1).parentIndex = rootIndex2;
                indexArr.get(rootIndex2).size += indexArr.get(rootIndex1).size;
            }
            noOfGroups--;
        }
    }
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder("[ ");
        for(Node var: indexArr)
        {
            //out.append("{ current index = "+var.currentIndex+" | parent index = "+var.parentIndex+" | data = "+var.data.toString()+" } ");
            out.append("{"+var.currentIndex+"->"+var.parentIndex+"}");
        }
        return out.append(" ]").toString();
    }
}    
class Driver
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        UnionFind<String> uf = new UnionFind<>(100);
        System.out.println("Add 5 elements");
        for(int i=0;i<5;i++) uf.add(in.readLine());
        System.out.println("Groups = " +uf.noOfGroups());
        System.out.println("Size =" +uf.size());
        System.out.println(uf.toString());
        System.out.println(uf.size());
        System.out.println("Unifying index 1 and 4");
        uf.unify(1,4);
        System.out.println("Unifying index 2 and 4");
        uf.unify(2,4);
        System.out.println("Groups = " +uf.noOfGroups());
        System.out.println("Size =" +uf.size());
        System.out.println(uf.toString());
        System.out.println("Unifying 3 and 0");
        uf.unify(3,0);
        System.out.println("Groups = " +uf.noOfGroups());
        System.out.println("Size =" +uf.size());
        System.out.println(uf.toString());
        System.out.println("Unifying 1 & 3");
        uf.unify(1,3);
        System.out.println("Groups = " +uf.noOfGroups());
        System.out.println("Size =" +uf.size());
        System.out.println(uf.toString());
    }
}
