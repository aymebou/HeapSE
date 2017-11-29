package heap;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;


public class HeapArray<E> implements Heap<E> {
	
	/////////////////////////////////	  ATTRIBUTES AND CONSTRUCTOR /////////////////////////////
	
	private int size; //Stores the total number of values stored in the heap.
	private int capacity; //The total capacity of the Heap (for the interface to work, it must be capacity = 2^k - 1)
	private List <E> elements; // The list of elements in the heap. Please note that elements[size+k] where k>0 are disregarded
	 private Comparator<E> comparator; //The comparator class that goes along the type E
	
	
	public HeapArray  (int initialCapacity, Comparator comp) {
		/*Creates an empty Heap of given Capacity, a comparator is required to work with the type given
		 * 						(even if the comparator is obvious, e.g E = int )					
		 * 
		 * 				INITIALCAPACITY MUST BE 2^K - 1 WITH K POSITIVE INTEGER	OR WON'T WORK						 */
		this.size = 0;
		this.capacity = initialCapacity;
		this.elements = (List<E>) Arrays.asList(new Object[this.capacity]); 
		this.comparator = comp;
	}
	
	/////////////////////////////////	  GETTERS FUNCTIONS /////////////////////////////
												    //
	public int getCapacity () {						//
		return capacity;							//
	}												//
													//
	public int getSize() {							//
		return size;								//	
	}												//
	public boolean isFull() {						//
		return (size==capacity);					//
	}
	
	
	
	
	/////////////////////////////////// CONVINIENT PRIVATE AUXILIARY FUNCTIONS //////////////////////////////
	
	// swaps two elements at index i and j in the elements list
	private void swap (int i, int j) {
		E storedValue = elements.get(j);
		elements.set(j, elements.get(i));
		elements.set(i, storedValue);
	}
	
	//Gets the index of the minimum value of a heap
	private int getMin() {
		//Since this function is private and only used on a full heap, we will do as if the Heap is full, 
		// disregarding other cases, then we know that the minimum can only be on the last line,
		// we initialize it on the first element on the last line and will compare all.
		
		E min = elements.get(capacity/2);
		int index = capacity/2;
		for (int i = capacity / 2 ; i < capacity ; i++) {
            if (comparator.compare(elements.get(i), min) < 0) {
                min = elements.get(i);
                index = i;
             // NB : It seems preferable to access to the element i twice (the if and the affectation) than to store the value, 
             // because we should only in average get into the if only half of the time
             //, and it should be better than storing a variable all the time.
            }
		}
		return index;
	}
	
	
	// auxiliary function used ONLY in insertElement to avoid copy-pasting code
	// Used to go up the tree to insert an element.
	private void switchUp (int childIndex) {
		int fatherIndex = (childIndex - 1) / 2;
		if ( (childIndex==0) || (comparator.compare(elements.get(childIndex),elements.get(fatherIndex))) < 0){
			//Checking if the recursive has ended i.e we are at the root or if father > son
		}
		else {
			swap(fatherIndex,childIndex);
			switchUp(fatherIndex);
		}
		
	}
	
	

	/////////////////////////////////// HEAP ITERATOR  //////////////////////////////
	
	
	//The iterator :
    public HeapArrayIterator<E> iterator() {
        return new HeapArrayIterator<E>();
    }
	
    //Its interface :
	public class HeapArrayIterator<F> implements Iterator <F> {
		/*
		 * This class implements the HeapArray iterator, which allows to get in the HeapArray
		 * 
		 * */
		
		private int index;
		
		//Constructor function
		HeapArrayIterator() {
	        this.index = -1;
		}
		
		@Override
		public boolean hasNext() {
			return ((index < size -1) && (elements.get(index+1) != null));
		}

		@Override
		public F next() throws IndexOutOfBoundsException {
			if (hasNext()) {
				index++;
				return (F) elements.get(index);
			}
			else {
				throw new IndexOutOfBoundsException ("End reached");
			}
		
			
		}
		
        public void remove() throws IllegalStateException {
            if (index < 0) {
                throw new IllegalStateException("Cannot remove before Iterator.next() method is called !");
            }
            else {
            	List <E> elementsNew = (ArrayList<E>) ((ArrayList<F>) (elements)).clone();
            	elementsNew.remove(index);
            	elements.clear();
            	try {
					insertArrayList( elementsNew);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
}

	}
	
	
	

	
	/////////////////////////////////// CORE INTERFACE FUNCTIONS  //////////////////////////////
	public boolean insertElement(E e) {

		if ( this.isFull() && (comparator.compare(e,(E)elements.get(getMin())) <= 0) ){
			// Notice that the first element of the && is isFull, in case the compare function costs a lot
			return false;			
		}
		else if (!this.isFull()) {
			elements.set(size, e);
			switchUp(size);
			size++;
			return true;
		}
		else {
			//if not full, replace the minimum element by 
			int index = this.getMin();
			elements.set(index, e);
			switchUp(index);
			return true;
		}
	}
	
	//Insert an ArrayList of elements in the heap
	//Returns true if all went correctly
	public boolean insertArrayList(List<E> L) throws IOException {
		for (Object element : L) {
			if (!insertElement((E)element)) {
				throw new IOException("An element wasn't added, i suggest you check the Heap state");
			}    
		}
		return true;
	}

	// Returns the root of a Heap, element at index 0
	public E element() {

		return elements.get(0);
	}

	// Explicit name
	public boolean isEmpty() {
		return (size  == 0);
	}

	// Explicit name
	public int size() {
		return size;
	}
	
	//Return True if Heap contains element e
	public boolean contains(E e) {
		return elements.contains(e);
	}
	
	//Returns True iff the two Heaps contain the same elements
	public boolean sameElements(HeapArray otherHeap) {
		
		for (Object element : this.elements) {
			if (!otherHeap.contains(element)) {
				return false;
			}
		}
		
		return true;
	}


	
	
}
