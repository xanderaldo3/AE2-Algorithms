import java.io.*;
import java.util.Scanner;

class Node {
    int key;

    Node left;
    Node right;

    Node(int key){
        this.key = key;
    }

    public String toString(){
        return "" + key;
    }
}

public class BinaryTree {
    Node root;

    public void add(int value){
        Node newNode = new Node(value);

        if(root == null){
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            
            while(true){
                parent = focusNode;
                if (value == focusNode.key){
                    System.out.println(value + " is already present in the set, cannot add");
                    return;
                }else if(value < focusNode.key){
                    focusNode = focusNode.left;

                    if (focusNode == null){
                        parent.left = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.right;

                    if(focusNode == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean remove(int value){

        Node focusNode = root;
        Node parent = root;

        boolean isLeft = true;
        
        while(focusNode.key != value){
            parent = focusNode;
            if(value < focusNode.key){
                isLeft = true;
                focusNode = focusNode.left;
            } else {
                isLeft = false;
                focusNode = focusNode.right;
            }
            if (focusNode == null){
                return false;
            }
        }

        if (focusNode.left == null && focusNode.right == null){
            if(focusNode == root){
                root = null;
            } else if (isLeft){
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        
        else if (focusNode.right == null){
            if(focusNode == root){
                root = focusNode.left;
            } else if (isLeft){
                parent.left = focusNode.left;
            } else {
                parent.right = focusNode.left;
            }
        }

        else if (focusNode.left == null){
            if(focusNode == root){
                root = focusNode.right;
            } else if (isLeft){
                parent.left = focusNode.right;
            } else {
                parent.right = focusNode.right;
            }
        }
        else {
            Node replacement = getReplacementNode(focusNode);

            if (focusNode == root){
                root = replacement;
            } else if (isLeft){
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            replacement.left = focusNode.left;
        }
        return true;
    }
    
    public boolean isElement(int value){
        Node focusNode = root;
        while(focusNode.key != value){
            if (value < focusNode.key){
                focusNode = focusNode.left;
            } else {
                focusNode = focusNode.right;
            }
            if (focusNode == null){
                return false;
            }
        }
        return true;
    }

    private Node getReplacementNode(Node replacedNode){
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.right;

        while(focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.left;

        }

        if (replacement != replacedNode.right){
            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;
        }
        return replacement;
    }

    public boolean setEmpty(){
        if (root == null){
            return true;
        }
        return false;
    }

    public int setSize(){
        return getSetSize(root);
    }

    private int getSetSize(Node focusNode){
        if (focusNode == null){
            return 0;
        }
        return getSetSize(focusNode.left) + getSetSize(focusNode.right) + 1;
    }


//Tree print methods

    public void inOrderTraverseTree(){
        getInOrderTraverseTree(root);
    }

    private void getInOrderTraverseTree(Node focusNode){
        
        if(focusNode !=null){
            getInOrderTraverseTree(focusNode.left);
            System.out.println(focusNode);
            getInOrderTraverseTree(focusNode.right);
        }
    }

    public void preOrderTraverseTree(Node focusNode){

        if (focusNode !=null){
            System.out.println(focusNode);
            preOrderTraverseTree(focusNode.left);
            preOrderTraverseTree(focusNode.right);
        }
    }

    public void postOrderTraverseTree(Node focusNode){

        if (focusNode !=null){
            preOrderTraverseTree(focusNode.left);
            preOrderTraverseTree(focusNode.right);
            System.out.println(focusNode);
        }
        
    }

//SET OPERATIONS

    public static BinaryTree union(BinaryTree A, BinaryTree B){
        BinaryTree result = new BinaryTree();

        fillTree(A.root, result);
        fillTree(B.root, result);

        return result;
    }

    private static void fillTree(Node node, BinaryTree result){
        if (node != null){
            result.add(node.key);
            fillTree(node.left, result);
            fillTree(node.right, result);
        }
    }

    public static BinaryTree intersect(BinaryTree A, BinaryTree B){
        BinaryTree result = new BinaryTree();
        
        findIntersect(A.root, B, result);

        return result;

    }
    
    private static void findIntersect(Node nodeA, BinaryTree B, BinaryTree result){
        if (nodeA == null){
            return; //hit end of tree / empty tree
        }

        if (B.isElement(nodeA.key)){
            result.add(nodeA.key);
        }

        findIntersect(nodeA.left, B, result);
        findIntersect(nodeA.right, B, result);
    }

    public static BinaryTree difference(BinaryTree A, BinaryTree B){
        BinaryTree result = new BinaryTree();

        findDiff(A.root, B, result);

        return result;
    }

    private static void findDiff(Node nodeA,BinaryTree B, BinaryTree result){
        if (nodeA == null){
            return;
        }

        if (!B.isElement(nodeA.key)){
            result.add(nodeA.key);
        }

        findDiff(nodeA.left, B, result);
        findDiff(nodeA.right, B, result);
    }

    public static boolean subset(BinaryTree A, BinaryTree B){
        boolean isSubset = false;

        isSubset = checkSubset(A.root, B);

        return isSubset;
    }

    private static boolean checkSubset(Node nodeA, BinaryTree B){
        if (nodeA == null){
            return true;
        }

        if (!B.isElement(nodeA.key)){
            return false;
        }
    
        return checkSubset(nodeA.left, B) && checkSubset(nodeA.right, B);
    }

    public static void main(String[] args) throws Exception {
        BinaryTree S = new BinaryTree();
        BinaryTree T = new BinaryTree(); 
        BinaryTree emptyTree = new BinaryTree();

        S.add(2);
        S.add(3);
        S.add(6);

        T.add(3);
        T.add(2);
        T.add(5);
        T.add(6);

        System.out.println("------------MAIN OPERATIONS------------");

        System.out.println("S before adding 1: ");
        S.inOrderTraverseTree();

        System.out.println("");

        System.out.println("S after adding 1: ");
        S.add(1);
        S.inOrderTraverseTree();

        System.out.println("");

        System.out.println("Try to add 1 to S again: ");
        S.add(1);

        System.out.println("");

        System.out.println("remove 1 from set S (added back after): " + S.remove(1));
        S.inOrderTraverseTree();
        S.add(1);

        System.out.println("");

        System.out.println("Check 5 is in set T: " + T.isElement(5));
        System.out.println("Check 8 is not in set S: " + S.isElement(8));

        System.out.println("");

        System.out.println("Check set 'emptyTree' is empty: " + emptyTree.setEmpty());
        System.out.println("Check set 'S' is not empty: " + S.setEmpty());
    
        System.out.println("");

        System.out.println("Check size of S: " + S.setSize());
        System.out.println("Check size of T: " + T.setSize());
        System.out.println("Check size of emptyTree: " + emptyTree.setSize());

        System.out.println("");
        System.out.println("");

        System.out.println("------------------SET THEORETICAL OPERATIONS--------------");

        System.out.println("Union of sets S and T: ");
        BinaryTree SunionT = union(S,T);
        SunionT.inOrderTraverseTree();

        System.out.println("");

        System.out.println("intersection of sets S and T");
        BinaryTree SintersectT = intersect(S,T);
        SintersectT.inOrderTraverseTree();

        System.out.println("");

        System.out.println("difference of S and T");
        BinaryTree SdiffT = difference(S,T);
        SdiffT.inOrderTraverseTree();

        System.out.println("");

        System.out.println("check S is subset of T");
        boolean SsubsetT = subset(S, T);
        System.out.println(SsubsetT);

        System.out.println("remove 1 from S and subset test again: ");
        S.remove(1);
        S.inOrderTraverseTree();
        System.out.println(subset(S,T));        
    }
}
