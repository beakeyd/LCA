import java.util.LinkedList;
import java.util.Queue;

/* Class containing left and right child of current
   node and key value*/
class Node<T extends Comparable<T>>
{
    T key;
    Node left, right;

    public Node(T item)
    {
        key = item;
        left = right = null;
    }
}

// A Java program to introduce Binary Tree
public class BinaryTree<T extends Comparable<T>>
{
    // Root of Binary Tree
    Node root;


    BinaryTree()
    {
        root = null;
    }



    /*function to insert element in binary tree
     * taken from http://www.geeksforgeeks.org/insertion-binary-tree/
     */
    public void insert(Node temp, T key)
    {
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);

        // Do level order traversal until we find
        // an empty place.
        while (!q.isEmpty()&&temp.key!=key) {
            temp = q.peek();


            q.remove();
            if (temp.left == null) {
                temp.left = new Node(key);
                break;
            } else if(temp.left.key!=key)
                q.add(temp.left);
            else
                break;

            if (temp.right == null) {
                temp.right = new Node(key);
                break;
            } else if(temp.right.key!=key)
                q.add(temp.right);
            else
                break;


        }
    }


}