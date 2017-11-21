//requires junit.jar to be in project structure
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LowestCommonAncestorTest<T extends Comparable<T>> {
    /*
    Testing when the tree has no nodes
     */
    @Test
    public void emptyTree() throws Exception {
        BinaryTree<Integer> bt = new BinaryTree<>();
        LowestCommonAncestor lca = new LowestCommonAncestor();
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 1,2));

    }
    /*
    The very basic expected functions of lowest common ancestor
     */
    @Test
    public void basicFunction() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();

        bt.root=new Node(5);
        bt.insert(bt.root, 3);
        bt.insert(bt.root, 4);
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 4,3));
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 2);
       assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 1,2));
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 4, 2));
        bt.insert(bt.root, 7);
        bt.insert(bt.root, 10);
         assertEquals("Error, expected lca to be 4",4, lca.getLCA(bt, 10,7));

    }
    /*
    When the binary tree has only one node, the root
     */
    @Test
    public void loneRoot() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();
        bt.root=new Node(4);
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 4,5));
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 4,null));

    }
    /*
    Testing the lca of a parent and node
     */
    @Test
    public void oneLeaf() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();
        bt.root=new Node(5);
        bt.insert(bt.root, 3);
        bt.insert(bt.root, 4);
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 3,5));
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 4,5));
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 2);
         assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 1,3));
         assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 2, 3));
        bt.insert(bt.root, 7);
         assertEquals("Error, expected lca to be 4",4, lca.getLCA(bt, 4,7));

    }
    /*
    Testing when the tree doesnt contain at least one of the elements asked for
     */
    @Test
    public void elementsNotInTree() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();

        bt.root=new Node(5);
        bt.insert(bt.root, 3);
        bt.insert(bt.root, 4);
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 4,3));
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 2);
        assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 1,2));
        assertEquals("Error, expected lca to be 5",5, lca.getLCA(bt, 4, 2));
        bt.insert(bt.root, 7);
        bt.insert(bt.root, 10);
        assertEquals("Error, expected lca to be 4",4, lca.getLCA(bt, 10,7));
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 11,12));
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 7,12));
         assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 11,4));
        assertEquals("Error, expected lca to be null",null, lca.getLCA(bt, 5,22));


    }
    /*
   Testing if the order of params affect lca
    */
    @Test
    public void orderTest() throws Exception {
        BinaryTree<Integer> bt = new BinaryTree<>();
        LowestCommonAncestor lca = new LowestCommonAncestor();
        bt.root = new Node(2);
        bt.insert(bt.root, 8);
        bt.insert(bt.root, 6);
        assertEquals("Error, expected lca to be 2", 2, lca.getLCA(bt, 6, 8));
        assertEquals("Error, expected lca to be 2", 2, lca.getLCA(bt, 8, 6));
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 3);
        assertEquals("Error, expected lca to be 8", 8, lca.getLCA(bt, 1, 3));
        assertEquals("Error, expected lca to be 8", 8, lca.getLCA(bt, 3, 1));
        bt.insert(bt.root, 7);

        assertEquals("Error, expected lca to be 4", 6, lca.getLCA(bt, 6, 7));
        assertEquals("Error, expected lca to be 4", 6, lca.getLCA(bt, 7, 6));

    }
    /*
   Testing when the tree for different object than Integer
    */
    @Test
    public void stringTest() throws Exception {
        BinaryTree<String> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();
        bt.root=new Node("hello");
        bt.insert(bt.root, "world");
        bt.insert(bt.root, "!");
        assertEquals("Error, expected lca to be hello","hello", lca.getLCA(bt, "world","!"));
        bt.insert(bt.root, "console");

        assertEquals("Error, expected lca to be world","world", lca.getLCA(bt, "console","world"));

    }

    /*
   Testing when the tree contains non unique values
   Basic assumption of Binary Tree is that tree cannot contain duplicates
    */
    @Test
    public void nonUnique() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();
        bt.root=new Node(3);
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 2);
        bt.insert(bt.root, 3);
        assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 1,3));
        bt.insert(bt.root,2);
        assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 1,2));
        bt.insert(bt.root, 1);
        bt.insert(bt.root, 5);
        bt.insert(bt.root, 4);
        bt.insert(bt.root, 8);
        bt.insert(bt.root, 6);
        bt.insert(bt.root, 8);
        assertEquals("Error, expected lca to be 3",3, lca.getLCA(bt, 8,5));
        bt.insert(bt.root, 1);
        assertEquals("Error, expected lca to be 1",1, lca.getLCA(bt, 1,4));
    }
    /*
  Testing for deep trees, making sure that the given lca, is the right lca
                                        1
                                     2/   \3
                                  4/  \5  6/    \7
                              8/\9 10/\11 12/\13 14/\15
                            16/
   */
    @Test
    public void deepTreeTest() throws Exception {
        BinaryTree<Integer> bt=new BinaryTree<>();
        LowestCommonAncestor lca=new LowestCommonAncestor();
        bt.root=new Node(1);
        bt.insert(bt.root, 2);
        bt.insert(bt.root, 3);
        bt.insert(bt.root, 4);
        bt.insert(bt.root,5);
        bt.insert(bt.root, 6);
        bt.insert(bt.root, 7);
        bt.insert(bt.root, 8);
        bt.insert(bt.root, 9);
        bt.insert(bt.root, 10);
        bt.insert(bt.root,11);
        bt.insert(bt.root, 12);
        bt.insert(bt.root, 13);
        bt.insert(bt.root, 14);
        bt.insert(bt.root, 15);
        bt.insert(bt.root, 16);


        assertEquals("Error, expected lca to be 4",1, lca.getLCA(bt, 15,16));
        assertEquals("Error, expected lca to be 1",1, lca.getLCA(bt, 1,2));
        assertEquals("Error, expected lca to be 1",1, lca.getLCA(bt, 8,14));
        assertEquals("Error, expected lca to be 1",2, lca.getLCA(bt, 10,16));
        assertEquals("Error, expected lca to be 4",4, lca.getLCA(bt, 9,8));
        assertEquals("Error, expected lca to be 1",8, lca.getLCA(bt, 16,8));
        assertEquals("Error, expected lca to be 1",1, lca.getLCA(bt, 12,11));
        assertEquals("Error, expected lca to be 1",3, lca.getLCA(bt, 14,13));



    }
    /*
        Testing lca method with an empty dag
     */
    @Test
    public void emptyDAG() throws Exception {
        Digraph g=new Digraph();
        LowestCommonAncestor lca = new LowestCommonAncestor();
        int answerArray[]={-1,0};
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 1,2));

    }
    /*
    The very basic expected functions of lowest common ancestor
     */
    @Test
    public void basicDigraphLCAFunction() throws Exception {
        In in=new In("sampleDAG.txt");
        In in2=new In("mediumDAG.txt");
        Digraph g=new Digraph(in);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        int answerArray[]={1,0};
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 1,5));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 2,6));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 2,5));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 1,6));

        Digraph g2=new Digraph(in2);
        LowestCommonAncestor lca2 = new LowestCommonAncestor();
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca2.getLCA(g2, 2,3));
        answerArray[1]=3;
        assertArrayEquals("Error, expected lca to be 3",answerArray, lca2.getLCA(g2, 5,6));
        answerArray[1]=1;
        assertArrayEquals("Error, expected lca to be 1",answerArray, lca2.getLCA(g2, 4,3));

    }
    /*
    When the binary tree has only one node, the root
     */
    @Test
    public void loneRootDAG() throws Exception {
        Digraph g=new Digraph(1);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        int answerArray[]={-1,0};
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 1,5));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 1,2));
    }
    /*
    Testing the lca of a parent and node
     */
    @Test
    public void lcaIsNodeDAG() throws Exception {
        In in=new In("sampleDAG.txt");
        In in2=new In("mediumDAG.txt");
        int answerArray[]={1,5};
        Digraph g=new Digraph(in);
        Digraph g2=new Digraph(in2);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor lca2 = new LowestCommonAncestor();
        assertArrayEquals("Error, expected lca to be 5",answerArray, lca.getLCA(g, 6,5));
            answerArray[1]=1;
        assertArrayEquals("Error, expected lca to be 1",answerArray, lca.getLCA(g, 1,2));
        assertArrayEquals("Error, expected lca to be 1",answerArray, lca.getLCA(g, 1,3));
          answerArray[1]=6;
        assertArrayEquals("Error, expected lca to be 6",answerArray, lca.getLCA(g, 6,4));
           answerArray[1]=5;
        assertArrayEquals("Error, expected lca to be 5",answerArray, lca.getLCA(g, 4,5));

        answerArray[1]=0;
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca2.getLCA(g2, 3,0));
        answerArray[1]=1;
        assertArrayEquals("Error, expected lca to be 1",answerArray, lca2.getLCA(g2, 1,3));
        answerArray[1]=3;
        assertArrayEquals("Error, expected lca to be 3",answerArray, lca2.getLCA(g2, 3,6));
        answerArray[1]=6;
        assertArrayEquals("Error, expected lca to be 6",answerArray, lca2.getLCA(g2, 6,8));
        answerArray[1]=7;
        assertArrayEquals("Error, expected lca to be 7",answerArray, lca2.getLCA(g2, 8,7));


    }

    /*
   Testing when the DAG doesn't contain at least one of the elements asked for
    */
    @Test
    public void elementsNotInDAG() throws Exception {
        In in=new In("sampleDAG.txt");
        In in2=new In("mediumDAG.txt");
        Digraph g=new Digraph(in);
        Digraph g2=new Digraph(in2);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor lca2 = new LowestCommonAncestor();
        int answerArray[]={-1,0};
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 11,22));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 6,2222));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 3,22));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca.getLCA(g, 55,2));


      assertArrayEquals("Error, expected lca to be -1",answerArray, lca2.getLCA(g2, 9,13));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca2.getLCA(g2, 20,123));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca2.getLCA(g2, 5,30));
        assertArrayEquals("Error, expected lca to be -1",answerArray, lca2.getLCA(g2, 11,8));



    }
    /*
   Testing if the order of params affect lca
    */
    @Test
    public void orderTestDAG() throws Exception {
        In in=new In("sampleDAG.txt");
        In in2=new In("mediumDAG.txt");
        Digraph g=new Digraph(in);
        Digraph g2=new Digraph(in2);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        LowestCommonAncestor lca2 = new LowestCommonAncestor();
        int answerArray[]={1,0};
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 1,5));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 1,5));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 2,5));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca.getLCA(g, 5,2));
        answerArray[1]=6;
        assertArrayEquals("Error, expected lca to be 6",answerArray, lca.getLCA(g, 6,4));
        assertArrayEquals("Error, expected lca to be 6",answerArray, lca.getLCA(g, 4,6));

        answerArray[1]=0;
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca2.getLCA(g2, 2,3));
        assertArrayEquals("Error, expected lca to be 0",answerArray, lca2.getLCA(g2, 3,2));
        answerArray[1]=3;
        assertArrayEquals("Error, expected lca to be 3",answerArray, lca2.getLCA(g2, 5,6));
        assertArrayEquals("Error, expected lca to be 3",answerArray, lca2.getLCA(g2, 6,5));



    }
    /*
    An assumption with this DAG was that all values in it had to be unique
    Therefore there is no test to see if the DAG LCA solution works with non unique values
    All vertices must be positive also
     */



}