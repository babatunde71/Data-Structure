package Heap;

import Hash.Hash;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinaryHeap<E>{
    E[] array;
    int lastPosition = -1;

    class Node<E> implements Comparable<E> {
        E obj;

        public Node(E obj) {
            this.obj  = obj;
        }



        @Override
        public int compareTo(E obj2) {
            return (((Comparable<E>) obj2).compareTo(this.obj));
        }
    }

    public BinaryHeap(int size){
        array = (E[]) new Object[size];
    }

    public void add(E obj){
        array[++lastPosition] = obj;
        trickleUp(lastPosition);

    }

    public void swap(int from, int to){
        E tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;

    }

    public void trickleUp(int position){
        //System.out.println(position);
        if(position == 0) {
            return;
        }


        int parent =(int) Math.floor((position-1)/2);
//        System.out.println(parent);


        if((((Comparable<E>) array[position]).compareTo(array[parent])) > 0) {
            swap(position,parent);
            trickleUp(parent);
        }

    }


    public E remove(){
        E tmp = array[0];
        swap(0,lastPosition--);
        trickleDown(0);
        return tmp;
    }


    public void trickleDown(int parent){
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if(((Comparable<E>) array[parent]).compareTo(array[left]) > 0 ){
            swap(parent,left);
            return;
        }
        if(((Comparable<E>) array[parent]).compareTo(array[right]) > 0 ){
            swap(parent,right);
            return;
        }

        if(left >= lastPosition || right >= lastPosition){
            return;
        }

        if(    ((Comparable<E>) array[left]).compareTo(array[right]) > 0       &&      ((Comparable<E>) array[parent]).compareTo(array[left]) > 0){
            swap(parent,left);
            trickleDown(left);
        }
        else if(  ((Comparable<E>) array[left]).compareTo(array[parent]) > 0 ){
            swap(parent,right);
            trickleDown(right);
        }


    }


    public E[] getArray() {
        return array;
    }
}
