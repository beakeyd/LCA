

import java.util.ArrayList;


public class LowestCommonAncestor<T extends Comparable<T>> {



    public T getLCA(BinaryTree<T> bt, T a, T b){

        ArrayList<T> pathA = new ArrayList<T>(); // path to key a
        ArrayList<T> pathB = new ArrayList<T>(); // path to key b
        getPath( bt,a,pathA);
        getPath(bt,b,pathB);
        //while the two path are the same
        int i;
        for(  i=0;i<pathA.size()&&i<pathB.size();i++){
            if(pathA.get(i)!=pathB.get(i)){
                break;
            }
        }
        //if a common key was found
        if(i>0) {
            return pathA.get(i - 1);
        }
        //no key found
        else{
            return null;
        }
    }
    public int[] getLCA(Digraph g, int a, int b){
        int greaterNode,smallerNode,i;
       Topological t=new Topological(g);
       ArrayList<ArrayList<Integer>> bag=g.getBag();
        ArrayList<Integer> tophOrder=t.listOrder();
        //getting which node is closer to the first item in the tophological order
        //called greaterNode from here on
        int returnArray[]=new int[2];
        returnArray[0]=-1;
        i=0;
        while(true){
            if(i==tophOrder.size()){

                return returnArray;
            }
            if(tophOrder.get(i)==a){
                greaterNode=a;
                smallerNode=b;
                break;
            }
            else if(tophOrder.get(i)==b){
                greaterNode=b;
                smallerNode=a;
                break;
            }
            i++;
        }

        boolean pathExists=false;
        while(true){
            //if a path exists then the greaterNode is the lca
            pathExists=pathExist(g,greaterNode,smallerNode,bag,tophOrder);

            if(!pathExists){
                //else move find the first previous node in the tophOrder which has a path to the current greaterNode
                greaterNode=findNextGreaterNode(g,tophOrder,greaterNode);

                //If no nodes like this exist, then there is no lca
                if(greaterNode==-1){
                    return returnArray;
                }
            }
            else {
                returnArray[0]=1;
                returnArray[1]=greaterNode;
                return returnArray;
            }

        }
    }
    /*
    If a path exists from the root to the node
     */
    private boolean pathExist(Digraph g, int root, int node,ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tophOrder){
        int indexToph=tophOrder.indexOf(root);

        //This list holds the edges off of the root
        ArrayList<Integer> local=list.get(tophOrder.get(indexToph));
        int i=0;
        int current;
        //While there are still edges off the root
        while(i<local.size()) {
            //edge of local(i)
            current=local.get(i);

            i++;
            //an edge case where the path is directly from the root to the node
            if (current == node) {

                return true;
            }
            //if a path exists from the edge off root to other node, if there is one then the lca is the root
            boolean pathExist=pathExistTail(g, current, node, list, tophOrder);
            if(pathExist){

                return true;
            }
        }
        return false;

    }
    /*
    Recursive tail for above function
     */
    private boolean pathExistTail(Digraph g, int root, int node, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> tophOrder){
        int indexToph=tophOrder.indexOf(root);

        //This list holds the edges off of the root
        ArrayList<Integer> local=list.get(tophOrder.get(indexToph));
        int i=0;
        //base case if there is no path, we have gone past the node we are looking for in the tophOrder
        if(tophOrder.indexOf(root)>tophOrder.indexOf(node)){

            return false;
        }

        int current;

        while(i<local.size()) {
            current=local.get(i);
            i++;
            //base case if path is found
            if (current == node) {
                return true;
            }
            //see if this node has a path to the node we are looking for
            boolean pathExist=pathExistTail(g, current, node, list, tophOrder);
            if(pathExist){
                return true;
            }

        }
        return false;
    }
    /*
    Get the next lowest node in tophOrder which has a path to the current node
     */
    private int findNextGreaterNode(Digraph g, ArrayList<Integer> tophOrder,int greaterNode){
        ArrayList<ArrayList<Integer>> list=g.getBag();
        int indexToph=tophOrder.indexOf(greaterNode);
        indexToph--;
        if(indexToph<0){
            return -1;
        }
        int currentIndex=tophOrder.get(indexToph);

         while( currentIndex>=0){

             ArrayList<Integer> local=list.get(currentIndex);
           int j=0;
            int current;
            while(j<local.size()){
                current=local.get(j);
                if(current==greaterNode){
                    return currentIndex;
                }
                    j++;


            }
            indexToph--;
             if(indexToph<0){
                 return -1;
             }
             currentIndex=tophOrder.get(indexToph);
        }
        return -1;
    }
    private void getPath(BinaryTree<T> bt, T a,ArrayList<T> path){

        boolean found=getPath(bt.root, a, path);

    }

    private boolean getPath(Node node, T a,ArrayList<T> path){
        //no path exists
        if(node==null){
            return false;
        }
        path.add((T) node.key);
        if(a==node.key){
            return true;
        }
        if(node.left!=null&&getPath(node.left,a,path)){
            return true;
        }
        if(node.right!=null&&getPath(node.right,a,path)){
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }


}