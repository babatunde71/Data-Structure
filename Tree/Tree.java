package Tree;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.function.BinaryOperator;

public class Tree<E> implements TreeI<E>, Iterable<E> {

    Node<E> root;
    int currentSize;

    public Tree() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    class Node<E> implements Comparable<Node<E>>{
        E data;
        Node<E> left,right;

        public Node(E obj) {
            this.data = obj;
            left = right = null;
        }


        @Override
        public int compareTo(Node<E> e) {
            return (((Comparable<E>) e.data).compareTo(this.data));
        }
    }

    public Tree(E data){
        add(data);
    }

    //recursive add
    private Node<E> add(E obj, Node<E> node){
        if(((Comparable <E>)obj).compareTo(node.data) > 0){
            // go to the right

            if(node.right == null){
                node.right = new Node<E> (obj);
                return null;
            }
            return add(obj, node.right);
        }

        if(node.left == null){
            node.left =  new Node<E> (obj);
            return null;
        }
        return add(obj,node.left);
    }

    public void add(E obj){
        if(root == null){
            root = new Node<E>(obj);
        }
        else{
            add(obj,root);
        }
        currentSize++;
    }
    @Override
    public boolean contains(E obj) {
        return false;
    }


    public boolean contains(E obj, Node<E> node){
        if(node == null){
            return false;
        }
        if(((Comparable<E>) obj).compareTo(node.data) == 0) {
            return true;
        }
        if(((Comparable<E>) obj).compareTo(node.data) > 0) {
            return contains(obj,node.right);
        }
        return contains(obj, node.left);
    }

    @Override
    public boolean remove(E obj) {
        System.out.println("check1");
        root = removeRecursive(root,obj);
        return true;
    }

    public Node<E> removeRecursive(Node<E> node, E obj){
        if (node == null){
            System.out.println("check6");
            return null;
        }

        if(((Comparable<E>) node.data).compareTo(obj) == 0){
            System.out.println("check2");
            System.out.println("node: " +  node.data);
            if (node.left == null && node.right == null) {
                System.out.println("check3");
                return null;
            }

            if (node.right == null) {
                System.out.println("check4");
                return node.left;
            }

            if (node.left == null) {
                System.out.println("check5");
                return node.right;
            }


            if(node.right != null && node.left != null){
                if(node == root){
                    root = null;
                    return null;

                }


                System.out.println("check");
                //Using the predessor - inorder
                replacementInOrder(node);
                tmp.right = node.right;
                node = null;

                return tmp;
            }



        }

        if(((Comparable<E>) node.data).compareTo(obj) > 0){
            node.left = removeRecursive(node.left,obj);
        }

        if(((Comparable<E>) node.data).compareTo(obj) < 0){
            node.right = removeRecursive(node.right,obj);
        }

        return node;

    }

    Node tmp;

    public void replacementInOrder(Node<E> root){
        if(root == null) return;
        replacementInOrder(root.left);
        if(tmp == null){
            tmp = root;
            return;
        }
        replacementInOrder(root.right);


    }



    public void visit(Node<E> root){
        System.out.println(root.data);
    }

    public void preorder(Node<E> root){
        if (root == null) return;
        visit(root);
        preorder(root.left);
        preorder(root.right);

    }

    public void postorder(Node<E> root){
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        visit(root);
    }

    public void inorder(Node<E> root){
        if(root == null) return;
        inorder(root.left);
        visit(root);
        inorder(root.right);
    }


    public Node<E> getRoot() {
        return root;
    }


    public Node<E> leftRotate (Node<E> node){
        Node<E> tmp = node.right;
        node.right = tmp.left;
        tmp.left =  node;
        root = tmp;
        return tmp;
    }

    public Node<E> rightRotate (Node<E> node){
        Node<E> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        root = tmp;
        return tmp;
    }


    public Node<E> rightLeftRotate(Node<E> node){
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    public Node<E> leftRightRotate(Node <E> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);

    }


}
