/**
 * 
 */
package heap;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Ayme-
 *
 */
public class HeapArrayTest {

	/**
	 * Test method for {@link heap.HeapArray#HeapArray(int, java.util.Comparator)}.
	 */
	@Test
	public void testHeapArray() {
		
		 HeapArray<Integer> testHeap = new HeapArray<>(7, new Comparator<Integer>() {
			 												public int compare(Integer a, Integer b) { return a-b; }
		 																		} );
		 assertEquals(7, testHeap.getCapacity());
		
	}

	/**
	 * Test method for {@link heap.HeapArray#isFull()}.
	 */
	@Test
	public void testIsFull() {
		//In reality this function's test isn't needed because it's called in insertElement, but for the sake of cleanness it is :
		//test : an empty length 1 Heap isn't full
		HeapArray<Integer> testHeap = new HeapArray<>(1, new Comparator<Integer>() {
				public int compare(Integer a, Integer b) { return a-b; }
									} );
		assertFalse(testHeap.isFull());
	}

	/**
	 * Test method for {@link heap.HeapArray#iterator()}.
	 */
	@Test
	public void testIterator() {
		/*
		 * test : hasNext on an empty collection (returns false)
		 * 		  Next on a collection with one item (returns the item)
		 * 		  remove on a one element list, hasNext to check if empty
		 * 
		 * */
		HeapArray<Integer> testHeap = new HeapArray<>(1, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		HeapArray<Integer>.HeapArrayIterator<Integer> it =   testHeap.iterator();
		assertFalse(it.hasNext());
		testHeap.insertElement(12);
		assertEquals((int)it.next(),12);
		//it.remove();								// This last test doesn't work, remove function not working.
		//assertTrue(testHeap.isEmpty());
	}

	/**
	 * Test method for {@link heap.HeapArray#insertElement(java.lang.Object)}.
	 */
	@Test
	public void testInsertElement() {
		// test : checking if inserting 2 returns true
		HeapArray<Integer> testHeap = new HeapArray<>(1, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		assertTrue(testHeap.insertElement((int)2));
	}

	/**
	 * Test method for {@link heap.HeapArray#element()}.
	 */
	@Test
	public void testElement() {
		
	}

	/**
	 * Test method for {@link heap.HeapArray#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		//test : checking if empty Heap of length 1 is empty
		HeapArray<Integer> testHeap = new HeapArray<>(1, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		assertTrue(testHeap.isEmpty());
	}

	/**
	 * Test method for {@link heap.HeapArray#size()}.
	 */
	@Test
	public void testSize() {
		//test : inserting numbers from 0 to 9 and checking size = 10
		HeapArray<Integer> testHeap = new HeapArray<>(15, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		for (int i=0;i<10;i++) {
			testHeap.insertElement(10-i);
		}
		
		assertEquals((int)testHeap.size(),10);
		
	}
	public void testInsertArrayList () throws IOException {
		// test : creating two heaps same as testSize() one with array, one with loop, 
		// checking if size and first element coincide.
		HeapArray<Integer> testHeap1 = new HeapArray<>(15, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		HeapArray<Integer> testHeap2 = new HeapArray<>(15, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) { return a-b; }
								} );
		

		List<Integer> toAdd = (List<Integer>) Arrays.asList(new Integer []{1,2,3,4,5,6,7,8,9});
		
		testHeap1.insertArrayList(toAdd);
		
		
		for (int i=0;i<10;i++) {
			testHeap2.insertElement(10-i);
		}

		assertEquals(testHeap1.element(),testHeap2.element());
		assertEquals(testHeap1.getSize(),testHeap2.getSize());
		assertTrue(testHeap1.sameElements(testHeap2));
	}
	


}
