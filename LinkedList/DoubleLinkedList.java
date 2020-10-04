package LinkedList;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements ListI<E>, Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E obj) {
            data = obj;
            next = null;
            prev = null;

        }
    }


    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }






    @Override
    public void addFirst(E obj) {
       Node<E> newNode = new Node<E>(obj);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else {
            head.prev = newNode;
            newNode.next = head;
            tail = head;
            head = newNode;
        }
        currentSize++;
    }

    @Override
    public void addLast(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if (head == null) {
            head = tail = newNode;

            currentSize++;
            return;
        }else {

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            currentSize++;
        }


    }

    @Override
    public E removeFirst() {
        if(head == null){
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

    @Override
    public E removeLast() {
        if(head == null){
            return null;
        }

        if(head == tail) {

            return removeFirst();
        }


        Node<E> tmp = tail;


        tail = tail.prev;
        tmp.prev.next = null;
        currentSize--;


        return tmp.data;



    }

    @Override
    public E removeItem(E obj) {
        Node<E> current = head;
        Node<E> previous = null;

        while(current != null){
            if(((Comparable<E>) current.data).compareTo(obj) == 0){
                if(current == head) return removeFirst();
                if(current == tail) return removeLast();
                currentSize--;
                previous.next = current.next;
                current.next.prev = previous;
               // previous.prev = current.prev;
                return  current.data;
            }
            previous = current;
            current= current.next;

        }
        return null;
    }

    @Override
    public boolean contains(E obj) {
        Node<E> current = head , previous = null;
        while(current != null){
            if(((Comparable<E>) current.data).compareTo(obj) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public E peekFirst() {
        if(head == null){
            return null;

        }
        return head.data;
    }

    @Override
    public E peekLast() {
        if (tail == null){
            return null;
        }
        return tail.data;
    }


    class IteratorHelper implements Iterator<E> {

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
            if (!hasNext()) return (E) new NoSuchElementException();
            E val = index.data;
            index = index.next;
            return val;
        }


    }

    public int size() {
        return currentSize;
    }

//    public Node<E> getHead() {
//        return head;
//    }
//
//    public Node<E> getTail() {
//        return tail;
//    }
}
