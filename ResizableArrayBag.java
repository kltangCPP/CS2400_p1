import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
	private T[] bag;
	private static final int DEFAULT_CAPACITY = 25;
	private int numOfEntries;
	private static final int MAX_CAPACITY = 10000;
	
	public ResizableArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	public ResizableArrayBag(int size) {
		numOfEntries = 0;
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[size];
		bag = tempBag;
	}
	
	public boolean add(T newEntry) {
		if (isFull())
			doubleCapacity();
		bag[numOfEntries] = newEntry;
		numOfEntries++;
		return true;
	} // end add
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numOfEntries];
		for (int i = 0; i < numOfEntries; i++) 
			result[i] = bag[i];
		return result;
	} // end toArray
	
	public boolean isFull() {
		return numOfEntries == bag.length;
	} //end isFull
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose "+
					"capacity exeeds allowed "+
					"maximum of "+ MAX_CAPACITY);
	} //end checkCapacity
	
	private void doubleCapacity() {
		int newLength = 2*bag.length;
		checkCapacity(newLength);
		bag = Arrays.copyOf(bag, newLength);
	} // end doubleCapacity
	
	public boolean isEmpty() {
		return numOfEntries == 0;
	} // end isEmpty
	
	public int getCurrentSize() {
		return numOfEntries;
	} //end getCurrentSize
	
	public void clear() {
		while (!isEmpty())
			remove();
	} // end clear
	
	public boolean contains(T anEntry) {
		return getIndexOf(anEntry) > -1;
	} // end contains
	
	public BagInterface<T> union (BagInterface<T> otherBag) {
		BagInterface<T> everything = new ResizableArrayBag<>(); // Create everything to hold the union
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		
		for (int i = 0; i < secondBag.length; i++) {
			everything.add(secondBag[i]); // Place all of the second bag contents into everything
		}
		for (int i = 0; i < firstBag.length; i++) {
			everything.add(firstBag[i]); // Place all of the first bag contents into everything
		}
		return everything; // Return the union of the two bags
	} // end union
	
	public BagInterface<T> intersection (BagInterface<T> otherBag) {
		BagInterface<T> commonItems = new ResizableArrayBag<>(); // Create commonItems to hold the intersection
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		BagInterface<T> copyOfBag = new ResizableArrayBag<>(); // Create copy of the linked bag to hold contents without altering actual bag
		
		for (int i = 0; i < firstBag.length; i++) {
			copyOfBag.add(firstBag[i]); // Make a copy of the first bag
		}
		
		for (int i = 0; i < secondBag.length; i++) {
			if (copyOfBag.contains(secondBag[i])) {
				commonItems.add(secondBag[i]); // If first and second bag have an item in common, add to commonItems
				copyOfBag.remove(secondBag[i]); // Remove the common item from the copied bag
			}
		}
		return commonItems; // Return the intersection of the two bags
	} // end intersection
	
	public BagInterface<T> difference(BagInterface<T> otherBag) {
		BagInterface<T> leftOver = new ResizableArrayBag<>(); // Create leftOver to hold the difference
		T[] firstBag = this.toArray(); // Take first bag as an array
		T[] secondBag = otherBag.toArray(); // Take second bag as an array
		
		for (int i = 0; i < firstBag.length; i++) {
			leftOver.add(firstBag[i]); // Add first bag to the leftovers
		}
		
		for (int i = 0; i < secondBag.length; i++) {
			if (leftOver.contains(secondBag[i])) {
				leftOver.remove(secondBag[i]); // If first bag contains anything from second bag, take it out
			}
		}
		return leftOver; // Return the difference of the two bags
	} // end difference

	public T remove() {
		T result = removeEntry(numOfEntries-1);
		return result;
	} // end remove

	public boolean remove(T anEntry) {
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);         
		return anEntry.equals(result);
	} // end remove  

	public int getFrequencyOf(T anEntry) {
		int counter = 0;
		for(int i = 0; i < numOfEntries; i++){
			if(anEntry.equals(bag[i])){
				counter++;
			} // end if
		} // end for
		return counter;
	} //end getFrequencyOf
	
	private T removeEntry(int givenIndex){
		T result = null;
		if(!isEmpty() && (givenIndex>= 0)){
			result = bag[givenIndex];                   // Entry to remove
			bag[givenIndex] = bag[numOfEntries-1]; // Replace entry with last entry
			bag[numOfEntries-1] = null;            // Remove last entry
			numOfEntries--;
		} // end if
		return result;
	} // end removeEntry
	
	private int getIndexOf(T anEntry) {
		int where = -1;
		boolean found = false;
		int index = 0;
		while(!found && (index < numOfEntries)){
			if(anEntry.equals(bag[index])){
				found = true;
				where = index;} // end if
			index++;
			} // end while
		
		return where;
	} //end getIndexOf
}
