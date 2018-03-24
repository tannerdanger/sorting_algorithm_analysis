/*
 * Tanner Brown
 * TCSS 342 - Assignment 2
 * Winter 2018 
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents basic unsorted array-based list.
 *
 * @author modified from Building Java Programs 3rd ed.
 * * @author Further modified by Tanner Brown to meet assignment requirements.
 * @version Dec 2017
 * @param <E> is of any object type
 */
public class ArrayListUnsorted<E extends Comparable<? super E>>  {

	/**
	 * default list capacity.
	 */
	private static final int DEFAULT_CAPACITY = 150;

	/**
	 * list of values
	 */
	private E[] elementData;

	/**
	 * index of the last element in the list
	 */
	private int size;

	/**
	 * Constructs an empty list of default capacity.
	 */
	public ArrayListUnsorted() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an empty list of the given capacity.
	 *
	 * @param capacity > 0
	 * @throws IllegalArgumentException if capacity <= 0
	 */
	@SuppressWarnings("unchecked")
	protected ArrayListUnsorted(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity: " + capacity);
		}
		elementData = (E[]) new Comparable[capacity];
		//elementData = (E[]) new Object[capacity];
		size = -1;

	}


	/**
	 * Returns the current number of elements in the list.
	 *
	 * @return the current number of elements in the list >= 0
	 */
	public int getSize() {
		return size + 1;
	}

	/**
	 * Returns whether the list is empty.
	 *
	 * @return true if list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == -1;
	}

	/**
	 * Returns whether value is in the list.
	 *
	 * @param value assigned
	 * @return true if value in the list, false otherwise.
	 */
	public boolean contains(E value) {
		for (int i = 0; i <= size; i++) {
			if (elementData[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Inserts an element into the list.
	 *
	 * @param value assigned
	 */
	public void insert(E value) {
		ensureCapacity(size + 2);
		size++;
		elementData[size] = value;
	}

	/**
	 * Clears the list.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		if (size > DEFAULT_CAPACITY)
			elementData = (E[]) new Object[DEFAULT_CAPACITY];
		size = -1;
	}

	/**
	 * Returns a string representation of list contents.
	 *
	 * @return a string representation of list contents.
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		if (size == -1) {
			return "[]";
		} else {
			String result = "[" + elementData[0];
			for (int i = 1; i <= size; i++) {
				result += ", " + elementData[i];
			}
			result += "]";
			return result;
		}
	}

	/**
	 * Removes first element occurrence from the list.
	 *
	 * @param value assigned
	 */
	public void remove(E value) {
		int index = getIndex(value);
		if (size >= 0 && index >= 0) {
			for (int i = index; i < size; i++)
				elementData[i] = elementData[i + 1];
			size--;
		}
	}

	/**
	 * Ensures that the underlying array has the given capacity; if not,
	 * increases the size by doubling it.
	 *
	 * @param capacity > elementData.length.
	 */
	private void ensureCapacity(int capacity) {
		if (capacity > elementData.length) {
			int newCapacity = elementData.length * 2;
			if (capacity > newCapacity) {
				newCapacity = capacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/*********************************************
	 * Index list methods follow
	 *********************************************/

	/**
	 * Returns the index of value.
	 *
	 * @param value assigned.
	 * @return index of value if in the list, -1 otherwise.
	 */
	public int getIndex(E value) {
		for (int i = 0; i <= size; i++) {
			if (elementData[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}


	/**
	 * Removes value at the given index, shifting subsequent values up.
	 *
	 * @param index < length and index >= 0
	 * @throws IndexOutOfBoundsException if index < 0 or index >= length
	 */
	public void removeAtIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = index; i < size; i++)
			elementData[i] = elementData[i + 1];
		size--;
	}

	/**
	 * Replaces the value at the given index with the given value.
	 *
	 * @param index < length and index >= 0
	 * @value is assigned
	 * @throws IndexOutOfBoundsException if index < 0 or index >= length
	 */
	public void set(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		elementData[index] = value;
	}

	/**
	 * Returns the value at the given index in the list.
	 *
	 * @param index < length and index >= 0
	 * @throws IndexOutOfBoundsException if index < 0 or index >= length
	 * @return the value at the given index in the list.
	 */
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}

	/*********************************************
	 * Index list methods end
	 *********************************************/

	/*********************************************
	 * Algorithm methods follow
	 *********************************************/

	/***** Helper methods ******/

	/**
	 * Method to swap to elements in an array.
	 * @param a an array of objects.
	 * @param index1 the index of the first object.
	 * @param index2 the index of the second object.
	 */
	public static final <E> void swapReferences( E [] a, int index1, int index2 )
	{
		E tmp = a[ index1 ];
		a[ index1 ] = a[ index2 ];
		a[ index2 ] = tmp;
	}

	/**
	 * Helper method used to sort values of 3 to help maintain clean code.
	 * @param a an array of objects.
	 *
	 * @param index1 first index location in the array to be sorted
	 * @param index2 first index location in the array to be sorted
	 * @param index3 first index location in the array to be sorted.
	 */
	public static final <E extends Comparable<? super E>> void  sortThree(E[] a, int index1, int index2, int index3){


		if(a[index1].compareTo(a[index3]) > 0)
			swapReferences(a, index1, index3);

		if(a[index1].compareTo(a[index2]) > 0)
			swapReferences(a, index1, index2);

		if(a[index2].compareTo(a[index3]) > 0)
			swapReferences(a, index2, index3);
	}

	/*************** Insertion Sort Methods *********/


	/**
	 * Public method that calls to insertioSsort method to sort the data in the ArrayList.
	 */
	public void myinsertion(){
		insertionSort(elementData);
	}

	/**
	 * Simple insertion sort.
	 * @param a an array of Comparable items.
	 */
	private void insertionSort(E[] a ) {  
		int i = 1;
		int length = getSize();
		while (i < length){
			int j = i;
			while(j > 0 && a[j-1].compareTo(a[j]) > 0){
				swapReferences(a, j, j-1);
				j--;
			}//end while
			i++;
		}//end while
	}


	/************ Count Sort Methods *************/

	public void myCountingSort(){
		countingSort(elementData);
	}

	@SuppressWarnings("unchecked")
	private void countingSort(E[] a){

		int length = getSize();
		E[] outputArray = (E[]) new Comparable[length];

		//Create array to count instances of each element
		int count[] = new int [length+1];

		//Count occurrences of each element
		for(int i = 0; i < length; i++){
			int tmp = a[i].hashCode();
			//int tmp = testElementData[i];
			count[tmp]++;
		}

		//adjust count[i] so that it contains the position of this
		//element in the output
		for(int i = 1; i <=length; i++){
			count[i] += count[i-1];
		}


		for(int i = 0; i < length; i++){
			outputArray[count[a[i].hashCode()]-1] = a[i];
			count[a[i].hashCode()]--;
		}

		for(int i = 0; i < length; i++){
			a[i] = outputArray[i];
		}
	}

	/************ Quick Sort Methods *************/

	/**
	 * Public method that calls to private quicksort methods for safety.
	 */
	public void myquick(){
		quicksort(elementData);
	}

	/**
	 * Quicksort algorithm.
	 * @param a an array of Comparable items.
	 */
	private void quicksort( E[] a ) {
		quicksort( a, 0, a.length - 1 );
	}


	/**
	 * Internal quicksort method that makes recursive calls.
	 * Uses median-of-three partitioning and a cutoff of 2.
	 * 
	 * @param a an array of Comparable items.
	 * @param low the left-most index of the subarray.
	 * @param high the right-most index of the subarray.
	 */
	private void quicksort( E[] a, int low, int high ) {
		//pick low, pivot and high and sort them
		int mid = ( low + high ) / 2;
		sortThree(a, low, mid, high);

		//if only 3 elements left, sort the 3.
		if(high - low <= 2) {
			if (high - low == 2)
				sortThree(a, low, mid, high);
			if (high - low == 1) ;
			if (high < low)
				swapReferences(a, low, high);
		}else{

			//place the pivot at position high - 1
			swapReferences( a, mid, high - 1 );

			//define the pivot
			E pivot = a[high - 1];

			// Begin partitioning
			int lowIndex, highIndex;
			for (lowIndex = low, highIndex = high - 1; ; ) {
				//increase low index while it is less than the pivot
				while (lowIndex < high-1 && a[++lowIndex].compareTo(pivot) < 0) ;

				//decrease high index while it is greater than the pivot
				while (highIndex > 0 && a[--highIndex].compareTo(pivot) > 0) ;

				//if indexers meet or cross, exit loop
				if (lowIndex >= highIndex)
					break;
				//if they don't meet, swap them and continue
				else
					swapReferences(a, lowIndex, highIndex);
			}

			quicksort(a, low, lowIndex - 1);    // Sort small elements
			quicksort(a, lowIndex, high);
		}
	}

	/************ Merge Sort Methods *************/

	public void mymerge(){
		mergeSort(elementData);
	}

	/**
	 * Public method that allows merge sort to be
	 * applied to a seperate array if desired.
	 *
	 * @param a the array to be sorted.
	 */
	public void mymerge(E[] a){
		mergeSort(a);
	}


	/**
	 * Mergesort algorithm.
	 * @param a an array of Comparable items.
	 */
	// public <E extends Comparable<? super E>> void mergeSort( E [ ] a ) {
	public void mergeSort( E [ ] a ) {

		@SuppressWarnings("unchecked")
		E [] tmpArray = (E []) new Comparable[getSize()];
		//E [] tmpArray = (E []) new Comparable[ a.length ];
		mergeSort( a, tmpArray, 0, getSize() - 1 );
	}

	/**
	 * Internal method that makes recursive calls.
	 * @param a an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param left the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	//    private static <E extends Comparable<? super E>> void mergeSort( E [] a, E [ ] tmpArray,
	//                                                                                 int left, int right )
	private void mergeSort( E [] a, E [ ] tmpArray, int left, int right )
	{
		if( left < right )
		{
			int center = ( left + right ) / 2;
			mergeSort( a, tmpArray, left, center );
			mergeSort( a, tmpArray, center + 1, right );
			merge( a, tmpArray, left, center + 1, right );
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param a an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	//    private static <E extends Comparable<? super E>> void merge( E [ ] a, E [ ] tmpArray,
	//                                                                             int leftPos, int rightPos, int rightEnd )
	private void merge( E [ ] a, E [ ] tmpArray, int leftPos, int rightPos, int rightEnd ) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd )
			if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
				tmpArray[ tmpPos++ ] = a[ leftPos++ ];
			else
				tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		while( leftPos <= leftEnd )    // Copy rest of first half
			tmpArray[ tmpPos++ ] = a[ leftPos++ ];

		while( rightPos <= rightEnd )  // Copy rest of right half
			tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		// Copy tmpArray back
		for( int i = 0; i < numElements; i++, rightEnd-- )
			a[ rightEnd ] = tmpArray[ rightEnd ];
	}

	/********* Odd-even sort ************/

	public void myOddEven(){
		oddEvenSort(elementData);
	}

	/**
	 * Public method that allows odd-even sort to be
	 * applied to a seperate array if desired.
	 *
	 * @param a the array to be sorted.
	 */
	public void myOddEven(E[] a){
		oddEvenSort(a);
	}


	private void oddEvenSort(E[] a){
		boolean isSorted = false;
		int aSize = getSize(); //length-1?


		//while the array is not sorted
		while (!isSorted){

			/*
			 * set true so each forloop can check to determine
			 * if it is not sorted.
			 */
			isSorted = true;

			//perform Bubble sort on the odd - indexed elements
			for(int i = 1; i <= aSize-2; i=i+2){
				if(a[i].compareTo(a[i+1]) > 0){
					swapReferences(a, i, i+1);
					isSorted = false;
				}
			}

			//Perform bubble sort on even indexed elements.
			for(int i = 0; i <= aSize-2; i=i+2){
				if(a[i].compareTo(a[i+1])>0){
					swapReferences(a, i, i+1);
					isSorted = false;
				}
			}
		}
	}



	/*********************************************
	 * Algorithm methods end
	 *********************************************/

	/*********************************************
	 * Iterator list class / methods follow
	 *********************************************/

	/**
	 * Returns an iterator for this list.
	 *
	 * @return an iterator for the list.
	 */
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	/**
	 * Represents an iterator for the list.
	 *
	 * @author BuildingJavaPrograms 3rd Edition
	 */
	private class ArrayListIterator implements Iterator<E> {

		/**
		 * current position within the list.
		 */
		private int position;

		/**
		 * flag that indicates whether list element can be removed.
		 */
		private boolean removeOK;

		/**
		 * Constructs an iterator for the given list
		 */
		public ArrayListIterator() {
			position = 0;
			removeOK = false;
		}

		/**
		 * Returns whether there are more list elements.
		 *
		 * @return true if there are more elements left, false otherwise
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return position <= size;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @throws NoSuchElementException if no more elements.
		 * @return the next element in the iteration.
		 * @see java.util.Iterator#next()
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E result = elementData[position];
			position++;
			removeOK = true;
			return result;
		}

		/**
		 * Removes the last element returned by the iterator.
		 *
		 * @throws IllegalStateException if a call to next has not been made
		 *             before call to remove.
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			ArrayListUnsorted.this.removeAtIndex(position - 1);
			position--;
			removeOK = false;
		}
	}

	/*********************************************
	 * Iterator list class / methods end
	 *********************************************/


}