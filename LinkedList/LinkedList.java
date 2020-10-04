package LinkedList;

import java.util.*;

public class LinkedList <E> implements ListI<E>, Iterable<E>{

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class Node<E> {
        E data;
        Node<E> next;

        public Node(E obj) {
            data = obj;
            next = null;

        }
    }




    private Node<E> head;
    private Node<E> tail;
    private int currentSize;


    public void LinkedList(){
        head = null;
        tail = null;
        currentSize= 0;
    }




    public void addFirst(E obj){
        Node<E> newNode = new Node<E> (obj);
        if(head == null){
            head = newNode;
            tail = newNode;

        }else {
            newNode.next = head;
            head = newNode;
        }
        currentSize++;
    }


    public void addLast(E obj) {
        Node<E> node = new Node<E>(obj);
        if (head == null) {
          head = tail = node;
          currentSize++;

        }

        tail.next = node;
        tail = node;
        currentSize++;


    }


    public E removeFirst(){
        if(head == null){
            System.out.println("Here");
            return null;
        }

        E tmp = head.data;

        if(head == tail){
            head = tail = null;
        }else{
            head = head.next;
        }
        currentSize--;
        return tmp;

    }


    public E removeLast(){
        if(head == null){

            return null;
        }

        if(head == tail) {

            return removeFirst();
        }

        Node<E> current = head, previous = null;

        while(current != tail) {

            previous = current;
//            System.out.println(current);
//            System.out.println(current.data);
            current = current.next;
        }
        System.out.println(current.data);

        previous.next = null;
        tail = previous;
        currentSize--;


        return current.data;

    }


    public E removeItem(E obj){
        Node<E> current = head;
        Node<E> previous = null;

        while(current != null){
            if(((Comparable<E>) current.data).compareTo(obj) == 0){
                if(current == head) return removeFirst();
                if(current == tail) return removeLast();
                currentSize--;
                previous.next = current.next;
                return  current.data;
            }
            previous = current;
            current= current.next;

        }
        return null;
    }


    public boolean contains(E obj){
        Node<E> current = head , previous = null;
        while(current != null){
            if(((Comparable<E>) current.data).compareTo(obj) == 0) {
                return true;
            }
            current = current.next;

        }
        return false;
    }


    public E peekFirst(){
        if(head == null){
            return null;

        }
        return head.data;

    }


    public E peekLast(){
        if (tail == null){
            return null;
        }
        return tail.data;
    }

//    public Node<E> getTail() {
//        return tail;
//    }
//
//    public Node<E> getHead() {
//        return head;
//    }

    public int size() {
        return currentSize;
    }


    class IteratorHelper implements Iterator<E>{

        Node<E> index;

        public IteratorHelper() {
            this.index = head;
        }

        @Override
        public boolean hasNext() {
            return (index != null);
        }

        @Override
        public E next() {
            if(!hasNext()) return (E) new NoSuchElementException();
            E val = index.data;
            index = index.next;
            return val;
        }


    }




}




