package Hash;

import LinkedList.*;
import java.util.Iterator;
import java.util.Objects;

public class Hash<K, V> implements HashI<K,V>, Iterable<K> {

    @Override
    public Iterator<K> iterator() {
        return new IteratorHelper<K>();
    }


//    @Override
//    public Iterator<(K)T> iterator() {
//
//        return new IteratorHelper();
//    }

    class HashElement<K, V> implements Comparable<HashElement<K, V>> {
        K key;
        V value;
        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(HashElement<K, V> h) {
            return (((Comparable<K>) h.key).compareTo(this.key));
        }


    }

    int numElements, tableSize;
    double maxLoadFactor;

    LinkedList<HashElement<K, V>> [] harray;

    public Hash(int tableSize) {
        this.tableSize = tableSize;
        harray = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];

        for (int i = 0; i < tableSize; i++) {
            harray[i] = new LinkedList<HashElement<K, V>>();
        }
        maxLoadFactor = 0.75;
        numElements = 0;
    }

    @Override
    public boolean add(K key, V value) {
        if (loadFactor() > maxLoadFactor)
            resize(tableSize * 2);
        HashElement<K, V> he = new HashElement<>(key, value);
        int hashVal = (key.hashCode() & 0X7FFFFFFF) % tableSize;
        harray[hashVal].addFirst(he);

        numElements++;
        return true;
    }

    @Override
    public boolean remove(K key, V value) {
        int hashVal = (key.hashCode() & 0X7FFFFFFF) % tableSize;
        HashElement<K, V> he = new HashElement<>(key, value);
        harray[hashVal].removeItem(he);
        numElements--;
        return true;
    }

    @Override
    public V getValue(K key) {
        int hashVal = (key.hashCode() & 0X7FFFFFFF) % tableSize;
        for (HashElement<K, V> he : harray[hashVal]) {
            if ((((Comparable<K>) key).compareTo(he.key)) == 0)
                return he.value;
        }

        return null;


    }

    @Override
    public int loadFactor() {
        return (numElements / tableSize);
    }


    @Override
    public void resize(int newSize) {
        LinkedList<HashElement<K, V>>[] new_array = (LinkedList<HashElement<K, V>>[]) new LinkedList[newSize];

        for (int i = 0; i < newSize; i++) {
            new_array[i] = new LinkedList<HashElement<K, V>>();
        }

        for (K key: this) { // Refers -keyIterator
            V val = getValue((K) key);
            HashElement<K, V> he = new HashElement<K, V>((K) key, val);
            int hashVal = (key.hashCode() & 0X7FFFFFF) % newSize;
            new_array[hashVal].addFirst(he);
        }

        harray = new_array;
        tableSize = newSize;


    }

    public void setMaxLoadFactor(double maxLoadFactor) {
        this.maxLoadFactor = maxLoadFactor;
    }

    public int getTableSize() {
        return tableSize;
    }




    class IteratorHelper<K> implements Iterator<K> {

        int position;
        K[] keys = (K[]) new Object[numElements];

        public IteratorHelper() {
            keys = (K[]) keySet();

        }

        @Override
        public boolean hasNext() {
            return (position < keys.length);
        }



        @Override
        public K next() {
            if(!hasNext())
                return null;
            return keys[position++];
        }
    }


    public K[] keySet(){
        K[] keys = (K[]) new Object[numElements] ;
        int position;
        int p = 0;
        for(int i = 0; i < tableSize; i++) {
            LinkedList<HashElement<K, V>> list = harray[i];
            for (HashElement<K, V> h : list) {

                keys[p++] = h.key;

            }
        }
        return  keys;
    }




}




