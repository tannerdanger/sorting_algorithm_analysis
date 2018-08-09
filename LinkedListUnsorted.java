/*
 * Tanner Brown
 * January 2018 
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents basic unsorted node-based list.
 *
 * @author modified from Building Java Programs 3rd ed.
 * @author Further modified by Tanner Brown to meet assignment requirements.
 * @version Sep 2016
 * @param <E> is of any object type
 */
public class LinkedListUnsorted<E>{

	/**
	 * Reference to the first node in the list.
	 */
	private ListNode<E> front;

	/**
	 * Reference to the last node in the list.
	 */
	private ListNode<E> back;

	/**
	 * index of the last list element
	 */
	private int size;

	/**
	 * Constructs an empty list.
	 */
	public LinkedListUnsorted() {
		front = null;
		back = null;
		size = -1;
	}

	/**
	 * Get the size of the list.
	 * @return the size of the list
	 */
	public int getSize() {
		return size + 1;
	}

	/**
	 * Returns if the LinkedList is empty
	 */
	public boolean isEmpty() {
		return size == -1;
	}

	/**
	 * Returns if there exists a node with the given value
	 * @param value the value being searched for.
	 */
	public boolean contains(E value) {
		return getIndex(value) >= 0;
	}

	/**
	 * Inserts a new node
	 * @param value of new data in the new node
	 */
	public void insert(E value) {
		ListNode<E> valueNode = new ListNode<E>(value);
		if (size == -1) {
			front = valueNode;
			back = valueNode;
		} else {
			back.next = valueNode;
			back = valueNode;
		}
		size++;
	}

	/**
	 * Clear the list
	 */
	public void clear() {
		front = null;
		back = null;
		size = -1;
	}


