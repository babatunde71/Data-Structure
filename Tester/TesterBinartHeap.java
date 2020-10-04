package Tester;

import Heap.BinaryHeap;

import java.util.Arrays;

public class TesterBinartHeap {
    static BinaryHeap<Integer>  heap = new BinaryHeap<Integer>(4);
    static int size = 4;
    public static void main(String[] args) {
        for(int i = 1; i< size+1; i++){
            heap.add(i);
            System.out.println(Arrays.toString(heap.getArray()));
        }
        for(int i = 0; i < size; i++){
            heap.remove();
        }


    }
}
