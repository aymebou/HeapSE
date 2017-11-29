package heap;

import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.io.IOException;


public class HeapTree<E> implements Heap<E> {
	/*
	 * This method implements the class Heap but with a tree structure instead of an array structure
	 * This implementation is much easier to understand because of the structure we are dealing with 
	 *
	 * */
	
	///////////////////////////////////// ATTRIBUTES AND CONSTRUCTOR //////////////////////////////////
	
	private HeapTree<E> childL;
	private HeapTree<E> childR;
	private HeapTree<E> father;
	
	private E value;
	private Comparator <E> comparator;
	private int size;
	
    public HeapTree(Comparator<E> comparator) {
    	/*
    	 * Creates an empty HeapTree with correct type and comparator
    	 * */
    	this.size = 0;
    	this.childL = null;
    	this.childR = null;
    	this.father = null;
    	this.value = null;
        this.comparator = comparator;
    }
	
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	private void switchUp () {
		
	}

	@Override
	public boolean insertElement(E e) {
		//Pattern : if leaf : insert here, else, insert to left or right if non-existant and else insert 
		// to left or right depending on the minimum length
		if (value == null) {
			value = e;
		}
		else if (childL ==null) {
			childL = new HeapTree(comparator);
			childL.insertElement(e);
			childL.father = this;
			childL.switchUp();
		}
		else if (childR ==null) {
			childR = new HeapTree(comparator);
			childR.insertElement(e);
			childR.father = this;
			childR.switchUp();
		}
		else if (childL.minimumLength() == childR.minimumLength()) {
			childL.insertElement(e);
		}
		
		return true;
	}
	
	// We define recursive function that gives the length at minimum point (far right side)
	private int minimumLength() {
		if (childR ==null) {
			return 0;
		}
		else {
			return 1+this.childR.minimumLength();
		}
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean sameElements(HeapArray otherHeap) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertArrayList(List<E> L) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