	/**
	 * Creates a comma-separated, bracketed version of the list.
	 *
	 * @see Object#toString()
	 */
	public String toString() {
		if (size == -1) {
			return "[]";
		} else {
			String result = "[" + front.data;
			ListNode<E> current = front.next;
			while (current != null) {
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}



	/**
	 *
	 */
	public void remove(E value) {
		if (size > -1) {					 // if the list is not empty
			if (value.equals(front.data)) {  // if value in the first node
				front = front.next;
				if (size == 0)				 // if it was the only node
					back = null;
				size--;
			}
			else {							  // value not in the first node
				ListNode<E> prior = front;	  // set up a trailing reference
				while (prior.next != null) {   // look ahead one node
					if (prior.next.data.equals(value)) { // found it
						prior.next = prior.next.next;
						if (prior.next == null)    // if deleting the last node
							back = prior;
						size--;
						break;
					} //endif
					prior = prior.next;
				} //endwhile
			} // endelse
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
		int index = 0;
		ListNode<E> current = front;
		while (current != null) {
			if (current.data.equals(value)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	/**
	 * Removes value at the given index, shifting subsequent values up.
	 *
	 * @param index <= size and index >= 0
	 * @throws IndexOutOfBoundsException if index < 0 or index > size
	 */
	public void removeAtIndex(int index) {
		checkIndex(index);
		if (index == 0) {
			front = front.next;
			if (size == 0)
				back = null;
		}
		else {
			ListNode<E> current = nodeAt(index - 1);
			current.next = current.next.next;
			if (current.next == null)
				back = current;
		}

		size--;
	}

	public void myinsertion(){
		insertionSort();
	}

	public void myOddEven(){
		oddEvenSort();
	}

	/**
	 * Replaces the value at the given index with the given value.
	 *
	 * @param index <=size
	 * @param value is assigned
	 * @throws IndexOutOfBoundsException if index < 0 or index > size
	 */
	public void set(int index, E value) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		current.data = value;
	}

	/**
	 * Returns the value at the given index in the list.
	 *
	 * @param index <=size
	 * @throws IndexOutOfBoundsException if index < 0 or index > size
	 * @return the value at the given index in the list.
	 */
	public E get(int index) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		return current.data;
	}

	/**
	 * Returns the node at a specific index.
	 * @param index where 0 <= index <= size
	 * @return reference to the node at a specific index
	 */
	private ListNode<E> nodeAt(int index) {
		ListNode<E> current = front;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		return current;
	}


	/**
	 * Checks if the index is a legal index of the current list.
	 * @param index
	 * @throws IndexOutOfBoundsException if the given index is not a legal index of the current list
	 */
	private void checkIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index: " + index);
		}
	}

	/*********************************************
	 * Index list methods end
	 *********************************************/

	/*********************************************
	 * Algorithm methods follow
	 *********************************************/


	/************** Odd-Even Sort ***************/

	/**
	 * Method that sorts values using odd-even sort.
	 */
	private void oddEvenSort() {

		ListNode<E> swapNode = null;
		ListNode<E> traverseNode;
		ListNode<E> priorNode;
		int loopSize;

		boolean isSorted = false;
		boolean evenSwap = true; //flag to swap between even and odd sorting
		loopSize = getSize()/2;
		if(getSize()%2 != 0)
			loopSize++;

		//while the array is not sorted
		while (!isSorted) {
			isSorted = true;
			if(evenSwap){
				priorNode = traverseNode = front;
				swapNode = traverseNode.next;
				evenSwap = false;
			}else{
				priorNode = front;
				traverseNode = front.next;
				swapNode = traverseNode.next;
				evenSwap = true;
			}

			int i = loopSize;
			while (i > 1 && swapNode != null && swapNode.next != null){
				i--;

				if(swapNode.data.hashCode() < traverseNode.data.hashCode()){ //nodes need to swap
					if(traverseNode == front){ //if the lower index node is the front
						traverseNode.next = swapNode.next;
						swapNode.next = traverseNode;
						front = swapNode;
					}else{
						priorNode.next = swapNode;
						traverseNode.next = swapNode.next;
						swapNode.next = traverseNode;
					}
					isSorted = false;

					//Move node references forward to next location
					if(traverseNode.next != back) {
						priorNode = traverseNode;
						traverseNode = traverseNode.next;
						swapNode = traverseNode.next;
					}

					//if not swapped, move everything two spaces foward
				} else if(traverseNode.next != back && traverseNode.next.next != back) {
					priorNode = traverseNode.next;
					traverseNode = traverseNode.next.next;
					swapNode = traverseNode.next;
				}else{
					break; //break if next nodes are null (shoulnd't be necessary, mostly here as a backup).
				}

				//if the node to be swapped is the back node...
			} if(swapNode == back && swapNode.data.hashCode() < traverseNode.data.hashCode()){
				isSorted = false;
				priorNode.next = swapNode;
				swapNode.next = traverseNode;
				traverseNode.next = null;
				back = traverseNode;
			}
		}
	}


	/************** Insertion Sort ***************/

	/**
	 * This method sorts the element values in this object
	 * using an insertion sort method.
	 */
	private void insertionSort(){
		int i, j;
		ListNode<E> partitionNode, swapNode;
		LinkedIterator it;
		//partition node represents barrier between sorted and unsorted parts of array
		partitionNode = front; 
		swapNode = front.next; //node in front of partition that is to be sorted

		i = 1;
		int len = getSize();
		while (i < len) {
			j = 0;
			swapNode = partitionNode.next;
			//if swapnode is higher than the highest sorted data
			if (swapNode.data.hashCode() > partitionNode.data.hashCode()) {
				partitionNode = swapNode; //move partition forward one node
				swapNode = swapNode.next;

			}else{
				it = (LinkedIterator) iterator();
				//find the first sorted node that is greater than the swap node
				while(j < i && swapNode.data.hashCode() > it.current.data.hashCode()) {
					it.next();
					j++;
				}
				partitionNode.next = swapNode.next; //have partition point to node after swap
				swap(it.prior, it.current, swapNode);
			}
			i++;
		}
	}
	/**
	 * This helper method to insertion sort swaps nodes.
	 * 
	 * @param sortedPrior the node prior to the swap node
	 * @param sorted the sorted node that will be located after the swap node
	 * @param swap the node that needs to be sorted
	 */
	private void swap(ListNode<E> sortedPrior, ListNode<E> sorted, ListNode<E> swap) {
		//swap is new head
		if(sortedPrior == null){
			swap.next = front;
			front = swap;
		} else {
			sortedPrior.next = swap;
			swap.next = sorted;
		}
	}

	/*********************************************
	 * End Algorithm Methods
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
		return new LinkedIterator();
	}

	/**
	 * Represents a list node.
	 * @author Building Java Programs 3rd ed.
	 *
	 * @param <E> is of any object type
	 */
	private static class ListNode<E> {

		/**
		 * Data stored in this node.
		 */
		public E data;

		/**
		 * Link to next node in the list.
		 */
		public ListNode<E> next;


		/**
		 * Constructs a node with given data and a null link.
		 * @param data assigned
		 */
		public ListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructs a node with given data and given link.
		 * @param data assigned
		 * @param next assigned
		 */
		public ListNode(E data, ListNode<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Represents an iterator for the list.
	 *
	 * @author modified from BuildingJavaPrograms 3rd Edition
	 */
	private class LinkedIterator implements Iterator<E> {

		/**
		 * Location of current value to return.
		 */
		private ListNode<E> current;

		/**
		 * flag that indicates whether list element can be removed.
		 */
		private boolean removeOK;

		/**
		 * Location of the prior value in case of removal.
		 */
		private ListNode<E> prior;

		/**
		 * Constructs an iterator for the given list.
		 */
		public LinkedIterator() {
			current = front;
			removeOK = false;
			prior = null;
		}

		/**
		 * Returns whether there are more list elements.
		 *
		 * @return true if there are more elements left, false otherwise
		 * @see Iterator#hasNext()
		 */
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @throws NoSuchElementException if no more elements.
		 * @return the next element in the iteration.
		 * @see Iterator#next()
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prior = current;
			E result = current.data;
			current = current.next;
			removeOK = true;
			return result;
		}

		/**
		 * Removes the last element returned by the iterator.
		 *
		 * @throws IllegalStateException if a call to next has not been made
		 *             before call to remove.
		 * @see Iterator#remove()
		 */
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			LinkedListUnsorted.this.remove(prior.data);
			removeOK = false;
		}
	}


}

