package Hash;
import java.util.Iterator;

public interface HashI<K,V>  {
    public boolean add(K key,V value);
    public boolean remove(K key,V value);
    public V getValue(K key);
    public int loadFactor();
    public void resize(int newSize);


}