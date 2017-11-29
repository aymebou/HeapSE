package heap;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public interface Heap<E> extends Iterable<E> 
{ 
	/** Add specified element into this Heap, if there is enough space
	 * or if smaller than the current highest element
	 * 
	 * @return true if the element was successfully added
	 */
	boolean insertElement(E e); 
	
    /**
     * Retrieves (without removing) the highest element of this heap
     *
     * @return the highest element of this heap
     * @throws NoSuchElementException if this heap is empty
     */
    E element();
    
    /** Returns true if this heap contains no elements. */
    boolean isEmpty(); 

    /** Returns the number of elements contained in this heap */
    int size();
    
    
    /** Returns true iff the heaps have the same elements*/
    public boolean sameElements(HeapArray otherHeap);
    
    
    /** Returns true if a heap contains element e*/
    public boolean contains(E e);
    
    /** Inserts an array in a heap directly */
    public boolean insertArrayList(List<E> L) throws IOException;
    
}
