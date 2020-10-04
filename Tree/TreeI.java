package Tree;

import java.util.Iterator;

public interface TreeI<E>  {
    public void add(E obj);
    public boolean contains(E obj);
    public boolean remove(E obj);

}