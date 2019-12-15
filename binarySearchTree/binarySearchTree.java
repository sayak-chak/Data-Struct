import java.io.*;
import java.util.LinkedList;
class BinarySearchTree<Type extends Comparable<Type>> 
{
    private int nodeCount; Node root;
    private class Node
    {
        Type data; Node left, right;
        private Node(Type data, Node left, Node right)
        {
            this.data = data; this.left = left; this.right = right;
        }
    }
    public BinarySearchTree()
    {
        root = null; nodeCount = 0;
    }
    public boolean isEmpty()
    {
        return nodeCount==0;
    }
    public boolean add(Type element)
    {
        if(root==null) root = new Node(element, null,null);
        else
        {
            if(find(element)) return false; //not allowing duplicates
            Node trav = root, prev = null;
            while(trav != null)
            {
                prev = trav;
                if (element.compareTo(trav.data)>0) trav = trav.right;
                else trav = trav.left;
            }
            if(element.compareTo(prev.data)<0) prev.left = new Node(element, null,null);
            else prev.right = new Node(element, null, null);
        }
        nodeCount++;
        return true;
    }
    public boolean find(Type element)
    {
        Node trav = root;
        while(trav!=null)
        {
            if (element.compareTo(trav.data)==0) {return true; }
            else if (element.compareTo(trav.data)>0) trav = trav.right;
            else trav = trav.left;
        }
        return false;
    }
    public boolean remove(Type element)
    {
        if(!find(element)) return false;
        Node trav = root, prev = null;
        while(trav.data!=element)
        {
            prev = trav;
            if (element.compareTo(trav.data)>0) trav = trav.right;
            else trav = trav.left;  
        }
        remove(prev,trav);
        return true;
    }
    private void remove(Node prev, Node delete)
    {
        if(prev == null) root = null;
        else
        {
            //four cases
            //Case 1: Node is a leaf node
            if(delete.left == null && delete.right == null)
            {
                if(prev.left==delete) prev.left = null;
                else prev.right = null;
            }
            //Case 2: Node has only right BST
            else if(delete.left==null && delete.right!=null)
            {
                if(prev.left==delete) prev.left = delete.right;
                else prev.right = delete.right;
            }
            //Case 3: Node has pnly left BST
            else if(delete.left!=null && delete.right == null)
            {
                if(prev.left==delete) prev.left = delete.left;
                else prev.right = delete.left;
            }
            //Case 4: Has both left and right BST
            // => Replace del w largest val in left BST OR smallest value in right BST
            // Cuz, largest in left still 
            else 
            {
                largestInLeft(prev,delete);
                //or you can do this 
                //smallestInRight(prev,dalate);
            }
        }
        delete.left = null;
        delete.right = null;
        delete = null;
        nodeCount--;
    }
    private void largestInLeft(Node prev, Node delete)
    {
        Node largestInLeft = delete.left, prevLargestInLeft = delete;
        while(largestInLeft.right!=null) 
        {
            prevLargestInLeft = largestInLeft;
            largestInLeft = largestInLeft.right;
        }
        prevLargestInLeft.right = largestInLeft.left;
        largestInLeft.left = delete.left;
        largestInLeft.right = delete.right;
        if(prev.left==delete) prev.left = largestInLeft;
        else prev.right = largestInLeft;
    }
    public boolean contains(Type element)
    {
        return find(element);
    }
    public void traversal(int order) //1 -> preorder 2-> inorder 3-> postorder 4-> level order
    {
        if(order!=4) traversal(root, order);
        else postOrderTraversal();
    }
    private void postOrderTraversal()
    {//bfs basically
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addFirst(root);
        System.out.println();
        while(!queue.isEmpty())
        {
            if(queue.peek()!=null)
            {
                System.out.print(queue.peek().data+" ");
                queue.addLast(queue.peek().left);
                queue.addLast(queue.peek().right);
            }            
            queue.removeFirst();
        }
    }
    private void traversal(Node trav, int order)
    {
        if(trav!=null)
        {
            if(order==1)
            {
                System.out.println(trav.data+" ");
                traversal(trav.left, order);
                traversal(trav.right, order);
            }
            else if(order==2)
            {
                traversal(trav.left, order);
                System.out.println(trav.data+" ");
                traversal(trav.right, order);
            }
            else if(order==3)
            {//This one prints the array in sorted order
                traversal(trav.left, order);
                traversal(trav.right, order);
                System.out.println(trav.data+" ");
            }
        }
    }
    public int size()
    {
        return nodeCount;
    }
}
class Driver
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println("How many ele to add?");
        int n = Integer.parseInt(in.readLine());
        while(n-->0) {int var = Integer.parseInt(in.readLine()); bst.add(var); System.err.println(bst.find(var));}
        System.out.println("Empty? "+bst.isEmpty()+" Size = "+bst.size());
        bst.traversal(2);
    }
}
